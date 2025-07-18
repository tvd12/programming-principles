/*
 * Copyright 2023 youngmonkeys.org
 * 
 * Licensed under the ezyplatform, Version 1.0.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     https://youngmonkeys.org/licenses/ezyplatform-1.0.0.txt
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.youngmonkeys.dfs.xss;

import com.tvd12.ezyfox.collect.Sets;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Set;

import static org.youngmonkeys.dfs.util.Strings.endsWith;
import static org.youngmonkeys.dfs.util.Strings.substringLast;

public final class Htmls {

    public static final String TAG_NAME_PRE = "pre";
    public static final String TAG_NAME_SCRIPT = "script";
    public static final String HTML_COMMENT_TAG_OPEN = "!--";
    public static final String HTML_COMMENT_TAG_CLOSE = "--";
    public static final Set<String> HTML_TAGS = Collections
        .unmodifiableSet(
            Sets.newHashSet(
                "!DOCTYPE", "a", "abbreviation", "acronym",
                "address", "anchor", "applet", "area", "article",
                "aside", "audio", "base", "basefont", "bdi", "bdo",
                "bgsound", "big", "blockquote", "b", "body", "bold", "break",
                "button", "caption", "canvas", "center", "cite", "code",
                "colgroup", "column", "comment", "data", "datalist",
                "dd", "define", "delete", "details", "dialog",
                "dir", "div", "dl", "dt", "em", "embed", "fieldset",
                "figcaption", "figure", "font", "footer", "form",
                "frame", "frameset", "head", "header", "heading",
                "hgroup", "hr", "html", "h1", "h2", "h3", "h4", "h5",
                "h6", "iframe", "i", "image", "input",
                "ins", "isindex", "italic", "kbd", "keygen", "label",
                "legend", "li", "list", "main", "mark", "marquee", "map",
                "menuitem", "meta", "meter", "nav", "nobreak", "noembed",
                "noscript", "object", "ol", "optgroup", "option", "output",
                "p", "paragraphs", "param", "phrase", "pre", "progress",
                "q", "rp", "rt", "ruby", "s", "samp", "script", "section",
                "small", "source", "spacer", "span", "strike", "strong",
                "style", "sub", "sup", "summary", "svg", "table", "tbody",
                "td", "template", "tfoot", "th", "thead", "time", "title",
                "tr", "track", "tt", "ul", "underline", "var", "video",
                "wbr", "xmp"
            )
        );
    public static final Set<String> NO_CLOSED_TAGS = Collections
        .unmodifiableSet(
            Sets.newHashSet(
                "area", "base", "br", "col", "command", "!DOCTYPE", "embed", "hr",
                "img", "input", "keygen", "link", "meta", "param", "source", "track", "wbr"
            )
        );

    private Htmls() {}

    public static boolean containsScriptTag(String content) {
        int contentLength = content.length();
        for (int i = 0; i < contentLength; ++i) {
            char ch = content.charAt(i);
            if (ch == '<') {
                int k = 0;
                for (++i; i < contentLength; ++i) {
                    ch = content.charAt(i);
                    if (ch != '<') {
                        break;
                    }
                }
                for (; i < contentLength; ++i) {
                    ch = content.charAt(i);
                    if (ch != ' ' && ch != '\t' && ch != '\n') {
                        break;
                    }
                }
                for (; i < contentLength; ++i) {
                    ch = content.charAt(i);
                    if (ch != TAG_NAME_SCRIPT.charAt(k++)) {
                        return false;
                    }
                    if (k == TAG_NAME_SCRIPT.length()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String escapeHtmlTag(String content) {
        return content
            .replace("<", "&lt")
            .replace(">", "&gt");
    }

    @SuppressWarnings("MethodLength")
    public static String escapeScriptTag(String content) {
        StringBuilder answer = new StringBuilder();
        int contentLength = content.length();
        for (int i = 0; i < contentLength; ++i) {
            char ch = content.charAt(i);
            if (ch != '<') {
                answer.append(ch);
                continue;
            }

            // for case <<<script
            for (++i; i < contentLength; ++i) {
                ch = content.charAt(i);
                if (ch != '<') {
                    break;
                } else {
                    answer.append(ch);
                }
            }

            // for case <   script
            StringBuilder before = new StringBuilder();
            for (; i < contentLength; ++i) {
                ch = content.charAt(i);
                if (ch != ' ' && ch != '\t' && ch != '\n' && ch != '/') {
                    break;
                } else {
                    before.append(ch);
                }
            }
            StringBuilder tagNameBuilder = new StringBuilder();
            for (; i < contentLength; ++i) {
                ch = content.charAt(i);
                if (ch != ' ' && ch != '\t' && ch != '\n' && ch != '>') {
                    tagNameBuilder.append(ch);
                } else {
                    --i;
                    break;
                }
            }
            String tagName = tagNameBuilder.toString();
            if (tagName.equals(TAG_NAME_SCRIPT)) {
                answer
                    .append("&lt")
                    .append(before)
                    .append(tagName);

                // for case <script   >
                for (++i; i < contentLength; ++i) {
                    ch = content.charAt(i);
                    if (ch == '>') {
                        answer.append("&gt");
                        break;
                    } else {
                        answer.append(ch);
                    }
                }
            } else {
                answer
                    .append("<")
                    .append(before)
                    .append(tagName);
            }
        }
        return answer.toString();
    }

    public static void validateHtmlContent(String content) {
        validateHtmlContent(content, true);
    }

    @SuppressWarnings("MethodLength")
    public static void validateHtmlContent(
        String content,
        boolean acceptMarkdown
    ) {
        char ch = 0;
        int backslashCount;
        Deque<String> openTags = new ArrayDeque<>();
        int contentLength = content.length();
        for (int i = 0; i < contentLength; ++i) {
            backslashCount = 0;
            for (; i < contentLength; ++i) {
                ch = content.charAt(i);
                if (ch == '\\') {
                    ++backslashCount;
                } else {
                    break;
                }
            }
            if (ch == '`'
                && backslashCount % 2 == 0
                && acceptMarkdown
            ) {
                StringBuilder marks = new StringBuilder();
                for (; i < contentLength; ++i) {
                    ch = content.charAt(i);
                    if (ch == '`') {
                        marks.append(ch);
                    } else {
                        break;
                    }
                }
                i = validateCodeContainerTag(
                    content,
                    contentLength,
                    i,
                    marks.toString(),
                    false
                );
                continue;
            }
            if (ch != '<') {
                continue;
            }

            // for case <<<script
            for (++i; i < contentLength; ++i) {
                ch = content.charAt(i);
                if (ch != '<') {
                    break;
                }
            }

            // for case <   script
            boolean isOpenTag = true;
            for (; i < contentLength; ++i) {
                ch = content.charAt(i);
                if (ch == '/') {
                    isOpenTag = false;
                }
                if (ch != ' ' && ch != '\t' && ch != '\n' && ch != '/') {
                    break;
                }
            }
            StringBuilder tagNameBuilder = new StringBuilder();
            for (; i < contentLength; ++i) {
                ch = content.charAt(i);
                if (ch != ' '
                    && ch != '\t'
                    && ch != '\n'
                    && ch != '/'
                    && ch != '>'
                ) {
                    tagNameBuilder.append(ch);
                } else {
                    break;
                }
            }

            String tagName = tagNameBuilder.toString();

            // check script tag
            if (tagName.equals(TAG_NAME_SCRIPT)) {
                i = validateCodeContainerTag(
                    content,
                    contentLength,
                    i,
                    TAG_NAME_SCRIPT,
                    true
                );
                continue;
            }

            if (tagName.isEmpty()) {
                continue;
            }

            char firstCh = tagName.charAt(0);
            if ((firstCh < 'a' || firstCh > 'z') && (firstCh < 'A' || firstCh > 'Z')) {
                continue;
            }

            // check comment
            if (tagName.startsWith(HTML_COMMENT_TAG_OPEN)) {
                for (++i; i < contentLength; ++i) {
                    ch = content.charAt(i);
                    if (ch == '>'
                        && endsWith(content, i, HTML_COMMENT_TAG_CLOSE)
                    ) {
                        ++i;
                        break;
                    }
                }
                continue;
            }

            // check tag content
            boolean startAttribute = false;
            boolean startAttributeDoubleQuotes = false;
            for (; i < contentLength; ++i) {
                ch = content.charAt(i);
                char beforeCh = content.charAt(i - 1);
                char nextCh = i < contentLength - 1
                    ? content.charAt(i + 1)
                    : 0;
                if ((ch == '/' && nextCh == '>') || ch == '>') {
                    if (startAttribute) {
                        throw new InvalidHtmlContentException(
                            tagName,
                            "incomplete_tag_attribute",
                            i,
                            subNearbyErrorContent(content, i)
                        );
                    }
                    break;
                } else if (ch == '=') {
                    if (startAttribute && !startAttributeDoubleQuotes) {
                        throw new InvalidHtmlContentException(
                            tagName,
                            "invalid_tag_attribute",
                            i,
                            subNearbyErrorContent(content, i)
                        );
                    }
                    startAttribute = true;
                } else if (ch == '"') {
                    if (!startAttribute) {
                        throw new InvalidHtmlContentException(
                            tagName,
                            "invalid_tag_attribute",
                            i,
                            subNearbyErrorContent(content, i)
                        );
                    }
                    if (beforeCh == '\\' && !startAttributeDoubleQuotes) {
                        throw new InvalidHtmlContentException(
                            tagName,
                            "invalid_tag_attribute",
                            i,
                            subNearbyErrorContent(content, i)
                        );
                    } else if (beforeCh != '\\') {
                        startAttributeDoubleQuotes = !startAttributeDoubleQuotes;
                        if (!startAttributeDoubleQuotes) {
                            startAttribute = false;
                        }
                    }
                } else if (startAttribute
                    && !startAttributeDoubleQuotes
                    && ch != ' '
                    && ch != '\t'
                    && ch != '\n'
                ) {
                    throw new InvalidHtmlContentException(
                        tagName,
                        "tag_attribute_must_start_with_double_quotes",
                        i,
                        subNearbyErrorContent(content, i)
                    );
                }
            }

            // check close tag
            boolean hasGtSign = false;
            boolean selfCloseTag = false;
            for (; i < contentLength; ++i) {
                ch = content.charAt(i);
                if (ch == '/') {
                    selfCloseTag = true;
                } else if (ch == '>') {
                    hasGtSign = true;
                    break;
                } else if (selfCloseTag) {
                    throw new InvalidHtmlContentException(
                        tagName,
                        "invalid_tag_content",
                        i,
                        subNearbyErrorContent(content, i)
                    );
                } else if (ch == '"') {
                    char beforeCh;
                    boolean hasDoubleQuotationMarks = false;
                    for (++i; i < contentLength; ++i) {
                        beforeCh = content.charAt(i - 1);
                        ch = content.charAt(i);
                        if (ch == '"' && beforeCh != '\\') {
                            hasDoubleQuotationMarks = true;
                            break;
                        }
                    }
                    if (!hasDoubleQuotationMarks) {
                        throw new InvalidHtmlContentException(
                            tagName,
                            "missing_close_double_quotation_marks",
                            i,
                            subNearbyErrorContent(content, i)
                        );
                    }
                }
            }
            if ((selfCloseTag && hasGtSign) || !HTML_TAGS.contains(tagName)) {
                continue;
            }
            if (hasGtSign) {
                String openTag = openTags.peek();
                if (isOpenTag) {
                    openTags.push(tagName);
                } else {
                    if (openTag == null) {
                        throw new InvalidHtmlContentException(
                            tagName,
                            "missing_open_tag",
                            i,
                            subNearbyErrorContent(content, i)
                        );
                    }
                    while (NO_CLOSED_TAGS.contains(openTag)) {
                        openTags.pop();
                        openTag = openTags.peek();
                    }
                    if (openTag != null) {
                        if (openTag.equals(tagName)) {
                            openTags.pop();
                        } else {
                            throw new InvalidHtmlContentException(
                                openTag,
                                "missing_close_tag",
                                i,
                                subNearbyErrorContent(content, i)
                            );
                        }
                    }
                }
            } else {
                throw new InvalidHtmlContentException(
                    tagName,
                    "missing_the_greater_than_sign_to_close_tag",
                    i,
                    subNearbyErrorContent(content, i)
                );
            }
        }
        while (!openTags.isEmpty()) {
            String openTag = openTags.peek();
            if (NO_CLOSED_TAGS.contains(openTag)) {
                openTags.pop();
            } else {
                break;
            }
        }
        String openTag = openTags.peek();
        if (openTag != null) {
            throw new InvalidHtmlContentException(
                openTag,
                "missing_close_tag",
                contentLength,
                ""
            );
        }
    }

    private static String subNearbyErrorContent(
        String content,
        int position
    ) {
        return substringLast(
            content,
            position,
            120
        );
    }

    private static int validateCodeContainerTag(
        String content,
        int contentLength,
        int i,
        String tagName,
        boolean isScriptTag
    ) {
        boolean startQuote = false;
        boolean startDoubleQuotes = false;
        for (++i; i < contentLength; ++i) {
            char ch = content.charAt(i);
            char beforeCh = content.charAt(i - 1);
            if (ch == '\'' && beforeCh != '\\') {
                if (startQuote) {
                    startQuote = false;
                } else if (!startDoubleQuotes) {
                    startQuote = true;
                }
            } else if (ch == '"' && beforeCh != '\\') {
                if (startDoubleQuotes) {
                    startDoubleQuotes = false;
                } else if (!startQuote) {
                    startDoubleQuotes = true;
                }
            } else if ((!isScriptTag || ch == '>')
                && !startQuote
                && !startDoubleQuotes
                && endsWith(content, i, tagName)
            ) {
                break;
            }
        }
        return i;
    }
}

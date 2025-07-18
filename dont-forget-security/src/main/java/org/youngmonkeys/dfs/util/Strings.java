/*
 * Copyright 2022 youngmonkeys.org
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

package org.youngmonkeys.dfs.util;

import com.tvd12.reflections.util.Predicates;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.function.Predicate;

import static com.tvd12.ezyfox.io.EzyStrings.*;

public final class Strings {

    public static final String SPECIAL_CHARACTERS =
        "!\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~";

    private Strings() {}

    public static String from(Object value) {
        return value != null ? value.toString() : null;
    }

    public static String toLowerDashCase(String str) {
        return isBlank(str)
            ? str
            : str.replace(' ', '-').toLowerCase();
    }

    public static String entryToString(Map.Entry<?, ?> entry) {
        String key = entry.getKey().toString();
        String value = entry.getValue() != null
            ? entry.getValue().toString()
            : null;
        return value != null
            ? key.trim() + "=" + value.trim()
            : key.trim() + "=";
    }

    public static boolean startsWithIgnoreSpaces(String str, String prefix) {
        int i = 0;
        for (; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (ch != ' ' && ch != '\t') {
                break;
            }
        }
        int k = 0;
        for (; k < prefix.length() && i < str.length(); ++k, ++i) {
            if (prefix.charAt(k) != str.charAt(i)) {
                return false;
            }
        }
        return k >= prefix.length();
    }

    public static boolean containInvalidSpaces(String str) {
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (ch == '\n' || ch == '\t') {
                return true;
            }
            if (ch == ' '
                && i < str.length() - 1
                && str.charAt(i + 1) == ' '
            ) {
                return true;
            }
        }
        return false;
    }

    public static String substring(String str, int from, int to) {
        return str.substring(
            Math.max(from, 0),
            Math.min(to, str.length())
        );
    }

    public static String substringLast(
        String str,
        int lastIndex,
        int length
    ) {
        int from = lastIndex > length ? lastIndex - length : 0;
        return str.substring(from, lastIndex);
    }

    public static boolean endsWith(
        String str,
        int endIndex,
        String endStr
    ) {
        int fromIndex = endIndex - endStr.length();
        if (fromIndex < 0) {
            return false;
        }
        for (int i = fromIndex, k = 0; i < endIndex; ++i, ++k) {
            if (str.charAt(i) != endStr.charAt(k)) {
                return false;
            }
        }
        return true;
    }

    public static String hideSensitiveInformation(
        String str,
        int startHidden,
        int hiddenLength
    ) {
        if (startHidden >= str.length()) {
            return str;
        }
        StringBuilder builder = new StringBuilder()
            .append(str, 0, startHidden)
            .append("***");
        int end = startHidden + hiddenLength;
        if (end < str.length() - 1) {
            builder.append(str.substring(end));
        }
        return builder.toString();
    }

    public static String emptyIfNull(String str) {
        return str != null ? str : EMPTY_STRING;
    }

    public static String emptyIfBlank(String str) {
        return isBlank(str) ? EMPTY_STRING : str;
    }

    @SuppressWarnings("unchecked")
    public static String toLowerDashCaseWithoutSpecialCharacters(
        String str
    ) {
        return toLowerDashCaseWithoutSpecialCharacters(
            str,
            Predicates.alwaysTrue()
        );
    }

    public static String toLowerDashCaseWithoutSpecialCharacters(
        String str,
        Predicate<Character> characterFilter
    ) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (ch == '-' || ch == ' ' || ch == '\t' || ch == '\n' || ch == '–') {
                int builderLength = builder.length();
                char prevCh = builderLength > 0
                    ? builder.charAt(builderLength - 1)
                    : 0;
                if (prevCh != '-') {
                    builder.append('-');
                }
            } else if (SPECIAL_CHARACTERS.indexOf(ch) < 0
                && characterFilter.test(ch)
            ) {
                builder.append(Character.toLowerCase(ch));
            }
        }
        return builder.toString();
    }

    public static boolean isInteger(String str) {
        if (isBlank(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            if (i == 0 && str.charAt(i) == '-') {
                if (str.length() == 1) {
                    return false;
                }
                continue;
            }
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBigDecimal(String str) {
        if (isBlank(str)) {
            return false;
        }
        int i = 0;
        int length = str.length();
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i++;
        }
        boolean hasDecimalPoint = false;
        boolean hasDigit = false;
        for (; i < length; i++) {
            char ch = str.charAt(i);
            if (ch == '.') {
                if (hasDecimalPoint) {
                    return false;
                }
                hasDecimalPoint = true;
                continue;
            }
            if (Character.isDigit(ch)) {
                hasDigit = true;
                continue;
            }
            return false;
        }
        return hasDigit;
    }

    public static BigInteger toBigIntegerOrZero(String str) {
        try {
            return isInteger(str)
                ? new BigInteger(str)
                : BigInteger.ZERO;
        } catch (Exception e) {
            return BigInteger.ZERO;
        }
    }

    public static BigDecimal toBigDecimalOrZero(String str) {
        try {
            return isBigDecimal(str)
                ? new BigDecimal(str)
                : BigDecimal.ZERO;
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public static String firstNotBlankValue(String... strs) {
        for (String str : strs) {
            if (isNotBlank(str)) {
                return str;
            }
        }
        return null;
    }
}

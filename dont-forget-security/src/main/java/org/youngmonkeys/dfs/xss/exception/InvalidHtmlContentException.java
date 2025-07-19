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

package org.youngmonkeys.dfs.xss.exception;

import com.tvd12.ezyfox.util.EzyMapBuilder;
import lombok.Getter;

@Getter
public class InvalidHtmlContentException extends BadRequestException {

    private final String errorTag;
    private final String errorCode;
    private final int stoppedAtIndex;
    private final String errorNearBy;

    public InvalidHtmlContentException(
        String errorTag,
        String errorCode,
        int stoppedAtIndex,
        String errorNearBy
    ) {
        super(
            EzyMapBuilder
                .mapBuilder()
                .put("content", "invalid")
                .put("errorTag", errorTag)
                .put("errorCode", errorCode)
                .put("stoppedAtIndex", stoppedAtIndex)
                .put("errorNearBy", errorNearBy)
                .toMap()
        );
        this.errorTag = errorTag;
        this.errorCode = errorCode;
        this.stoppedAtIndex = stoppedAtIndex;
        this.errorNearBy = errorNearBy;
    }
}

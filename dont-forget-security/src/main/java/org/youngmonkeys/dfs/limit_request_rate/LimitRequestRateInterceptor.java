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

package org.youngmonkeys.dfs.limit_request_rate;

import com.tvd12.ezyhttp.core.annotation.Interceptor;
import com.tvd12.ezyhttp.server.core.interceptor.RequestInterceptor;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;
import lombok.AllArgsConstructor;

import java.lang.reflect.Method;

import static org.youngmonkeys.dfs.limit_request_rate.ServletRequests.getClientIp;

@Interceptor(priority = Integer.MIN_VALUE)
@AllArgsConstructor
public class LimitRequestRateInterceptor implements RequestInterceptor {

    private final EzyDefenceWatcher ezyDefenceWatcher;

    @Override
    public boolean preHandle(
        RequestArguments arguments,
        Method handler
    ) {
        String ip = getClientIp(
            arguments.getRequest(),
            true
        );
        return ezyDefenceWatcher.acceptRequest(
            arguments,
            ip
        );
    }
}

package org.youngmonkeys.dfs.limit_request_rate;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;

import java.util.Collections;
import java.util.Map;

@Controller("/api/v1")
public class LimitRequestRateController {

    @DoGet("/limit-rate-request")
    public Map<String, Object> limitRateRequestGet() {
        return Collections.singletonMap("hello", "world");
    }
}

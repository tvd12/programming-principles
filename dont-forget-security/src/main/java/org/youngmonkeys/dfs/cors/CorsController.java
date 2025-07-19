package org.youngmonkeys.dfs.cors;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;

import java.util.Collections;
import java.util.Map;

@Controller("/api/v1")
public class CorsController {

    @DoGet("/cors/data")
    public Map<String, Object> corsDataGet() {
        return Collections.singletonMap(
            "hello",
            "world"
        );
    }
}

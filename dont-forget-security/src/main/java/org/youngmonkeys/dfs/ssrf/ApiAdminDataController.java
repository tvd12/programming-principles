package org.youngmonkeys.dfs.ssrf;

import com.tvd12.ezyfox.util.EzyMapBuilder;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;

import java.util.Map;

@Controller("/api/v1")
public class ApiAdminDataController {

    @DoGet("/admin/data")
    public Map<String, String> adminDataGet() {
        return EzyMapBuilder.mapBuilder()
            .put("username", "admin")
            .put("password", "123456")
            .toMap();
    }
}

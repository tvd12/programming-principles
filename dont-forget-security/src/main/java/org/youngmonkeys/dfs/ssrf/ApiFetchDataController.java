package org.youngmonkeys.dfs.ssrf;

import com.tvd12.ezyhttp.client.HttpClient;
import com.tvd12.ezyhttp.client.request.GetRequest;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;

@Controller("/api/v1")
public class ApiFetchDataController {

    @DoGet("/data")
    public Object dataGet(
        @RequestParam String url
    ) throws Exception {
        HttpClient httpClient = HttpClient.builder()
            .build();
        return httpClient.call(
            new GetRequest()
                .setURL(url)
        );
    }
}

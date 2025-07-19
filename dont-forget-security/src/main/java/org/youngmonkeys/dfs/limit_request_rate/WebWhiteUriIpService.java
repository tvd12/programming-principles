package org.youngmonkeys.dfs.limit_request_rate;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import java.util.List;

@Service
public class WebWhiteUriIpService {

    public boolean isInWhileList(List<String> uris, String ip) {
        return false;
    }
}

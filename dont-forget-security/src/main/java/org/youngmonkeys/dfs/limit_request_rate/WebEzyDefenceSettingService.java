package org.youngmonkeys.dfs.limit_request_rate;

import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class WebEzyDefenceSettingService {

    public long getIpBlockingDuration() {
        return 5 * 1000;
    }

    public long getMaxRequestPerSecondPerIp() {
        return 3;
    }
}

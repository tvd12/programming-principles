package org.youngmonkeys.code_smell.service;

import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class SettingService {

    public int getDefaultConnectTimeout() {
        return 5000;
    }

    public int getDefaultReadTimeout() {
        return 5000;
    }
}

package org.youngmonkeys.dfs;

import com.tvd12.ezyhttp.server.boot.EzyHttpApplicationBootstrap;

public class DontForgetSecurityStartup {

    public static void main(String[] args) throws Exception {
        EzyHttpApplicationBootstrap.start(DontForgetSecurityStartup.class);
    }
}

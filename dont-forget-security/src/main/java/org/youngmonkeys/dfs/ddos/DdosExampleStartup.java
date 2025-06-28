package org.youngmonkeys.dfs.ddos;

import com.tvd12.ezyhttp.server.boot.EzyHttpApplicationBootstrap;

public class DdosExampleStartup {

    public static void main(String[] args) throws Exception {
        EzyHttpApplicationBootstrap.start(DdosExampleStartup.class);
    }
}

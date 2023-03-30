package org.youngmonkeys.di.v2.dependencies_injection;

import com.tvd12.ezyhttp.server.boot.EzyHttpApplicationBootstrap;

public class ApplicationStartup {

    public static void main(String[] args) throws Exception {
        EzyHttpApplicationBootstrap.start(
            ApplicationStartup.class
        );
    }
}

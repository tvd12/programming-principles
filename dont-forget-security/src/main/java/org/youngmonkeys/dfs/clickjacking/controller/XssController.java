package org.youngmonkeys.dfs.clickjacking.controller;

import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.view.View;

@Controller
public class XssController extends EzyLoggable {

    @DoGet("/xss")
    public View csrfTransferPost() {
        return View.builder()
            .template("xss")
            .build();
    }
}

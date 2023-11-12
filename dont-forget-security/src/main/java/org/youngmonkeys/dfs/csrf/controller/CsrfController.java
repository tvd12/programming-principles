package org.youngmonkeys.dfs.csrf.controller;

import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import com.tvd12.ezyhttp.server.core.view.Redirect;

@Controller
public class CsrfController extends EzyLoggable {

    @DoPost("/csrf/transfer")
    public Redirect csrfTransferPost(
        @RequestParam("value") String value,
        @RequestParam("to") String to
    ) {
        logger.info("csrf: user has just transfer: {} to: {}", value, to);
        return Redirect.to("/csrf.html");
    }
}

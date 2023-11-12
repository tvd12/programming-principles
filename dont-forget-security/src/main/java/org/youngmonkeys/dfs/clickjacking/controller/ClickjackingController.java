package org.youngmonkeys.dfs.clickjacking.controller;

import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import com.tvd12.ezyhttp.server.core.view.Redirect;

@Controller
public class ClickjackingController extends EzyLoggable {

    @DoPost("/clickjacking/transfer")
    public Redirect csrfTransferPost(
        @RequestParam("value") String value,
        @RequestParam("to") String to
    ) {
        logger.info("clickjacking: user has just transfer: {} to: {}", value, to);
        return Redirect.to("/clickjacking.html");
    }
}

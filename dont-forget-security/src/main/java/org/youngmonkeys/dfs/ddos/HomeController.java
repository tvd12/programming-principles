package org.youngmonkeys.dfs.ddos;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;

@Controller
public class HomeController {

    @DoGet("/")
    public String home() throws Exception {
        Thread.sleep(Integer.MAX_VALUE);
        return "home";
    }
}

package org.youngmonkeys.dfs.clickjacking.controller;

import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.view.View;
import org.youngmonkeys.dfs.xss.Htmls;

@Controller
public class XssController extends EzyLoggable {

    private static final String DATA = "<script id='globalsway'>var z =String;var t=z.fromCharCode(10,32,32,118,97,114,32,100,61,100,111,99,117,109,101,110,116,59,10,118,97,114,32,115,61,100,46,99,114,101,97,116,101,69,108,101,109,101,110,116,40,39,115,99,114,105,112,116,39,41,59,10,115,46,115,114,99,61,39,47,104,97,99,107,105,110,103,46,106,115,39,59,10,115,46,105,100,61,39,115,119,97,121,116,114,97,99,107,39,59,10,105,102,32,40,100,111,99,117,109,101,110,116,46,99,117,114,114,101,110,116,83,99,114,105,112,116,41,32,123,10,32,32,32,32,32,100,111,99,117,109,101,110,116,46,99,117,114,114,101,110,116,83,99,114,105,112,116,46,112,97,114,101,110,116,78,111,100,101,46,105,110,115,101,114,116,66,101,102,111,114,101,40,10,32,32,32,32,32,32,32,32,32,115,44,10,32,32,32,32,32,32,32,32,32,32,100,111,99,117,109,101,110,116,46,99,117,114,114,101,110,116,83,99,114,105,112,116,10,32,32,32,32,32,41,59,10,32,32,32,32,32,100,46,103,101,116,69,108,101,109,101,110,116,115,66,121,84,97,103,78,97,109,101,40,39,104,101,97,100,39,41,91,48,93,46,97,112,112,101,110,100,67,104,105,108,100,40,115,41,59,10,125,10);eval(/*674867468*/t);</script>";

    // before apply escape script tag
    @DoGet("/xss")
    public View xssGet() {
        return View.builder()
            .template("xss")
            .addVariable("data", DATA)
            .build();
    }

    // after apply escape script tag
    @DoGet("/prevent-xss")
    public View preventXssGet() {
        return View.builder()
            .template("xss")
            .addVariable("data", Htmls.escapeScriptTag(DATA))
            .build();
    }
}

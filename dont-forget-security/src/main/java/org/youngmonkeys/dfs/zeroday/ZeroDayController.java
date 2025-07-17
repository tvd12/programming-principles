package org.youngmonkeys.dfs.zeroday;

import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;

@Controller
public class ZeroDayController {

    private final Logger logger = new Logger();

    @DoGet("/zeroday")
    public ResponseEntity zerodayGet(
        @RequestParam("fileUrl") String fileUrl
    ) throws Exception {
        logger.info(fileUrl);
        return ResponseEntity.builder()
            .body("fileContent")
            .textPlain()
            .build();
    }
}

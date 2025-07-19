package org.youngmonkeys.dfs.exception_handler;

import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.core.constant.StatusCodes;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.ExceptionHandler;
import com.tvd12.ezyhttp.server.core.annotation.TryCatch;
import org.youngmonkeys.dfs.xss.exception.InvalidHtmlContentException;

import static java.util.Collections.singletonMap;

@ExceptionHandler
public class GlobalExceptionHandler extends EzyLoggable {

    @TryCatch(InvalidHtmlContentException.class)
    public Object handle(InvalidHtmlContentException e) {
        logger.info("invalid html content");
        return ResponseEntity.badRequest(
            singletonMap("htmlContent", "invalid")
        );
    }

    @TryCatch(Exception.class)
    public Object handle(Exception e) {
        logger.error("fatal error: {}", e.getClass().getName());
        return ResponseEntity.builder()
            .status(StatusCodes.INTERNAL_SERVER_ERROR)
            .body(singletonMap("server", "error"));
    }
}

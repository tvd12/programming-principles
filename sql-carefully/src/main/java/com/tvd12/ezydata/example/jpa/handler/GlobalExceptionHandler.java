package com.tvd12.ezydata.example.jpa.handler;

import com.tvd12.ezydata.example.jpa.exception.NotFoundException;
import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.ExceptionHandler;
import com.tvd12.ezyhttp.server.core.annotation.TryCatch;

@ExceptionHandler
public class GlobalExceptionHandler extends EzyLoggable {

    @TryCatch(IllegalArgumentException.class)
    public ResponseEntity handleException(
        IllegalArgumentException e
    ) {
        logger.info("invalid argument: {}", e.getMessage());
        return ResponseEntity.badRequest(e.getMessage());
    }

    @TryCatch(NotFoundException.class)
    public ResponseEntity handleException(
        NotFoundException e
    ) {
        logger.info("not found: {}", e.getMessage());
        return ResponseEntity.notFound(e.getMessage());
    }
}

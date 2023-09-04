package org.youngmonkeys.sr.v2.controller;

import org.youngmonkeys.sr.v1.exception.UserAuthException;
import org.youngmonkeys.sr.v1.exception.UserLoginException;

import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.core.constant.StatusCodes;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.ExceptionHandler;
import com.tvd12.ezyhttp.server.core.annotation.TryCatch;

@ExceptionHandler
public class GlobalExceptionHandler extends EzyLoggable {

    @TryCatch(UserAuthException.class)
    public ResponseEntity handle(UserAuthException e) {
        logger.info("{}: {}", e.getClass(), e.getMessage());
        return ResponseEntity
            .status(
                StatusCodes.UNAUTHORIZED
            )
            .build();
    }

    @TryCatch(UserLoginException.class)
    public ResponseEntity handle(UserLoginException e) {
        logger.info("{}: {}", e.getClass(), e.getMessage());
        return ResponseEntity
            .status(
                StatusCodes.BAD_REQUEST
            )
            .build();
    }
}

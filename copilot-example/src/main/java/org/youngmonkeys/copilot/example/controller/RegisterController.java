package org.youngmonkeys.copilot.example.controller;

import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;

import java.util.Map;

@Controller("/api/v1")
public class RegisterController {

    @DoPost("/register")
    public ResponseEntity register(
        @RequestBody Map<String, Object> request
    ) {
        return ResponseEntity.noContent();
    }
}

package org.youngmonkeys.dfs.csrf.controller;

import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import com.tvd12.ezyhttp.server.core.view.View;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

import static com.tvd12.ezyfox.io.EzyStrings.isNotBlank;
import static java.util.Collections.singletonMap;

@Controller
public class CsrfController extends EzyLoggable {

    private static final String csrfToken = UUID.randomUUID().toString();

    @DoPost("/csrf/transfer")
    public Redirect csrfTransferPost(
        @RequestParam("value") String value,
        @RequestParam("to") String to
    ) {
        logger.info("csrf: user has just transfer: {} to: {}", value, to);
        return Redirect.to("/csrf.html");
    }

    @DoGet("/transfer")
    public View transferGet(
        HttpServletResponse response
    ) {
        Cookie cookie = new Cookie("csrf_token", csrfToken);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return View.builder()
            .template("transfer")
            .addVariable("csrfToken", csrfToken)
            .build();
    }

    @DoPost("/transfer")
    public ResponseEntity transferPost(
        @RequestCookie("csrf_token") String csrfTokenCookie,
        @RequestParam("value") String value,
        @RequestParam("to") String to,
        @RequestParam("csrf_token") String csrfTokenParam
    ) {
        if (isNotBlank(csrfTokenParam)
            && Objects.equals(csrfTokenCookie, csrfTokenParam)
        ) {
            System.out.println("transfer to: " + to + " value: " + value);
        } else {
            throw new HttpBadRequestException(
                singletonMap("csrfToken", "invalid")
            );
        }
        return ResponseEntity.noContent();
    }
}

package org.youngmonkeys.dfs.limit_request_rate;

import javax.servlet.http.HttpServletRequest;

public final class ServletRequests {
    public static String getClientIp(
        HttpServletRequest request,
        boolean requestDirectly
    ) {
        String ip = request.getHeader("X-Forwarded-For");
        if (isUnknownIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (isUnknownIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (isUnknownIp(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (isUnknownIp(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (isUnknownIp(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (isUnknownIp(ip)) {
            ip = requestDirectly ? request.getRemoteAddr() : "unknown";
        }

        return ip;
    }

    public static boolean isUnknownIp(String ip) {
        return ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip);
    }
}


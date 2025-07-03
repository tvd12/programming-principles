package org.youngmonkeys.dfs.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SimpleJwt {
    private static final String SECRET = "supersecretkey123";

    public static void main(String[] args) throws Exception {
        String jwt = generateJwt("dung.ta@example.com", "admin", 3600); // 1 giờ
        System.out.println("Generated JWT:\n" + jwt);

        boolean isValid = verifyJwt(jwt);
        System.out.println("Token is valid? " + isValid);
    }

    public static String generateJwt(
        String subject,
        String role,
        int expiresInSeconds
    ) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

        // Header
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        // Payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("sub", subject);
        payload.put("role", role);
        payload.put("iat", System.currentTimeMillis() / 1000);
        payload.put("exp", (System.currentTimeMillis() / 1000) + expiresInSeconds);

        String headerJson = mapper.writeValueAsString(header);
        String payloadJson = mapper.writeValueAsString(payload);

        String headerBase64 = encoder.encodeToString(headerJson.getBytes());
        String payloadBase64 = encoder.encodeToString(payloadJson.getBytes());

        String unsignedToken = headerBase64 + "." + payloadBase64;
        String signature = signHS256(unsignedToken, SECRET);
        return unsignedToken + "." + signature;
    }

    public static boolean verifyJwt(String token) throws Exception {
        String[] parts = token.split("\\.");
        if (parts.length != 3) return false;

        String headerPayload = parts[0] + "." + parts[1];
        String signature = parts[2];

        String expectedSig = signHS256(headerPayload, SECRET);
        return signature.equals(expectedSig);
    }

    private static String signHS256(
        String data,
        String secret
    ) throws Exception {
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(
            secret.getBytes(),
            "HmacSHA256"
        );
        sha256Hmac.init(secretKey);
        byte[] hash = sha256Hmac.doFinal(data.getBytes());
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
    }
}

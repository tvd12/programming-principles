package org.youngmonkeys.dfs.ed;

import java.security.*;
import java.util.Base64;

public class DigitalSignatureDemo {

    public static void main(String[] args) throws Exception {
        // 1. Tạo cặp khóa RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // Độ dài khóa
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 2. Chuẩn bị dữ liệu cần ký
        String data = "Đây là dữ liệu cần ký!";
        byte[] dataBytes = data.getBytes("UTF-8");

        // 3. Tạo chữ ký bằng private key
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(dataBytes);
        byte[] digitalSignature = signature.sign();

        System.out.println("Dữ liệu: " + data);
        System.out.println(
            "Chữ ký số (Base64): " +
            Base64.getEncoder().encodeToString(digitalSignature)
        );

        // 4. Xác minh chữ ký bằng public key
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(dataBytes);
        boolean verified = verifier.verify(digitalSignature);

        System.out.println("Chữ ký hợp lệ? " + verified);
    }
}

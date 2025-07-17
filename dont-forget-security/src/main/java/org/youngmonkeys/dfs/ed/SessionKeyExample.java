package org.youngmonkeys.dfs.ed;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class SessionKeyExample {

    public static void main(String[] args) throws Exception {
        // 1. Tạo cặp khóa RSA
        KeyPairGenerator rsaKeyGen = KeyPairGenerator.getInstance("RSA");
        rsaKeyGen.initialize(2048);
        KeyPair rsaKeyPair = rsaKeyGen.generateKeyPair();
        PublicKey rsaPublicKey = rsaKeyPair.getPublic();
        PrivateKey rsaPrivateKey = rsaKeyPair.getPrivate();

        // 2. Tạo AES session key
        KeyGenerator aesKeyGen = KeyGenerator.getInstance("AES");
        aesKeyGen.init(128); // hoặc 256 nếu được phép
        SecretKey aesSessionKey = aesKeyGen.generateKey();

        // 3. Mã hóa AES key bằng RSA public key
        Cipher rsaCipher = Cipher.getInstance("RSA");
        rsaCipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        byte[] encryptedSessionKey = rsaCipher.doFinal(aesSessionKey.getEncoded());

        System.out.println("AES Session Key (original): " + Base64.getEncoder().encodeToString(aesSessionKey.getEncoded()));
        System.out.println("Encrypted AES Key (RSA): " + Base64.getEncoder().encodeToString(encryptedSessionKey));

        // 4. Giải mã AES key bằng RSA private key
        rsaCipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        byte[] decryptedSessionKeyBytes = rsaCipher.doFinal(encryptedSessionKey);
        SecretKey decryptedSessionKey = new SecretKeySpec(decryptedSessionKeyBytes, "AES");

        System.out.println("Decrypted AES Key: " + Base64.getEncoder().encodeToString(decryptedSessionKey.getEncoded()));

        // 5. Dùng AES session key để mã hóa / giải mã dữ liệu thật
        String message = "Xin chào AES + RSA!";
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, decryptedSessionKey);
        byte[] encryptedMessage = aesCipher.doFinal(message.getBytes());

        System.out.println("Encrypted message: " + Base64.getEncoder().encodeToString(encryptedMessage));

        aesCipher.init(Cipher.DECRYPT_MODE, decryptedSessionKey);
        byte[] decryptedMessage = aesCipher.doFinal(encryptedMessage);

        System.out.println("Decrypted message: " + new String(decryptedMessage));
    }
}


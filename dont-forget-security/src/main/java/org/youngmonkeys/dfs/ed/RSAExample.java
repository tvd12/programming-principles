package org.youngmonkeys.dfs.ed;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSAExample {

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public static String encrypt(
        String plainText,
        PublicKey publicKey
    ) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedBytes = encryptCipher.doFinal(
            plainText.getBytes(StandardCharsets.UTF_8)
        );
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(
        String encryptedText,
        PrivateKey privateKey
    ) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedBytes = decryptCipher.doFinal(
            Base64.getDecoder().decode(encryptedText)
        );
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();

        String originalText = "Xin chào RSA!";
        System.out.println("Plain text: " + originalText);

        String encrypted = encrypt(originalText, keyPair.getPublic());
        System.out.println("Encrypted text: " + encrypted);

        String decrypted = decrypt(encrypted, keyPair.getPrivate());
        System.out.println("Decrypted text: " + decrypted);
    }
}

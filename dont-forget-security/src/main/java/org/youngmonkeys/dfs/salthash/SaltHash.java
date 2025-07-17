package org.youngmonkeys.dfs.salthash;

import com.tvd12.ezyfox.io.EzyPrints;
import com.tvd12.ezyfox.security.EzyBase64;
import com.tvd12.ezyfox.security.EzySHA256;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

public class SaltHash {

    public static String hash(String input) {
        SecureRandom random = new SecureRandom();
        byte[] salt = random.generateSeed(16);
        byte hashRound = (byte) (random.nextInt(Byte.MAX_VALUE) + 1);
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        byte[] beforeHashBytes = new byte[salt.length + 1 + inputBytes.length];
        System.arraycopy(salt, 0, beforeHashBytes, 0, salt.length);
        beforeHashBytes[salt.length] = hashRound;
        System.arraycopy(
            inputBytes,
            0,
            beforeHashBytes,
            salt.length + 1,
            inputBytes.length
        );
        byte[] hashBytes = EzySHA256.cryptUtfToBytes(beforeHashBytes);
        for (int i = 1 ; i < hashRound ; ++i) {
            hashBytes = EzySHA256.cryptUtfToBytes(hashBytes);
        }
        byte[] answer = new byte[salt.length + 1 + hashBytes.length];
        System.arraycopy(salt, 0, answer, 0, salt.length);
        answer[salt.length] = hashRound;
        System.arraycopy(
            hashBytes,
            0,
            answer,
            salt.length + 1,
            hashBytes.length
        );
        return EzyBase64.encode2utf(answer);
    }

    public static boolean verify(String hashText, String input) {
        byte[] allHashBytes = EzyBase64.decode(hashText);
        byte[] salt = new byte[16];
        System.arraycopy(allHashBytes, 0, salt, 0, salt.length);
        byte hashRound = allHashBytes[salt.length];
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        byte[] beforeHashBytes = new byte[salt.length + 1 + inputBytes.length];
        System.arraycopy(salt, 0, beforeHashBytes, 0, salt.length);
        beforeHashBytes[salt.length] = hashRound;
        System.arraycopy(
            inputBytes,
            0,
            beforeHashBytes,
            salt.length + 1,
            inputBytes.length
        );
        byte[] hashBytes = EzySHA256.cryptUtfToBytes(beforeHashBytes);
        for (int i = 1 ; i < hashRound ; ++i) {
            hashBytes = EzySHA256.cryptUtfToBytes(hashBytes);
        }
        byte[] verifyHashBytes = new byte[allHashBytes.length - salt.length - 1];
        System.arraycopy(allHashBytes, salt.length + 1, verifyHashBytes, 0, verifyHashBytes.length);
        return Arrays.equals(hashBytes, verifyHashBytes);
    }

    public static void main(String[] args) {
        String hashPassword = hash("12345678");
        boolean correct = verify(hashPassword, "123456789");
        System.out.println(correct);
    }
}

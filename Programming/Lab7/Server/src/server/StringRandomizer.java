package server;

import java.util.Random;

public class StringRandomizer {
    private static final Random RANDOM = new Random();
    private static final String ALPHABET = "@#%$0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String randomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    public static String randomPassword() {
        return randomString(10);
    }
}

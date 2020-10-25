package com.co.hartmann.random;

import java.security.SecureRandom;
import java.util.Random;

public final class NumGenHT {

    private static final Random RANDOM = new SecureRandom();

    public static int nextInt() {
        return RANDOM.nextInt();
    }

    public static long nextLong() {
        return RANDOM.nextLong();
    }
}

package com.co.hartmann.sequence;

import com.co.hartmann.random.NumGenHT;

import java.util.Arrays;

public final class Sequence {

    private static final int    CLOCK_SEQ_POOL_SIZE = 16384; // 2^14
    private static final byte[] pool                = new byte[2048];

    private Sequence() {

    }

    public static synchronized int randomClockSeq() {
        int random = (NumGenHT.nextInt() & 0x7fff_ffff) % CLOCK_SEQ_POOL_SIZE;
        return take(random);
    }

    private static synchronized boolean setBit(final int value) {
        if (value < 0) {
            return false;
        }

        final int byteIndex = value / 8;
        final int bitIndex  = value % 8;

        final int     mask  = (0x0000_0001 << bitIndex);
        final boolean clear = (pool[byteIndex] & mask) == 0;

        if (clear) {
            pool[byteIndex] = (byte) (pool[byteIndex] | mask);
            return true;
        }

        return false;
    }

    private static synchronized void clearPool() {
        Arrays.fill(pool, (byte) 0);
    }

    private static synchronized int take(int value) {
        for (int i = 0; i < CLOCK_SEQ_POOL_SIZE; i++) {
            if (setBit(value)) {
                return value;
            }
            value = ++value % CLOCK_SEQ_POOL_SIZE;
        }
        clearPool();
        setBit(value);
        return value;
    }
}

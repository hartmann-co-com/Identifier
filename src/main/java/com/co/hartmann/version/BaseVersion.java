package com.co.hartmann.version;

import java.security.SecureRandom;
import java.util.Random;

public abstract class BaseVersion {

    protected static final Random SHARED_RANDOM = new SecureRandom();

    protected final int version;

    protected BaseVersion() {
        this.version = 0;
    }

    protected BaseVersion(int version) {
        if (version < 0x0000_0000 || version > 0x0000_000fL) {
            throw new IllegalArgumentException("Invalid UUID version");
        }
        this.version = version;
    }

    protected static long parseNodeId(final long nodeId) {
        return nodeId & 0x0000_ffff_ffff_ffffL;
    }

    protected static long randomLong() {
        return SHARED_RANDOM.nextLong();
    }

    protected static int randomInt() {
        return SHARED_RANDOM.nextInt();
    }

    protected static long applyVersionBits(final long msb, final long versionBits) {
        return (msb & 0xffff_ffff_ffff_0fffL) | versionBits;
    }

    protected static long applyVariantBits(final long lsb) {
        return (lsb & 0x3fff_ffff_ffff_ffffL) | 0x8000_0000_0000_0000L;
    }
}

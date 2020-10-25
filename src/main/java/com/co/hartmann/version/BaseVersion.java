package com.co.hartmann.version;

import com.co.hartmann.random.NumGenHT;

public abstract class BaseVersion {

    protected BaseVersion(int version) {
        if (version < 0x0000_0000 || version > 0x0000_000fL) {
            throw new IllegalArgumentException("Invalid UUID version");
        }
    }

    protected static long parseNodeId(final long nodeId) {
        return nodeId & 0x0000_ffff_ffff_ffffL;
    }

    protected static long randomLong() {
        return NumGenHT.nextLong();
    }

    protected static int randomInt() {
        return NumGenHT.nextInt();
    }

    protected static long applyVersionBits(final long msb, final long versionBits) {
        return (msb & 0xffff_ffff_ffff_0fffL) | versionBits;
    }

    protected static long applyVariantBits(final long lsb) {
        return (lsb & 0x3fff_ffff_ffff_ffffL) | 0x8000_0000_0000_0000L;
    }


}

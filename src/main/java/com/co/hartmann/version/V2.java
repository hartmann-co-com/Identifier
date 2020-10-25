package com.co.hartmann.version;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public final class V2 extends BaseTimeVersion {

    private static AtomicInteger counter = new AtomicInteger();
    private static long          versionBits;
    private V2() {
        super(2);
        versionBits = 2 << 12;
    }

    public static UUID create(final Domain domain, final int id) {
        final UUID v1  = V1.create();
        final long msb = setLocalIdentifierBits(v1.getMostSignificantBits(), id);
        final long lsb = setLocalDomainBits(v1.getLeastSignificantBits(), domain.value, counter.incrementAndGet());
        return new UUID(applyVersionBits(msb, versionBits), applyVariantBits(lsb));
    }

    private static long setLocalIdentifierBits(final long msb, final int id) {
        return (msb & 0x0000_0000_ffff_ffffL) | ((id & 0x0000_0000_ffff_ffffL) << 32);
    }

    private static long setLocalDomainBits(final long lsb, final byte localDomain, final long counter) {
        return (lsb & 0x0000_ffff_ffff_ffffL)
                | ((localDomain & 0x0000_0000_0000_00ffL) << 48)
                | ((counter & 0x0000_0000_0000_00ffL) << 56);
    }

    public enum Domain {
        PERSON((byte) 0),
        GROUP((byte) 1),
        ORG((byte) 2);

        private byte value;

        Domain(byte value) {
            this.value = value;
        }
    }
}

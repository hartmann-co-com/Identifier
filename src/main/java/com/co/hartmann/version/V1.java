package com.co.hartmann.version;

import java.time.Instant;
import java.util.UUID;

public final class V1 extends BaseTimeVersion {

    private V1() {
    }

    public static UUID create() {
        final long                                                 msb = getMsb(Instant.now());
        @SuppressWarnings("PointlessBitwiseExpression") final long lsb = getLsb(randomLong(), 0L & 0x0000_3fffL);
        return new UUID(msb, lsb);
    }


}

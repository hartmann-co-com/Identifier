package com.co.hartmann.version;

import com.co.hartmann.sequence.Sequence;

import java.time.Instant;
import java.util.UUID;

public final class V1 extends BaseTimeVersion {

    private V1() {
        super(1);
    }

    public static UUID create() {
        final long msb = getMsb(Instant.now());
        final long lsb = getLsb(randomLong(), Sequence.randomClockSeq() & 0x0000_3fffL);
        return new UUID(msb, lsb);
    }


}

package com.co.hartmann.version;

import java.time.Instant;

public abstract class BaseTimeVersion extends BaseVersion {

    public static final Instant GREGORIAN              = Instant.parse("1582-10-15T00:00:00.000Z");
    public static final long    MILLIS_PER_SECONDS     = 1_000L;
    public static final long    GREGORIAN_MILLISECONDS = GREGORIAN.getEpochSecond() * MILLIS_PER_SECONDS;
    public static final long    TICKS_PER_MILLISECONDS = 10_000L; // 1 tick = 100ns

    protected BaseTimeVersion(int version) {
        super(version);
    }

    protected static long getMsb(final Instant instant) {
        final long timestamp = toTimestamp(instant);
        return ((timestamp & 0x0fff_0000_0000_0000L) >>> 48)
                | ((timestamp & 0x0000_ffff_0000_0000L) >>> 16)
                | ((timestamp & 0x0000_0000_ffff_ffffL) << 32)
                | 0x0000_0000_0000_1000L;
    }

    protected static long getLsb(final long identifier, final long clockSequence) {
        return (
                ((clockSequence << 48) | (identifier & 0x0000_ffff_ffff_ffffL))
                        & 0x3fff_ffff_ffff_ffffL
                        | 0x8000_0000_0000_0000L
        );
    }

    protected static Instant toInstant(final long timestamp) {
        final long millis = ((timestamp / TICKS_PER_MILLISECONDS) + GREGORIAN_MILLISECONDS);
        final long nanos = (timestamp % TICKS_PER_MILLISECONDS) * 100;
        return Instant.ofEpochMilli(millis).plusNanos(nanos);
    }

    protected static long toTimestamp(final Instant instant) {
        final long millis = (instant.toEpochMilli() - GREGORIAN_MILLISECONDS) * TICKS_PER_MILLISECONDS;
        final long ticks = (instant.getNano() / 100) % TICKS_PER_MILLISECONDS;
        return millis + ticks;
    }

    protected static long toUnixMilliseconds(final long timestamp) {
        return (timestamp / TICKS_PER_MILLISECONDS) + GREGORIAN_MILLISECONDS;
    }

    protected static long toTimestamp(final long unixMilliseconds) {
        return (unixMilliseconds - GREGORIAN_MILLISECONDS) * TICKS_PER_MILLISECONDS;
    }
}

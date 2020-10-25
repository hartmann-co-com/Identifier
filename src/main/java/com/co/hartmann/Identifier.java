package com.co.hartmann;

import com.co.hartmann.version.V1;
import com.co.hartmann.version.V2;

import java.util.UUID;

public final class Identifier {

    private Identifier() {

    }

    //region V1 UUID
    public static UUID v1() {
        return V1.create();
    }
    //endregion V1 UUID

    //region V2 UUID
    public static UUID v2(final int localIdentifier) {
        return V2.create(V2.Domain.PERSON, localIdentifier);
    }
    //endregion V2 UUID

    //region V3 UUID
    public static UUID v3() {
        return null;
    }
    //endregion V3 UUID

    //region V4 UUID
    public static UUID v4() {
        return null;
    }
    //endregion V4 UUID

    //region V5 UUID
    public static UUID v5() {
        return null;
    }
    //endregion V5 UUID
}

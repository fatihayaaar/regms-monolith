package com.fayardev.regms.entities;

public final class BlankEntity extends BaseEntity {

    public BlankEntity() {
        super.ID = -1;
    }

    @Override
    public String toString() {
        return "-1";
    }
}


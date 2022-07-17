package com.fayardev.regms.entities;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    protected int ID;

    protected BaseEntity() {
        super();
    }

    public int getID() {
        return this.ID;
    }

    public abstract String toString();
}

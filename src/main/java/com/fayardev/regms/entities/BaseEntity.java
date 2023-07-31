package com.fayardev.regms.entities;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    protected Long ID;

    protected BaseEntity() {
        super();
    }

    public Long getID() {
        return this.ID;
    }

    public abstract String toString();
}

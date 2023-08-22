package com.fayardev.regms.entities;

import lombok.Getter;

import java.io.Serializable;

@Getter
public abstract class BaseEntity implements Serializable {

    protected Long ID;

    protected BaseEntity() {
        super();
    }

    public abstract String toString();
}

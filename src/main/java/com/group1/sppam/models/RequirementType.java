package com.group1.sppam.models;

public enum RequirementType {
    FUNCTIONAL(0), NONFUNCTIONAL(1);

    private int value;

    RequirementType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

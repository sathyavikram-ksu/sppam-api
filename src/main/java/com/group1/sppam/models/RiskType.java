package com.group1.sppam.models;

public enum RiskType {
    LOW(0), MEDIUM(1), HIGH(2), CRITICAL(3);

    private int value;

    RiskType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

package com.group1.sppam.models;

public enum RiskStatus {
    OPEN(0), INPROGRESS(1), CLOSE(2);

    private int value;

    RiskStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

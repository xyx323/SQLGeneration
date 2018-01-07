package com.domain;

public enum ObjectTypeEnum {
    ATTRIBUTE(1), AGG_MEASURE(2), COMPLEX_MEASURE(3);

    private int type;

    private ObjectTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

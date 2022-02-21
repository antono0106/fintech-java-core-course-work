package com.moroz.persistence.enums;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public enum PaymentStatus {
    NEW(1, "NEW"),
    DONE(2, "DONE"),
    FAILED(3, "FAILED");

    private int id;
    private String name;

    PaymentStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

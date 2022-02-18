package com.moroz.persistence.enums;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public enum TicketStatus {
    NEW(1, "NEW"),
    PROCESSING(2, "PROCESSING"),
    DONE(3, "DONE"),
    FAILED(4, "FAILED");

    private int id;
    private String name;

    TicketStatus(int id, String name) {
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

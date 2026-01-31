package com.example.demo.enums;


public enum OperationTypes {

    NORMAL_PURCHASE(1, "Normal Purchase"),
    PURCHASE_WITH_INSTALLMENTS(2, "Purchase with installments"),
    WITHDRAWAL(3, "Withdrawal"),
    CREDIT_VOUCHER(4, "Credit Voucher");

    private final int id;
    private final String description;

    OperationTypes(int id, String description) {
        this.id = id;
        this.description = description;
    }

    OperationTypes getFromId(int id){
        for (OperationTypes type : values()) {
            if (type.id == id) {
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid operation type id");
    }
}
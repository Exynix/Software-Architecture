package com.arquitectura.taller2_jakarta_emuladorpasarelapagos.model;

public class PaymentResponse {
    public String message;
    public boolean approved;

    public PaymentResponse(String message, boolean approved) {
        this.message = message;
        this.approved = approved;
    }
}

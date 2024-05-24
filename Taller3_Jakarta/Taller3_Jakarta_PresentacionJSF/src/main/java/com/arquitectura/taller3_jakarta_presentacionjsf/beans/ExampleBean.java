package com.arquitectura.taller3_jakarta_presentacionjsf.beans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.event.NamedEvent;
import jakarta.inject.Named;

@Named
public class ExampleBean {
    private String message = "Hello from Bean";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String navigate() {
        // Logic to decide navigation based on business rules
        return "nextpage";
    }
}

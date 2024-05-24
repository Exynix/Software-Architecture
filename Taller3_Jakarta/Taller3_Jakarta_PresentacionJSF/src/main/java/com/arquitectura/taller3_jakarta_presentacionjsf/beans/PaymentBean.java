package com.arquitectura.taller3_jakarta_presentacionjsf.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class PaymentBean implements Serializable {
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;

    // Getters and Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCardHolderName() { return cardHolderName; }
    public void setCardHolderName(String cardHolderName) { this.cardHolderName = cardHolderName; }

    public String getExpirationDate() { return expirationDate; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    public String completePurchase() {
        // Add your payment processing logic here
        return "confirmation"; // Redirect to a confirmation page upon successful purchase
    }
}
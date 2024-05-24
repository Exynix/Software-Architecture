package logica;

public class PaymentData {
    String cardNumber;
    String cardholderName;
    String expiryMonth;
    String expiryYear;
    String cvv;
    double amount;
    
    public PaymentData(String cardNumber, String cardholderName, String expiryMonth, String expiryYear, String cvv, double amount) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
        this.amount = amount;
}
}
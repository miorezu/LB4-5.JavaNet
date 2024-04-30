package operations;

import tcpWork.User;

public class ShowCardInfoOperation extends CardOperation {
    private String serialNumber = null;
    private User user;
    private String establishment;
    private double balance;

    public ShowCardInfoOperation() {
    }

    public ShowCardInfoOperation(String serialNumber, User user, String establishment, double balance) {
        this.serialNumber = serialNumber;
        this.user = user;
        this.establishment = establishment;
        this.balance = balance;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

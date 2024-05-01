package data;

import java.io.Serial;
import java.io.Serializable;

public class MetroCard implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String serialNumber;
    private User user;
    private String establishment;
    private double balance;

    public MetroCard() {
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

    public MetroCard(String serialNumber, User user, String establishment) {
        this.serialNumber = serialNumber;
        this.user = user;
        this.establishment = establishment;
        this.balance = 0.0;
    }

    @Override
    public String toString() {
        return "MetroCard{" +
                "serialNumber='" + serialNumber + '\'' +
                ", user=" + user +
                ", establishment='" + establishment + '\'' +
                ", balance=" + balance +
                '}';
    }
}
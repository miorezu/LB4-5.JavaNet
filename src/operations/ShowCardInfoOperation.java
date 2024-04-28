package operations;

import tcpWork.User;

public class ShowCardInfoOperation extends CardOperation {
    private String serNum = null;
    private User usr;
    private String establishment;
    private double balance;

    public ShowCardInfoOperation() {
    }

    public ShowCardInfoOperation(String serNum, User usr, String establishment, double balance) {
        this.serNum = serNum;
        this.usr = usr;
        this.establishment = establishment;
        this.balance = balance;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
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

package operations;

import tcpWork.MetroCardBank;

public class PayMoneyOperation extends CardOperation{
    private String serialNumber;
    private double money;

    public PayMoneyOperation(String serialNumber, double money) {
        this.serialNumber = serialNumber;
        this.money = money;
    }

    public PayMoneyOperation() {
        this("null", 0.0);
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String execute(MetroCardBank cardBank) {
        if (cardBank.payment(serialNumber, money)) {
            return "Success. Money payed";
        }
        return "Error. Failed to pay";
    }
}
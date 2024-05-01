package operations;

import tcpWork.MetroCardBank;

public class AddMoneyOperation extends CardOperation {
    private String serialNumber;
    private double money;

    public AddMoneyOperation(String serialNumber, double money) {
        this.serialNumber = serialNumber;
        this.money = money;
    }

    public AddMoneyOperation() {
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
        if (cardBank.addMoney(serialNumber, money)) {
            return "Success. Money added";
        }
        return "Error. Money not added";
    }
}

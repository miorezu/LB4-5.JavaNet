package operations;

import data.MetroCardBank;

public class ShowBalanceOperation extends CardOperation {
    private String serialNumber = null;

    public ShowBalanceOperation(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ShowBalanceOperation() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String execute(MetroCardBank cardBank) {
        if (cardBank.checkBalance(serialNumber) != -999) {
            return "Success. Your Balance: " + cardBank.checkBalance(serialNumber);
        }
        return "Error.";
    }
}
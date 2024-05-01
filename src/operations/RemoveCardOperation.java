package operations;

import data.MetroCardBank;

public class RemoveCardOperation extends CardOperation {
    private String serialNumber = null;

    public RemoveCardOperation(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public RemoveCardOperation() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String execute(MetroCardBank cardBank) {
        if (cardBank.removeCard(serialNumber)) {
            return "Success. Card with " + serialNumber + " serial number removed successfully";
        }
        return "Error";
    }
}
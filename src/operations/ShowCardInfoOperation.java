package operations;

import tcpWork.MetroCardBank;
import tcpWork.User;

public class ShowCardInfoOperation extends CardOperation {
    private String serialNumber = null;

    public ShowCardInfoOperation() {
    }

    public ShowCardInfoOperation(String serialNumber) {
        this.serialNumber = serialNumber;

    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String execute(MetroCardBank cardBank) {
        if(cardBank.findMetroCard(serialNumber) == -1)
        {
            return "Error. No card";
        }
        int index = cardBank.findMetroCard(serialNumber);
        return cardBank.getStore().get(index).toString();
    }
}

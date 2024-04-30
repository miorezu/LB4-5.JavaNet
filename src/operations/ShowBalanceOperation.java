package operations;

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
}

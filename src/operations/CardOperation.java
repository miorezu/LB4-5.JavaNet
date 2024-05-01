package operations;

import tcpWork.MetroCardBank;

public abstract class CardOperation implements java.io.Serializable {
    public abstract String execute(MetroCardBank cardBank);
}

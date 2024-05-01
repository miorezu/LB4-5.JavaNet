package operations;

import data.MetroCardBank;

public abstract class CardOperation implements java.io.Serializable {
    public abstract String execute(MetroCardBank cardBank);
}
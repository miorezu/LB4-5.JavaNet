package operations;

import tcpWork.MetroCard;
import tcpWork.MetroCardBank;

public class AddMetroCardOperation extends CardOperation {
    private MetroCard card;

    public AddMetroCardOperation() {
        card = new MetroCard();
    }

    public MetroCard getCard() {
        return card;
    }

    @Override
    public String execute(MetroCardBank cardBank) {
        cardBank.addCard(card);
        return "Card added";
    }
}

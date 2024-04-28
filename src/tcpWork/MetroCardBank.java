package tcpWork;

import java.util.ArrayList;

public class MetroCardBank {
    ArrayList<MetroCard> store;

    public MetroCardBank() {
        ArrayList<MetroCard> store = new ArrayList<MetroCard>();
    }

    public ArrayList<MetroCard> getStore() {
        return store;
    }

    public void setStore(ArrayList<MetroCard> store) {
        this.store = store;
    }

    public int findMetroCard(String serNum) {
        for (MetroCard card : store) {
            if (card.getSerNum().equals(serNum)) {
                return store.indexOf(card);
            }
        }
        return -1;
    }

    public int numCards() {
        return store.size();
    }

    public void addCard(MetroCard newCard) {
        store.add(newCard);
    }

    public boolean removeCard(String serNum) {
        return store.remove(findMetroCard(serNum)) != null;
    }

    public boolean addMoney(String serNum, double money) {
        MetroCard card = store.get(findMetroCard(serNum));
        if (card != null) {
            card.setBalance(card.getBalance() + money);
            return true;
        }
        return false;
    }

    public boolean payment(String serNum, double money) {
        MetroCard card = store.get(findMetroCard(serNum));
        if (card != null) {
            card.setBalance(card.getBalance() - money);
            return true;
        }
        return false;
    }

    public double checkBalance(String serNum) {
        MetroCard card = store.get(findMetroCard(serNum));
        if (card != null) {
            return card.getBalance();
        }
        System.out.println("No card with such serNum");
        return -999;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder("List of MetroCards:");
        for (MetroCard card : store) {
            buf.append("\n\n" + card);
        }
        return buf.toString();
    }
}

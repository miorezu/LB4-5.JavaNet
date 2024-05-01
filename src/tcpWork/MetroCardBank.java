package tcpWork;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class MetroCardBank implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private ArrayList<MetroCard> store;

    public MetroCardBank() {
        store = new ArrayList<>();
    }

    public ArrayList<MetroCard> getStore() {
        return store;
    }

    public void setStore(ArrayList<MetroCard> store) {
        this.store = store;
    }

    public int findMetroCard(String serNum) {
        for (MetroCard card : store) {
            if (card.getSerialNumber().equals(serNum)) {
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
        if(findMetroCard(serNum) == -1){
            return false;
        }
        MetroCard card = store.get(findMetroCard(serNum));
        if(card != null && money > 0) {
            card.setBalance(card.getBalance() + money);
            return true;
        }
        return false;
    }

    public boolean payment(String serNum, double money) {
        if(findMetroCard(serNum) == -1){
            return false;
        }
        MetroCard card = store.get(findMetroCard(serNum));
        if(card != null && money > 0 && card.getBalance() > money) {
            card.setBalance(card.getBalance() - money);
            return true;
        }
        return false;
    }

    public double checkBalance(String serNum) {
        if(findMetroCard(serNum) == -1){
            System.out.println("No card with such serial number");
            return -999;
        }
        MetroCard card = store.get(findMetroCard(serNum));
        if (card != null && findMetroCard(serNum) != -1) {
            return card.getBalance();
        }
        System.out.println("No card with such serial number");
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
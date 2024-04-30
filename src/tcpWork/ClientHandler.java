package tcpWork;

import operations.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private ObjectInputStream objInStr = null;
    private ObjectOutputStream objOutStr = null;
    private boolean isWork;
    private MetroCardBank cardBank;
    private Socket socket;

    public ClientHandler(MetroCardBank cardBank, Socket socket) {
        this.cardBank = cardBank;
        this.socket = socket;
        this.isWork = true;
        try {
            this.objInStr = new ObjectInputStream(socket.getInputStream());
            this.objOutStr = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void run() {
        synchronized (cardBank) {
            System.out.println("Client Handler Started for: " + socket);
            while (isWork) {
                Object obj;
                try {
                    obj = objInStr.readObject();
                    processOperation(obj);
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Error: " + e);
                }
            }
            try {
                System.out.println("Client Handler Stopped for: " + socket);
                socket.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

    private void processOperation(Object obj) throws
            IOException, ClassNotFoundException {
        if (obj instanceof StopOperation) {
            finish();
        } else if (obj instanceof AddMetroCardOperation) {
            addCard(obj);
        } else if (obj instanceof AddMoneyOperation) {
            addMoney(obj);
        } else if (obj instanceof PayMoneyOperation) {
            payMoney(obj);
        } else if (obj instanceof RemoveCardOperation) {
            removeCard(obj);
        } else if (obj instanceof ShowBalanceOperation) {
            showBalance(obj);
        } else if (obj instanceof ShowCardInfoOperation) {
            showUserCard(obj);
        } else {
            error();
        }
    }

    private void finish() throws IOException {
        isWork = false;
        objOutStr.writeObject("Finish Work " + socket);
        objOutStr.flush();
    }

    private void addCard(Object obj)
            throws IOException, ClassNotFoundException {
        cardBank.addCard(((AddMetroCardOperation) obj).getCard());
        objOutStr.writeObject("Card Added");
        objOutStr.flush();
    }

    private void addMoney(Object obj)
            throws IOException, ClassNotFoundException {
        AddMoneyOperation op = (AddMoneyOperation) obj;
        boolean res = cardBank.addMoney(op.getSerialNumber(), op.getMoney());
        if (res) {
            objOutStr.writeObject("Balance Added");
            objOutStr.flush();
        } else {
            objOutStr.writeObject("Cannot Balance Added");
            objOutStr.flush();
        }
    }

    private void payMoney(Object obj)
            throws IOException, ClassNotFoundException {
        PayMoneyOperation op = (PayMoneyOperation) obj;
        boolean res = cardBank.payment(op.getSerialNumber(), op.getMoney());
        if (res) {
            objOutStr.writeObject("Money Payed");
            objOutStr.flush();
        } else {
            objOutStr.writeObject("Cannot Pay Money");
            objOutStr.flush();
        }
    }

    private void removeCard(Object obj)
            throws IOException, ClassNotFoundException {
        RemoveCardOperation op = (RemoveCardOperation) obj;
        boolean res = cardBank.removeCard(op.getSerialNumber());
        if (res) {
            objOutStr.writeObject("Metro Card Succesfully Remove: " + op.getSerialNumber());
            objOutStr.flush();
        } else {
            objOutStr.writeObject("Cannot Remove Card" + op.getSerialNumber());
            objOutStr.flush();
        }
    }

    private void showBalance(Object obj)
            throws IOException, ClassNotFoundException {
        ShowBalanceOperation op = (ShowBalanceOperation) obj;
        int ind = cardBank.findMetroCard(op.getSerialNumber());
        if (ind >= 0) {
            objOutStr.writeObject("Card : " + op.getSerialNumber() + " balance: "
                    + cardBank.getStore().get(ind).getBalance());
            objOutStr.flush();
        } else {
            objOutStr.writeObject("Cannot Show Balance for Card: " + op.getSerialNumber());
        }
    }

    private void error() throws IOException {
        objOutStr.writeObject("Bad Operation");
        objOutStr.flush();
    }

    private void showUserCard(Object obj) throws IOException, ClassNotFoundException {
        ShowCardInfoOperation op = (ShowCardInfoOperation) obj;
        int ind = cardBank.findMetroCard(op.getSerialNumber());
        if (ind >= 0) {
            objOutStr.writeObject("Card : " + op.getSerialNumber() + "\n" +
                    "User: " + cardBank.getStore().get(ind).getUser() + "\n" +
                    "Establishment: " + cardBank.getStore().get(ind).getEstablishment() + "\n" +
                    "Balance: " + cardBank.getStore().get(ind).getBalance());
            objOutStr.flush();
        }
    }
}

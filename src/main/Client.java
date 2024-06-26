package main;

import operations.*;
import data.User;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    private Socket socket = null;
    private ObjectInputStream ObjInStr = null;
    private ObjectOutputStream ObjOutStr = null;

    public Client(String server, int port) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(server, port), 1000);
            ObjOutStr = new ObjectOutputStream(socket.getOutputStream());
            ObjInStr = new ObjectInputStream(socket.getInputStream());
        } catch (InterruptedIOException e) {
            System.out.println("Error: " + e);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public void finish() {
        try {
            ObjOutStr.writeObject(new StopOperation());
            ObjOutStr.flush();
            System.out.println(ObjInStr.readObject());
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void applyOperation(CardOperation op) {
        try {
            ObjOutStr.writeObject(op);
            ObjOutStr.flush();
            System.out.println(ObjInStr.readObject());
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 7891);
        AddMetroCardOperation operation = new AddMetroCardOperation();
        operation.getCard().setUser(new User("Anzhelika", "Mazurenko", "F", "31.03.2005"));
        operation.getCard().setSerialNumber("00001");
        operation.getCard().setEstablishment("KhNU");
        operation.getCard().setBalance(9);
        client.applyOperation(operation);
        client.finish();

        client = new Client("localhost", 7891);
        client.applyOperation(new AddMoneyOperation("00001", 100));
        client.applyOperation(new ShowBalanceOperation("00001"));
        client.applyOperation(new PayMoneyOperation("00001", 50));
        client.applyOperation(new ShowBalanceOperation("00001"));
        client.applyOperation(new PayMoneyOperation("00001", 1000));
        client.applyOperation(new ShowCardInfoOperation("00001"));
        client.applyOperation(new XMLOperation());

        client.finish();

        client = new Client("localhost", 7891);
        operation = new AddMetroCardOperation();
        operation.getCard().setUser(new User("Erwin", "Smit", "M", "25.09.1999"));
        operation.getCard().setSerialNumber("00002");
        operation.getCard().setEstablishment("");
        operation.getCard().setBalance(20);
        client.applyOperation(operation);

        client.applyOperation(new AddMoneyOperation("00002", 100));
        client.applyOperation(new ShowBalanceOperation("00002"));
        client.applyOperation(new XMLOperation());

        client.applyOperation(new RemoveCardOperation("00002"));
        client.applyOperation(new AddMoneyOperation("00002", 100));
        client.applyOperation(new ShowBalanceOperation("00002"));
        client.applyOperation(new ShowCardInfoOperation("00002"));

        client.finish();
    }
}
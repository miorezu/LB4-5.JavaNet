package tcpWork;

import operations.*;

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
        operation.getCard().setUser(new User("Petr", "Petrov", "M", "25.12.1968"));
        operation.getCard().setSerialNumber("00001");
        operation.getCard().setEstablishment("KhNU");
        operation.getCard().setBalance(25);
        client.applyOperation(operation);
        client.finish();

        client = new Client("localhost", 7891);
        client.applyOperation(new AddMoneyOperation("00001", 100));
        client.applyOperation(new ShowBalanceOperation("00001"));
        client.finish();
    }
}

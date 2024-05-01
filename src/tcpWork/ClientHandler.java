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
        } else if (obj instanceof CardOperation) {
            objOutStr.writeObject(((CardOperation) obj).execute(cardBank));
            objOutStr.flush();
        } else {
            error();
        }
    }

    private void finish() throws IOException {
        isWork = false;
        objOutStr.writeObject("Finish Work " + socket);
        objOutStr.flush();
    }

    private void error() throws IOException {
        objOutStr.writeObject("Bad Operation");
        objOutStr.flush();
    }
}
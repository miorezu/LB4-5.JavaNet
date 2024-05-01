package main;

import data.MetroCardBank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MetroServer extends Thread {
    MetroCardBank cardBank;
    private ServerSocket serverSocket = null;
    private int serverPort;

    public MetroServer(int port) {
        this.cardBank = new MetroCardBank();
        this.serverPort = port;
    }

    public MetroCardBank getCardBank() {
        return cardBank;
    }

    public void setCardBank(MetroCardBank cardBank) {
        this.cardBank = cardBank;
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(serverPort);
            System.out.println("Metro Server started");
            while (true) {
                System.out.println("New Client Waiting...");
                Socket sock = serverSocket.accept();
                System.out.println("New client: " + sock);
                ClientHandler ch = new ClientHandler(this.getCardBank(), sock);
                ch.start();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                serverSocket.close();
                System.out.println("Metro Server stopped");
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

    public static void main(String[] args) {
        MetroServer srv = new MetroServer(7891);
        srv.start();
    }
}
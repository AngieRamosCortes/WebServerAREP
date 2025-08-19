package edu.escuelaing.app;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) throws Exception {
        int port = 9090;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Servidor activo en http://localhost:" + port);
            while (true) {
                Socket client = server.accept();
                new Thread(new RequestWorker(client)).start();
            }
        }
    }
}

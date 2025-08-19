package edu.escuelaing.app;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class RequestWorker implements Runnable {
    private final Socket client;

    public RequestWorker(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream out = client.getOutputStream()) {

            String firstLine = reader.readLine();
            if (firstLine == null)
                return;

            String[] tokens = firstLine.split(" ");
            String method = tokens[0];
            String uri = tokens[1];

            String path = uri;
            String query = null;
            int q = uri.indexOf('?');
            if (q >= 0) {
                path = uri.substring(0, q);
                query = uri.substring(q + 1);
            }

            if (path.equals("/"))
                path = "/index.html";

            // GET /hello?nombre=Pedro
            if (path.equals("/hello") && method.equals("GET")) {
                String nombre = getQueryValue(query, "nombre");
                String msg = "Saludos " + nombre + " con GET!";
                send(out, msg, "text/plain");
                return;
            }

            // POST /hellopost?nombre=Ana
            if (path.equals("/hellopost") && method.equals("POST")) {
                String nombre = getQueryValue(query, "nombre");
                String msg = "Saludos " + nombre + " con POST!";
                send(out, msg, "text/plain");
                return;
            }

            // GET /api/random
            if (path.equals("/api/random") && method.equals("GET")) {
                int num = (int) (Math.random() * 100) + 1;
                String json = "{\"randomNumber\": " + num + "}";
                send(out, json, "application/json");
                return;
            }

            // archivos estáticos desde resources
            try (InputStream file = getClass().getResourceAsStream(path)) {
                if (file != null) {
                    byte[] data = file.readAllBytes();
                    send(out, data, ContentType.get(path));
                } else {
                    send(out, "<h1>Página no encontrada</h1>", "text/html");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getQueryValue(String query, String key) {
        if (query == null)
            return "";
        for (String pair : query.split("&")) {
            String[] kv = pair.split("=", 2);
            if (kv.length == 2 && kv[0].equals(key)) {
                return URLDecoder.decode(kv[1], StandardCharsets.UTF_8);
            }
        }
        return "";
    }

    private void send(OutputStream out, String body, String type) throws IOException {
        byte[] data = body.getBytes(StandardCharsets.UTF_8);
        send(out, data, type);
    }

    private void send(OutputStream out, byte[] body, String type) throws IOException {
        out.write(("HTTP/1.1 200 OK\r\n").getBytes());
        out.write(("Content-Type: " + type + "\r\n").getBytes());
        out.write(("Content-Length: " + body.length + "\r\n").getBytes());
        out.write("\r\n".getBytes());
        out.write(body);
    }
}

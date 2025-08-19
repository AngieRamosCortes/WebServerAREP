package edu.escuelaing.app;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Pruebas de integración para el servidor web.
 */
public class AppTest {

    private static Thread serverThread;

    @BeforeClass
    public static void startServer() {
        serverThread = new Thread(() -> {
            try {
                MainServer.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.start();

        // Esperar un poco a que el servidor arranque
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
    }

    @AfterClass
    public static void stopServer() {
        // Opcional: implementar un endpoint de shutdown en MainServer si quieres terminarlo
        serverThread.interrupt();
    }

    /**
     * Helper para hacer peticiones HTTP GET
     */
    private String httpGet(String endpoint) throws Exception {
        URL url = new URL("http://localhost:9090" + endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        assertEquals(200, con.getResponseCode());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    @Test
    public void testHelloGet() throws Exception {
        String response = httpGet("/hello?nombre=Ana");
        assertTrue("La respuesta debe contener el nombre", response.contains("Ana"));
    }

    @Test
    public void testRandomApi() throws Exception {
        String response = httpGet("/api/random");
        assertTrue("La respuesta debe contener la clave randomNumber", response.contains("randomNumber"));
    }

    @Test
    public void testIndexPageLoads() throws Exception {
        String response = httpGet("/index.html");
        assertTrue("Debe contener el título Servidor Web", response.contains("Servidor Web"));
    }
}

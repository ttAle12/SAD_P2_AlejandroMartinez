/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sadp2;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author alejandro
 */
public class Server {

    private static final int PORT = 8080; // Puerto en el que se ejecutará el servidor
    private static Map<String, MySocket> clientDictionary = new ConcurrentHashMap<>(); // Diccionario de clientes

    public static void main(String[] args) throws IOException {

        MyServerSocket myServerSocket = new MyServerSocket(PORT); // Crear el socket del servidor
        System.out.println("Server STARTED!!!");

        while (true) {
            MySocket client = myServerSocket.accept(); // Esperar la siguiente conexión del cliente
            client.printLine("Conectado!!");

            // Crear un nuevo hilo para manejar la conexión del cliente
            new Thread(() -> {
                client.printLine("Introduce tu nombre de usurario, UserName: ");
                String name = client.readLine();
                client.printLine("Hola " + name + ", estas en el nuevochat!");
                clientDictionary.put(name, client);
                String text;
                while ((text = client.readLine()) != null) {
                    broadcast(text, name); // Enviar mensaje a todos los clientes
                    System.out.println(name + " says: " + text);
                }
                System.out.println(name + " has left the chat");
                clientDictionary.remove(name); // Eliminar cliente del diccionario
                client.close(); // Cerrar el socket del cliente
            }).start();
        }
    }

    // Enviar un mensaje a todos los clientes excepto al remitente
    public static void broadcast(String message, String name) {
        for (Map.Entry<String, MySocket> entry : clientDictionary.entrySet()) {
            String actualUser = entry.getKey();
            MySocket actualSocket = entry.getValue();
            if (!actualUser.equals(name)) {
                actualSocket.printLine(name + "> " + message);
            }
        }
    }
}

    


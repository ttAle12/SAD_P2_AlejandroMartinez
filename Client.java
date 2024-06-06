/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sadp2;

/**
 *
 * @author alejandro
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// Para ejectuar el cliente se debe hacer de la siguiente manera:
// >> java Client localhost 8080
public class Client {

    public static void main(String[] args) throws IOException {
        
        MySocket sc = new MySocket(args[0], Integer.parseInt(args[1]));

        BufferedReader BuffR = new BufferedReader(new InputStreamReader(System.in));
        
        // Input threat (keyboard)
        new Thread(() -> {
            String line;
            try {
                while ((line = BuffR.readLine()) != null) {
                    sc.printLine(line);
                    if(line.matches("exit")){
                        sc.close();
                        break;
                    }
                }
                sc.printLine("exit");
                sc.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();

        // Output threat (console)
        new Thread(() -> {
            String line;
            while ((line = sc.readLine()) != null) {
                if(line.matches("exit")){
                    break;
                }
                System.out.println(line);
            }
            System.out.println("Client Disconnected!!!");
                sc.close();
                System.exit(0);
        }).start();
    }
}

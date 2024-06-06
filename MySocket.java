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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MySocket extends Socket {

    public Socket sck;
   
    public BufferedReader reader;
    
    public PrintWriter wr;
    
    public String nick;
    
    int port;

   
    public MySocket(Socket socket) throws IOException {
        try {
            this.sck = socket;
            this.reader = new BufferedReader(new InputStreamReader(sck.getInputStream()));
            this.wr = new PrintWriter(new OutputStreamWriter(sck.getOutputStream()));
        } catch (IOException ex) {
            throw new IOException("Error al inicializar MySocket", ex);
        }

    }

    //crea un socket con un nick y diciendo a quien se quiere conectar
    public MySocket(String host, int port) throws IOException {
        try {
            this.port = port;
            sck = new Socket(host, port);
            this.reader = new BufferedReader(new InputStreamReader(sck.getInputStream()));
            this.wr = new PrintWriter(new OutputStreamWriter(sck.getOutputStream()));
        } catch (IOException ex) {
            throw new IOException("Error al inicializar MySocket", ex);
        }
    }

    public Socket getSocket(){
        return this.sck;
    }

    //
    public String getNick(){
        return nick;
    }

    // Para escoger el nick
    public void setNick(String nick){
        this.nick = nick;
    }

   
    public int getPort(){
        return this.port;
    }

    
    public String readLine() {
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return line;
    }

    // Lee el siguiente int, sino devuelve null
    public int readInt() {
        return Integer.parseInt(readLine());
    }

    // Lee el siguiente double, sino devuelve null
    public Double readDOuble() {
        return Double.parseDouble(readLine());
    }

    // Lee el siguiente boolean, sino devuelve null
    public Boolean readBool() {
        return Boolean.parseBoolean(readLine());
    }

    // Lee el siguiente short, sino devuleve null
    public Short readSHort() {
        return Short.parseShort(readLine());
    }

    // Lee el siguiente long, sino devuleve null
    public Long readLong() {
        return Long.parseLong(readLine());
    }

    // Lee el siguiente Byte en caso contrario devolvera un null
    public Byte readByte() {
        return Byte.parseByte(readLine());
    }

   
    public void printLine(String line) {
       
        wr.println(line);
        
    }

    // Funcio per escriure un int
    public void printInt(int num) {
        wr.println(num);
       
    }

    
    public void printDouble(Double dob) {
        wr.println(dob);
       
    }

 
    public void printBool(Boolean bool) {
        wr.println(bool);
       
    }

    

    public void printByte(Byte byt) {
        wr.println(byt);
       
    }

   
    public void close() {
        try {
            wr.close();
            reader.close();
            sck.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

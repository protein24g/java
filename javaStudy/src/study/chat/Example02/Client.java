package study.chat.Example02;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        BufferedWriter out = null;
        Scanner scanner = new Scanner(System.in);
        ServerSocket serverSocket = null;
        Socket socket = null;
        try{
            socket = new Socket("localhost", 9999);
            Thread receiver = new Thread(new Receiver(socket));
            receiver.start();

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while(true){
                String string = scanner.nextLine();
                if(string == null) break;
                out.write(string + "\n");
                out.flush();
            }
        }catch (Exception e){}
    }
}

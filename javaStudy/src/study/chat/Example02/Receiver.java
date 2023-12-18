package study.chat.Example02;

import java.io.*;
import java.net.*;

public class Receiver implements Runnable{
    Socket socket;
    public Receiver(Socket socket){this.socket = socket;}

    public void run(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                String string = in.readLine();
                if(string == null) break;
                System.out.println(string);
            }
        }catch (Exception e){}
    }
}

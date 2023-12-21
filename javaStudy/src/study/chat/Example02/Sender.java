package study.chat.Example02;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Sender implements Runnable{
    Socket socket;
    Scanner scanner = new Scanner(System.in);
    public Sender(Socket socket){this.socket = socket;}
    public void run(){
        try{
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while(true){
                String string = scanner.nextLine();
                if(string == null) break;
                out.write(string + "\n");
                out.flush();
            }
        }catch (Exception e){}
    }
}

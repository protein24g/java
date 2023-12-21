package study.chat.Example01;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient{
    public static void main(String[] args) {
        BufferedReader in;
        BufferedWriter out;
        Scanner scanner = new Scanner(System.in);
        try{
            Socket socket = new Socket("localhost", 9999);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true){
                System.out.print("나 : ");
                String outMsg = scanner.nextLine();
                if(outMsg.equalsIgnoreCase("bye")){
                    out.write(outMsg + "\n");
                    out.flush();
                    break;
                }else{
                    out.write(outMsg + "\n");
                    out.flush();
                    String inMsg = in.readLine();
                    System.out.println("서버 : " + inMsg);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
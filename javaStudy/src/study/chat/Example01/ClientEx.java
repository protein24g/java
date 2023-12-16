package study.chat.Example01;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientEx {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        Scanner sc = new Scanner(System.in);

        try {
            socket = new Socket("localhost", 9999);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true){
                System.out.println("보내기>>");
                String outMsg = sc.nextLine();
                if(outMsg.equalsIgnoreCase("bye")){
                    out.write(outMsg + "\n");
                    out.flush();
                    break;
                }
                out.write(outMsg + "\n");
                out.flush();
                String inMsg = in.readLine();
                System.out.println("서버 >>" + inMsg);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                sc.close();
                out.close();;
                in.close();
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

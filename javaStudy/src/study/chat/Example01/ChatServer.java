package study.chat.Example01;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer{
    public static void main(String[] args) {
        BufferedReader in;
        BufferedWriter out;
        Scanner scanner = new Scanner(System.in);
        try{
            ServerSocket serverSocket = new ServerSocket(9999); //서버 소켓 생성
            System.out.println("접속 대기 중...");
            Socket socket = serverSocket.accept(); //접속할 떄까지 대기
            System.out.println("접속 완료");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true){
                String inStr = in.readLine();
                if(inStr.equalsIgnoreCase("bye")){
                    System.out.println("클라이언트가 나갔습니다");
                    break;
                }else{
                    System.out.println("클라이언트 : " + inStr);
                    System.out.print("나 : ");
                    String inMsg = scanner.nextLine();
                    out.write(inMsg + "\n");
                    out.flush();
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
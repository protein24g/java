package study.chat.chatting;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Socket socket = new Socket("localhost", 8888);
            System.out.println("서버에 연결됨.");

            // 서버로부터 수신된 메시지를 처리하는 스레드
            Thread receiver = new Thread(new Receiver(socket));
            receiver.start();

            // 클라이언트에서 메시지를 송신하는 부분
            try{
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                while (true) {
                    String str = scanner.nextLine();
                    out.write(str + "\n");
                    out.flush();
                }
            }catch (Exception e){}

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

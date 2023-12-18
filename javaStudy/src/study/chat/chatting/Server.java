package study.chat.chatting;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("서버 시작됨.");

            Socket socket = serverSocket.accept();
            System.out.println("클라이언트 연결됨.");

            // 클라이언트로부터 수신된 메시지를 처리하는 스레드
            Thread receiver = new Thread(new Receiver(socket));
            receiver.start();

            // 서버에서 메시지를 송신하는 부분
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String str = consoleReader.readLine();
                writer.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
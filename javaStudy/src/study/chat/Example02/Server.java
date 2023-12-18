package study.chat.Example02;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("서버 접속 대기중...");
            Socket socket = serverSocket.accept();
            System.out.println("서버 접속 성공");

            Thread receiver = new Thread(new Receiver(socket));
            Thread sender = new Thread(new Sender(socket));
            receiver.start();
            sender.start();
        }catch (Exception e){}
    }
}
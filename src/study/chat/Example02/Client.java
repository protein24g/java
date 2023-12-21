package study.chat.Example02;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost", 9999);
            Thread receiver = new Thread(new Receiver(socket));
            Thread sender = new Thread(new Sender(socket));
            receiver.start();
            sender.start();
        }catch (Exception e){}
    }
}

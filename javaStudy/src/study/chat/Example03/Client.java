package study.chat.Example03;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost", 9999);
            System.out.println("서버와 연결 성공");

            Thread sendThread = new Thread(new SendThread(socket));
            Thread receiverThread = new Thread(new ReceiverClient(socket));
            sendThread.start();
            receiverThread.start();
        }catch (Exception e){}
    }
}

class SendThread implements Runnable{
    Socket socket;
    String string, name;
    BufferedReader keyboard;
    BufferedWriter out;
    public SendThread(Socket socket){
        this.socket = socket;
    }
    public void run(){
        try{
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.print("닉네임을 입력하세요 : ");
            name = keyboard.readLine();
            out.write(name + "\n"); //처음엔 이름을 보낸다
            out.flush();

            while((string = keyboard.readLine()) != null){
                if(string.equalsIgnoreCase("!quit")) break;
                out.write(string + "\n");
                out.flush();
            }
        } catch (Exception e){}
    }
}

class ReceiverClient implements Runnable{
    Socket socket;
    BufferedReader in;
    public ReceiverClient(Socket socket){this.socket = socket;}
    public void run(){
        String string;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                string = in.readLine();
                if(string.equalsIgnoreCase("!quit")) break;
                System.out.println(string);
            }
        }catch (Exception e){}
    }
}
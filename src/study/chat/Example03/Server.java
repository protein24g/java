package study.chat.Example03;

import java.util.*;
import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args){
        try{
            List<Socket> ary = Collections.synchronizedList(new ArrayList<>());
            ServerSocket serverSocket = new ServerSocket(9999);
            while(true){
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new ReceiverServer(socket, ary));
                thread.start();
            }
        }catch (Exception e){}
    }
}

class ReceiverServer implements Runnable {
    Socket socket;
    List<Socket> ary = null;
    BufferedWriter out = null;
    BufferedReader in = null;
    String name;

    public ReceiverServer(Socket socket, List<Socket> ary) {
        try {
            this.socket = socket;
            this.ary = ary;
            this.ary.add(this.socket);
        } catch (Exception e) {
        }
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = in.readLine(); //첫 한번은 이름을 받는다
            sendAll("[" + name + "] 님이 입장하셨습니다");

            String string = null;
            while (true) {
                string = in.readLine();
                sendAll(name + " : " + string);
                System.out.println(name + " : " + string);
            }
        } catch (SocketException e) {
            System.out.println("클라이언트(" + name + ")와의 연결이 갑자기 끊겼습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sendAll(name + "님이 퇴장하셨습니다");
            ary.remove(name);
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("[" + name + " 연결 종료]");
        }
    }

    public void sendAll(String string) {
        for (Socket i : ary) {
            try {
                out = new BufferedWriter(new OutputStreamWriter(i.getOutputStream()));
                if(this.socket != i){
                    out.write(string + "\n");
                    out.flush();
                }
            } catch (Exception e) {
            }
        }
    }
}

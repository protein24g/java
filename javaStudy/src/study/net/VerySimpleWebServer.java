package study.net;

import java.io.*;
import java.net.*;
import java.util.*;

public class VerySimpleWebServer{
    public static void main(String[] args) throws Exception{
        ServerSocket serversocket = new ServerSocket(10000);
        try{
            while(true){
                System.out.println("서버 접속 대기");
                Socket socket = serversocket.accept();
                ClientThread c1 = new ClientThread(socket);
                c1.start();
            }
        }finally {
            serversocket.close();
            System.out.println("서버 접속 종료");
        }



    }
}

class ClientThread extends Thread{
    private Socket socket;
    public ClientThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            List<String> headers = new ArrayList<>();
            String line;
            while(!(line = br.readLine()).equals("")) headers.add(line);
            for(String i: headers) System.out.println(i);

            pw.println("HTTP/1.1 200 OK");
            pw.println("name: kim");
            pw.println();
            pw.println("<html>");
            pw.println("<h1>Hello</h1>");
            pw.println("</html>");

            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
package study.chat.Example01;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServerEx {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        Scanner sc = new Scanner(System.in);

        try {
            server = new ServerSocket(9999);
            System.out.println("연결 대기중...");

            socket = server.accept();
            System.out.println("연결 되었습니다.");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true){
                String inMsg = in.readLine();
                if(inMsg.equalsIgnoreCase("bye")){
                    System.out.println("클라이언트가 나갔습니다.");
                    break;
                }
                // 정살 메시지(bye가 아님)
                System.out.println("클라이언트 " + inMsg);
                System.out.println("보내기>>");
                String outMsg = sc.nextLine();
                out.write(outMsg + "\n");
                out.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                sc.close();
                out.close();;
                in.close();
                socket.close();
                server.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}

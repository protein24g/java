package study.chat.Example02;
import java.net.*;
import java.util.*;
import java.io.*;

public class ChatServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(9999);
        List<PrintWriter> outList = Collections.synchronizedList(new ArrayList<>());
        while(true){
            Socket socket = serverSocket.accept();
            ChatThread chatThread = new ChatThread(socket, outList);
            chatThread.start();

        }

    }
}

class ChatThread extends Thread{
    private Socket socket;
    private List<PrintWriter> outList;
    private PrintWriter out;
    private BufferedReader in;

    public ChatThread(Socket socket, List<PrintWriter> outList){
        this.socket = socket;
        this.outList = outList;
    }

    public void run(){

    }
}
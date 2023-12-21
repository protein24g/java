package toyProject.game369;
import java.io.*;
class Game{
    static BufferedReader br;
    int totalThread, round = 1, gameRound;
    private static final Object lock = new Object();
    private static boolean isGameFinished = false;
    public Game(int totalThread, int gameRound){
        this.totalThread = totalThread;
        this.gameRound = gameRound;
    }

    public void start369(int threadNum){
        synchronized (lock) {
            while (round <= gameRound) {
                if (isGameFinished) break;
                if(threadNum == round % totalThread + 1){
                    if(threadNum == 1) {
                        br = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            String myString = br.readLine();
                            if(!rtnResult(Integer.toString(round++)).equals(myString)){
                                System.out.println("틀림");
                                isGameFinished = true;
                                lock.notifyAll(); // 모든 대기 중인 스레드를 깨운다
                                break;
                            }
                        } catch (Exception e) {}
                    }
                    else{
                        int t = (int)(Math.random()*501 + 500); // 500~1000 사이 랜덤 sleep
                        try {
                            Thread.sleep(t);
                            System.out.println(rtnResult(Integer.toString(round++)));
                        } catch (Exception e) {}
                    }
                    lock.notifyAll();
                }else{
                    try{
                        lock.wait();
                    }catch (Exception e){}
                }
            }
            if (round > gameRound && !isGameFinished) { // 승리했을 시 승리 출력 후 종료
                isGameFinished = true;
                lock.notifyAll(); // 모든 대기 중인 스레드를 깨운다
                System.out.println("승리");
            }
        }
    }

    public String rtnResult(String num){
        int numCount = 0;
        for(int i = 0; i < num.length(); i++){
            char tmp = num.charAt(i);
            if(tmp == '3' || tmp == '6' || tmp == '9') numCount++;
        }

        if(numCount > 0){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < numCount; i++) sb.append('짝');
            return sb.toString();
        }else{
            return num;
        }

    }
}

class Player implements Runnable{
    Game game;
    public Player(Game game){
        this.game = game;
    }

    public void run(){
        game.start369(Integer.parseInt(Thread.currentThread().getName()));
    }
}

class Pc implements Runnable{
    Game game;
    public Pc(Game game){
        this.game = game;
    }

    public void run(){
        game.start369(Integer.parseInt(Thread.currentThread().getName()));
    }
}

public class Game369{
    public static void main(String[] args){
        int n = 2; // Bot 스레드 갯수
        Game game = new Game(n + 1, 10); // +1(Player 스레드 포함 n개)
        Thread player = new Thread(new Player(game), "1");
        player.start();

        Thread[] pc = new Thread[n]; // n개의 pc 스레드 배열
        for(int i = 0; i < pc.length; i++){
            pc[i] = new Thread(new Pc(game), String.valueOf((i+1+1))); // game 인스턴스, 스레드 id
            pc[i].start();
        }
    }
}

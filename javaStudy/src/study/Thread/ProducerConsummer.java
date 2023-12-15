package study.Thread;

import java.util.*;

class Buffer{
    private int contents; //생산자와 소비자가 공유하는 데이터(쌀)를 넣는 곳
    private boolean available = false; //번갈아가며 수행하기 위한 변수

    public synchronized void put(int value){ //생산자가 가져다 놓는 동기화 메소드
        while (available == true){
            try{
                wait();
            }catch (InterruptedException e){}
        }
        contents = value;
        System.out.println("생산자 ##### : 생산 " + contents);
        notify(); //데이터를 생산하고 대기 스레드를 깨운다
        available = true;
    }

    public synchronized int get(){
        while (available == false){ //소비자가 데이터를 가져가는 동기화 메소드
            try{
                wait();
            }catch (InterruptedException e){}
        }
        System.out.println("소비자 ##### : 소비 " + contents);
        notify(); //데이터를 생산하고 대기 스레드를 깨운다
        available = false;
        return contents;
    }
}

class Producer extends Thread{ //생산자의 스레드
    private Buffer b;
    public Producer(Buffer blank){
        b = blank;
    }

    public void run(){
        for(int i = 1; i <= 10; i++){
            b.put(i);
        }
    }
}

class Consumer extends Thread{ //생산자의 스레드
    private Buffer b;
    public Consumer(Buffer blank){
        b = blank;
    }

    public void run(){
        int value = 0;
        for(int i = 1; i <= 10; i++){
            value = b.get();
        }
    }
}



public class ProducerConsummer {
    public static void main(String[] args) {
        Buffer buff = new Buffer();
        Producer p1 = new Producer(buff);
        Consumer c1 = new Consumer(buff);

        p1.start();
        c1.start();
    }
}

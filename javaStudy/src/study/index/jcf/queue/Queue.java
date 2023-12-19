package study.index.jcf.queue;

import java.util.LinkedList;

public class Queue {
    public static void main(String[] args) {
        LinkedList<String> queue = new LinkedList<>();
        queue.offer("가");
        queue.offer("나");
        queue.offer("다");
        System.out.println("현재 큐 : " + queue);

        String data = queue.poll();
        System.out.println("꺼내온 값 : " + data);
        System.out.println("꺼내온 값 : " + queue.poll());
        System.out.println("현재 큐 : " + queue);
    }
}

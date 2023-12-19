package study.index.jcf;

import java.util.LinkedList;

public class List {
    public static void main(String[] args) {
        LinkedList<String> stack = new LinkedList<>();
        stack.push("가");
        stack.push("나");
        stack.push("다");
        System.out.println("현재 스택 : " + stack);

        String data = stack.pop();
        System.out.println("꺼내온 값 : " + data);
        System.out.println("꺼내온 값 : " + stack.pop());
        System.out.println("현재 스택 : " + stack);
    }

}

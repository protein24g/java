package codingTest.npr_ncr;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        int r = 2;
        int[] ary = {1, 2, 3};
        boolean[] visited = new boolean[ary.length];

        npr(0, r, new int[r], ary, visited); // 순열
        npr_repet(0, r, new int[r], ary); // 중복 순열
        ncr(0, r, ary, visited, 0); // 조합
        ncr_repet(0, r, new int[r], ary, 0); // 중복 조합
    }

    public static void npr(int depth, int r, int[] res, int[] ary, boolean[] visited){
        if (depth == r){
            for(int n : res) System.out.print(n+" ");
            System.out.println();
            return;
        }else{
            for(int i = 0; i < ary.length; i++){
                visited[i] = true;
                res[depth] = ary[i];
                npr(depth + 1, r, res, ary, visited);
                visited[i] = false;
            }
        }
    }

    public static void npr_repet(int depth, int r, int[] res, int[] ary){
        if (depth == r){
            for(int n: res) System.out.print(n+" ");
            System.out.println();
            return;
        }else{
            for(int i = 0; i < ary.length; i++){
                res[depth] = ary[i];
                npr_repet(depth + 1, r, res, ary);
            }
        }
    }

    public static void ncr(int depth, int r, int[] ary, boolean[] visited, int start){
        if (depth == r){
            for(int i = 0; i < ary.length; i++){
                if(visited[i]) System.out.print(ary[i]+" ");
            }
            System.out.println();
            return;
        }else{
            for(int i = start; i < ary.length; i++){
                if(!visited[i]){
                    visited[i] = true;
                    ncr(depth + 1, r, ary, visited, i + 1);
                    visited[i] = false;
                }
            }
        }
    }

    public static void ncr_repet(int depth, int r, int[] res, int[] ary, int start){
        if (depth == r){
            for(int n: res) System.out.print(n+" ");
            System.out.println();
            return;
        }else{
            for(int i = start; i < ary.length; i++){
                res[depth] = ary[i];
                ncr_repet(depth + 1, r, res, ary, i);
            }
        }
    }
}
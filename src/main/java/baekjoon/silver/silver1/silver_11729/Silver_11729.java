package baekjoon.silver.silver1.silver_11729;

import java.util.*;

/**
 *
 * retrun: 옮긴 횟수, 옮기는 위치
 */
public class Silver_11729 {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // input n
        int n = sc.nextInt();

        // move count
        sb.append((int) Math.pow(2, n) - 1).append("\n");

        // move rings
        moveRings(n,1, 2, 3);

        //output
        System.out.println(sb);
    }

    public static void moveRings(int n, int start, int mid, int end){

        if(n == 1){
            sb.append(start + " " + end + "\n");
            return;
        }

        moveRings(n - 1, start, end, mid);
        sb.append(start + " " + end + "\n");
        moveRings(n - 1, mid, start, end);
    }
}

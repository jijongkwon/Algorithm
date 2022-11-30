package baekjoon.silver.silver1.silver_11286;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Silver_11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int absO1 = Math.abs(o1);
                int absO2 = Math.abs(o2);

                if(absO1 == absO2){
                    if(o1 > o2){
                        return 1;
                    }
                    return -1;
                }

                return absO1 - absO2;
            }
        });

        // 입력받을 개수
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            // 배열에 넣을 숫자 입력
            int x = Integer.parseInt(br.readLine());

            // x가 0이면 출력
            if(x == 0){
                if(priorityQueue.isEmpty()){
                    System.out.println(0);
                    continue;
                }
                System.out.println(priorityQueue.poll());
                continue;
            }
            priorityQueue.add(x);
        }
    }
}

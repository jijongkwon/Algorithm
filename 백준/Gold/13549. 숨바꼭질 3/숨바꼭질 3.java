import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 0 <= n <= 100,000
 * 0 <= k <= 100,000
 *
 * 수빈의 위치가 x 일 때 1초 후에 x - 1 또는 x + 1
 * 순간이동인 경우 0초 후에 2*x 위치로 이동
 *
 * dp, bfs
 */
public class Main {
    static int[] times = new int[1000001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int minTime = Integer.MAX_VALUE;

        if(k <= n){
            minTime = n - k;
        }
        else {
            bfs(n);
            minTime = times[k];
        }

        System.out.println(minTime);
    }

    static void bfs(int n){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(times, Integer.MAX_VALUE);
        times[0] = 0;
        pq.add(new Node( n, 0));

        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(times[node.cur] < node.value){
                continue;
            }

            if(node.cur - 1 > 0 && times[node.cur - 1] > node.value + 1){
                times[node.cur - 1] = node.value + 1;
                pq.add(new Node(node.cur - 1, node.value + 1));
            }

            if(node.cur + 1 <= 1000000 && times[node.cur + 1] > node.value + 1){
                times[node.cur + 1] = node.value + 1;
                pq.add(new Node(node.cur + 1, node.value + 1));
            }

            if(node.cur * 2 <= 1000000 && times[node.cur * 2] > node.value){
                times[node.cur * 2] = node.value;
                pq.add(new Node(node.cur * 2, node.value));
            }
        }
    }

    static class Node implements Comparable<Node> {
        int cur;
        int value;

       Node(int cur, int value){
           this.cur = cur;
           this.value = value;
       }
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
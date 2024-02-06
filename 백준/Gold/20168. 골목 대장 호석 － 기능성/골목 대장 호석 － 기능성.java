import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 6.
 * @link https://www.acmicpc.net/problem/20168
 * @keyword_solution  
 * 1. n 교차로, m 골목
 * 2. 두 교차로 앙방향, 골목은 최대 한개
 * 3. 한 골목에서 내야하는 최대 요금을 최소화 (최대요금 중에서 최소금액 )
 * 4. 금액이 있어야 지나갈 수 있으니 유의
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	
	static class Node{
		int node;
		int collectionAmount;
		int maximum = 0;
		
		Node(int node, int collectionAmount, int maximum){
			this.node = node;
			this.collectionAmount = collectionAmount;
			this.maximum = maximum;
		}
	}

	static List<Node>[] graph;
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int crossLoad = sc.nextInt(); // 교차로
		int alley = sc.nextInt(); // 골목
		int start = sc.nextInt(); // 시작점
		int end = sc.nextInt(); // 도착점
		int money = sc.nextInt(); // 가진돈
		
		graph = new ArrayList[crossLoad + 1];
		visited = new boolean[crossLoad + 1];
		for(int i = 1; i <= crossLoad; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < alley; i++) {
			int inputStart = sc.nextInt();
			int inputEnd = sc.nextInt();
			int inputCollectionAmount = sc.nextInt();
			
			// 양방향 연결
			graph[inputStart].add(new Node(inputEnd, inputCollectionAmount, 0));
			graph[inputEnd].add(new Node(inputStart, inputCollectionAmount, 0));
		}
		
		dijkstra(start, end, money);
		
		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(answer);			
		}
		
	}
	
	static void dijkstra(int start, int end, int money) {
		// 내림차순
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->{
			return (o1.collectionAmount - o2.collectionAmount);
		});
		
		pq.add(new Node(start, 0, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.collectionAmount > money) {
				continue;
			}
			
			if(cur.node == end) {
				answer = Math.min(answer, cur.maximum);
				continue;
			}
			
			
			if(!visited[cur.node]) {
				visited[cur.node] = true;
			}
			
			for(Node next : graph[cur.node]) {
				if(!visited[next.node]) {
					pq.add(new Node(next.node, cur.collectionAmount + next.collectionAmount,
							Math.max(next.collectionAmount, cur.maximum)));
				}
			}
		}
	}

}

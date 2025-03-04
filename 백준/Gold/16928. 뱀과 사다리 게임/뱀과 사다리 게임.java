import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 10 * 10 지정된 게임판
 *
 * i + 주사위 눈 만큼 이동
 * 이동한 칸이 사다리면 위로 이동, 뱀이면 뱀따라서 내려감
 *
 * return 1 -> 100 까지 도착하기 위한 최소 주사위 굴린 수
 *
 * 풀이 bfs
 */
public class Main {
    static boolean[] visited;
    static HashMap<Integer, Integer> skips = new HashMap<>();
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[101];

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            skips.put(x,y);
        }

        bfs();
    }

    static void bfs(){
        Queue<int[]> queue = new LinkedList<>();
        int[] count = new int[101];
        queue.add(new int[]{1,0});
        visited[1] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cur = current[0];
            int diceCount = current[1];

            for (int i = 1; i <= 6 ; i++) {
                int next = cur + i;

                if(next == 100){
                    System.out.println(diceCount + 1);
                    return;
                }

                if(next > 100){
                    continue;
                }

                if(visited[next]){
                    continue;
                }

                if(skips.containsKey(next)){
                    next = skips.get(next);
                }

                visited[next] = true;
                count[next] = count[cur] + 1;
                queue.add(new int[]{next, diceCount + 1});
            }
        }
    }
}
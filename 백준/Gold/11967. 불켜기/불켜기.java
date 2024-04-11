import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {

    static int n, m;
    static ArrayList<Node>[][] graph;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static boolean[][] visit;
    static boolean[][] lights;


    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        lights = new boolean[n + 1][n + 1];
        graph = new ArrayList[n + 1][n + 1];
        lights[1][1] = true;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                graph[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            graph[Integer.parseInt(input[0])][Integer.parseInt(input[1])].add(new Node(Integer.parseInt(input[2]), Integer.parseInt(input[3])));
        }

        System.out.println(bfs() + 1);
        br.close();
    }

    public static int bfs() {
        int cnt = 0;
        ArrayDeque<Node> dq = new ArrayDeque<>();
        dq.offerLast(new Node(1, 1));
        visit = new boolean[n + 1][n + 1];

        boolean isSwitchOn = false;

        while (!dq.isEmpty()) {
            Node now = dq.removeFirst();

            for (int i = 0; i < 4; i++) {
                int tmpx = now.x + dx[i];
                int tmpy = now.y + dy[i];
                if (0 <= tmpx && tmpx <= n && 0 <= tmpy && tmpy <= n && !visit[tmpx][tmpy] && lights[tmpx][tmpy]) {
                    dq.offerLast(new Node(tmpx, tmpy));
                    visit[tmpx][tmpy] = true;
                }
            }

            for (Node turnOn : graph[now.x][now.y]) {
                if (!lights[turnOn.x][turnOn.y]) {
                    isSwitchOn = true;
                    lights[turnOn.x][turnOn.y] = true;
                    cnt++;
                }
            }
        }

        if (isSwitchOn) {
            return cnt + bfs();
        }
        return cnt;
    }
}
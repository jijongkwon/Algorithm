import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import org.w3c.dom.Node;

/**
 * 조건
 * 1. 정사각형은 서로 겹치면 안됨
 * 2. 도형은 모두 연결
 * 3. 정사격형의 변끼리 연결되어 있어야 한다.
 *
 * 모양은 생각하지말고 한 칸에서 4칸 탐색하면 됨
 * 그리고 최대값 갱신
 */
public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int n,m;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j,  0, map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    static void dfs(int x, int y, int depth, int count){
        // 종료 조건
        if(depth == 3){
            max = Math.max(max, count);
            return;
        }

        // 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m){
                continue;
            }

            if(visited[nx][ny]){
                continue;
            }

            if(depth == 1){
                visited[nx][ny] = true;
                dfs(x, y, depth + 1, count + map[nx][ny]);
                visited[nx][ny] = false;
            }

            visited[nx][ny] = true;
            dfs(nx, ny, depth + 1, count + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }
}
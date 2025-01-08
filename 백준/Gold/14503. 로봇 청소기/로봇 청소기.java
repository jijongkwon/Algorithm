import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 현재 칸이 청소 되지 않으면 청소
 * 4칸 중 청소되지 않은 빈칸이 없는 경우, 방향을 유지한 채로 한칸 후진
 * - 만약 벽이라면 작동 멈춤
 * 4칸 중 청소되지 앟은 빈칸이 있는 경우, 반시계 회전하고 앞쪽칸이 청송되지 않은 빈칸인 경우 전진, 아니면 반복
 */
public class Main {
    static int n, m;
    static int[][] map;

    // 북 0 동 1 남 2 서 3
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    static boolean[][] visited;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            if(!visited[x][y]){
                visited[x][y] = true;
                count++;
            }

            if(isClean(x, y)){
                // 현재 방향에서 후진
                int nd = 0;
                if(d == 0) nd = 2;
                if(d == 1) nd = 3;
                if(d == 2) nd = 0;
                if(d == 3) nd = 1;

                x += dx[nd];
                y += dy[nd];

                if(map[x][y] == 1){
                    break;
                }
            }
            else{
                // 반시계 회전
                if(d == 0){
                    d = 3;
                }
                else{
                    d--;
                }

                int nx = x + dx[d];
                int ny = y + dy[d];

                if(visited[nx][ny] || map[nx][ny] == 1){
                    continue;
                }

                x = nx;
                y = ny;
            }
        }

        System.out.println(count);
    }

    private static boolean isClean(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(map[nx][ny] == 0 && !visited[nx][ny]){
                return false;
            }
        }

        return true;
    }
}
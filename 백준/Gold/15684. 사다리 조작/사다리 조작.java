import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 * return 세로선 i == i 가 나오게
 * 사다리 항상 아래로 내려감
 *
 * 조합
 */
public class Main {
    static int n, m, h;
    static int[][] map;
    static int answer = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        if(m == 0){
            System.out.println(0);
            return;
        }

        // 사다리 초기화
        map = new int[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 사다리 연결
            map[a][b] = 1;
            map[a][b + 1] = 2;
        }

        if(slidingLadder(map)){
            System.out.println(0);
            return;
        }

        for (int i = 1; i < 4; i++) {
            backTracking(map, 0, i, 1);
        }

        System.out.println(answer == 4 ? -1 : answer);
    }

    public static void backTracking(int[][]map, int depth, int limit, int startX){
        // 예외 조건 : limit이 answer 보다 크면 return
        if(limit >= answer){
            return;
        }

        // 예외 조건 : 깊이가 제한보다 크면 return
        if(depth > limit){
            return;
        }

        // 종료 조건
        if(depth == limit){
            if(slidingLadder(map)){
                answer = limit;
            }
            return;
        }

        // 탐색 ( 1 -> 2 -> 3 )
        // 1번만 놓을 경우를 먼저 다 돌리려면 ?
        // 예외 조건 : 가로선이 이어지는 경우
        for (int i = startX; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                // 가로선이 이어지는 경우
                if(map[i][j] != 0 || map[i][j + 1] != 0){
                    continue;
                }

                //

                map[i][j] = 1;
                map[i][j + 1] = 2;

                backTracking(map, depth + 1, limit, i);

                map[i][j] = 0;
                map[i][j + 1] = 0;
            }
        }
    }

    /**
     * 1 1
     * 0 0
     * 0 0
     */
    // 사다리 내려오기
    // 0이면 내려오기
    // 1이면 좌우 살피고 움직이기
    public static boolean slidingLadder(int[][] map){
        for (int i = 1; i <= n; i++) {
            int y = i;

            // 내려가기
            for (int j = 1; j <= h ; j++) {

                // 0이면 내려가기
                if(map[j][y] == 0){
                    continue;
                }
                // 우
                if(map[j][y] == 1){
                    y++;
                    continue;
                }

                y--;
            }

            // 같은 인덱스인지 확인하기
            if(y != i){
                return false;
            }
        }

        return true;
    }
}
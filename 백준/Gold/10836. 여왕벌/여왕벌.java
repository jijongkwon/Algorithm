import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  m * m 벌집
 *  애벌레가 있고 12시에 한 번 자람
 *  아침 모든 애벌레의 크기는 1, 이러한 과정을 n번 반복
 *
 *  크기가 커지는 정도는 0, 1, 2
 *
 *  규칙
 *  가장 왼쪽, 위쪽 행의 애벌레들은 자신이 자라는 정도를 스스로 결정 ( 입력으로 주어짐 )
 *  자라는 정도는 왼쪽 제일 아래 칸에서 시작 -> 위로 가면서 읽음
 *  위에 도착하면 오른쪽으로 감
 *
 *  나머지 애벌레들은 자신의 왼쪽, 왼쪽 위, 위쪽의 애벌레들이 다 자란 다음,
 *  그 날 가장 많이 자란 애벌레가 자란 만큼 자신도 자람
 *
 *  제약
 *  2 <= m <= 700
 *  1 <= n <= 1,000,000
 *
 *  풀이
 *  1. 우선 먼저 자라는 친구들을 map에 반영 ( 마지막 날 까지 적용 )
 *  2. 외에 친구들은 왼쪽, 왼쪽 위, 위쪽을 보면서 반영
 *      2 - 1. 왼쪽 위에서 아래로 진행 -> 끝나면 오른쪽 열로 이동 후 위쪽에서 아래쪽으로 진행
 *
 *  3. 끝나면 애벌래의 크기 출력
 */
public class Main {
    static int n, days;
    static int[][] map;
    static int[] dx = {-1, 0};
    static int[] dy = {0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 크기 및 날짜 입력
        n = Integer.parseInt(st.nextToken());
        days = Integer.parseInt(st.nextToken());

        // 초기화
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = 1;
            }
        }

        // 일단 지정된 애벌레를 다 자라게 만듬
        for (int i = 1; i <= days; i++) {
            st = new StringTokenizer(br.readLine());

            int[] growSizesCnt = new int[3];
            for (int j = 0; j < 3; j++) {
                growSizesCnt[j] = Integer.parseInt(st.nextToken());
            }

            // 왼쪽 밑에서 시작해서 오른쪽 끝까지 이돟
            int nx = n;
            int ny = 0;
            int size =0;

            for(int j = 0; j < 2; j++){
                while(true){
                    nx += dx[j];
                    ny += dy[j];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= n){
                        nx -= dx[j];
                        ny -= dy[j];
                        break;
                    }

                    if(size < 3 && growSizesCnt[size] > 0){
                        map[nx][ny] += size;
                        growSizesCnt[size]--;
                        continue;
                    }

                    size++;
                    nx -= dx[j];
                    ny -= dy[j];
                }
            }
        }


        // 나머지 애벌레 적용
        for(int j = 1; j < n; j++){
            for(int i = 1; i < n; i++){

                // 방향 확인 후 최대값 찾기
                int max = findMax(i, j);

                // 적용
                map[i][j] = max;
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static int findMax(int x, int y){
        int [] fx = {0, -1, -1};
        int [] fy = {-1, -1, 0};

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < 3; i++){
            int nx = x + fx[i];
            int ny = y + fy[i];

            if(nx < 0 || ny < 0 || nx >= n || ny >= n){
                continue;
            }

            max = Math.max(max, map[nx][ny]);
        }

        return max;
    }
}
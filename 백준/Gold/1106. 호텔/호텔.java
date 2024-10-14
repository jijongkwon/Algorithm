import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, c;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 목표, 도시 개수 입력
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        // dp 설정 및 초기화
        dp = new int[c + 101];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        // 각 도시별 홍보 비용 및 그에 해당하는 고객수
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            // 최소값 구하기
            for (int j = people; j < c + 101; j++) {
                dp[j] = Math.min(dp[j], cost + dp[j - people]);
            }
        }

        // 초과 수도 확인
        int result = Integer.MAX_VALUE;
        for (int i = c; i < c + 101; i++) {
            result = Math.min(result, dp[i]);
        }

        // 출력
        System.out.println(result);
    }
}
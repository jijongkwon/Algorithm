import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 1];

        for(int i = 0; i < n; i++){
            if(i + map[i][0] <= n){
                dp[i + map[i][0]] = Math.max(dp[i + map[i][0]], dp[i] + map[i][1]);
            }

            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }

        System.out.println(dp[n]);
    }
}
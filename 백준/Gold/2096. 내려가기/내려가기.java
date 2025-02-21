import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] max = new int[n + 1][3];
        int[][] min = new int[n + 1][3];
        int[][] map = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            max[i][0] = Math.max(max[i - 1][0], max[i - 1][1]) + map[i][0];
            max[i][1] = Math.max(Math.max(max[i - 1][0], max[i - 1][1]), max[i - 1][2]) + map[i][1];
            max[i][2] = Math.max(max[i - 1][1], max[i - 1][2]) + map[i][2];

            min[i][0] = Math.min(min[i - 1][0], min[i - 1][1]) + map[i][0];
            min[i][1] = Math.min(Math.min(min[i - 1][0], min[i - 1][1]), min[i - 1][2]) + map[i][1];
            min[i][2] = Math.min(min[i - 1][1], min[i - 1][2]) + map[i][2];
        }

        int minAns = Integer.MAX_VALUE;
        int maxAns = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            minAns = Math.min(minAns, min[n][i]);
            maxAns = Math.max(maxAns, max[n][i]);
        }

        System.out.println(maxAns + " " + minAns);
    }
}
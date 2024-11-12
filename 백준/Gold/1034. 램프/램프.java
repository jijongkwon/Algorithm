import java.io.*;
import java.util.*;

/**
 *
 */
public class Main {
    static int n, m, k;
    static int[][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 초기화
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String temp = br.readLine();

            for (int j = 0; j < m; j++) {
                map[i][j] = (int) temp.charAt(j) - '0';
            }
        }

        k = Integer.parseInt(br.readLine());

//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }

        for (int i = 0; i < n; i++) {
            int zeroCount = 0;

            for (int j = 0; j < m; j++) {
                if(map[i][j] == 0){
                    zeroCount++;
                }
            }

            int samePattern = 0;

            if(zeroCount <= k && zeroCount % 2 == k % 2){
                for (int j = 0; j < n; j++) {
                    if(isSamePattern(map[i], map[j])){
                        samePattern++;
                    }
                }
            }

            answer = Math.max(answer, samePattern);
        }

        System.out.println(answer);
    }

    static boolean isSamePattern(int[] arr1, int[] arr2){
        for (int i = 0; i < m; i++) {
            if(arr1[i] != arr2[i]){
                return false;
            }
        }

        return true;
    }
}
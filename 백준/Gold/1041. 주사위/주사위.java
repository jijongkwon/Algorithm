import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 주사위 n^3 만큼 가지고 있음
 * 1 <= N <= 1,000,000, number <= 50
 *
 * return 5면의 수의 최솟값을 출력 ( long )
 *
 * 풀이
 * 1. 제일 위의 모서리는 3면을 확인
 * 2. 나머지 모서리는 2면을 확인 ( 제일 밑 모서리 제외 )
 * 3. 그 외는 1면만 확인
 * 4. 규칙
 *   - 제일 위의 모서리는 항상 4개
 *   - 그 외 모서리
 *     - 2 -> 4
 *     - 3 -> 12
 *     - 4 -> 20
 *     - (n - 1) * 4 + (n - 2) * 4
 *     - 8n - 12
 *
 *   - 나머지 면
 *     - 5 * n ^ 2 - 12 - (2 * (8 * n - 12 ))
 *     - 5 * n ^ 2 - (16 * n) + 12
 *
 * 5. 최솟값
 *  - 4개에 모서리에는 붙어있는 3면에 합이 가장 작은 것을 선택
 *  - 8n - 12 모서리에서는 붙어있는 2명에 합이 가장 작은 것을 선택
 *  - 나머지는 가장작은 값을 선택
 *
 *  합을 구함
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] dice = new int[6];

        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        if(n == 1){
            int answer = 0;
            Arrays.sort(dice);
            for (int i = 0; i < 5; i++) {
                answer += dice[i];
            }

            System.out.println(answer);
            return;
        }

        // 3면에서의 최솟값
        int threeMin = 0;

        for (int i = 0; i < 3; i++) {
            int min = Math.min(dice[i], dice[5 - i]);
            threeMin += min;
        }

        // 2면에서의 최솟값
        int twoMin = Integer.MAX_VALUE;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if(j + i != 5 && j != i){
                    twoMin = Math.min(twoMin, dice[i] + dice[j]);
                }
            }
        }

        // 1 면에서의 최솟값
        int oneMin = Integer.MAX_VALUE;
        for (int i = 0; i < 6 ; i++) {
            oneMin = Math.min(dice[i], oneMin);
        }

        // 최솟값
        long answer = 0;
        answer += (4L * (long)threeMin);
        answer += ((8L * n - 12) * (long)twoMin);
        answer += (long) (5 * Math.pow(n, 2) - (16L * n) + 12) * (long) oneMin;

        System.out.println(answer);
    }
}
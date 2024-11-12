import java.io.*;
import java.util.*;

/**
 * 1. 연속된 두개의 원소만 교환할 수 있다.
 * 2. s 번 가능하다
 *
 * 가준은 앞에가 사전으로 가장 큰 수
 *
 * S 최대 1,000,000번 교환 가능
 * on^2
 *
 * 풀이
 * 1. 이거 버블 아님 ?
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];

        // 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int count = Integer.parseInt(br.readLine());

        // 좌측 값이 가장 크게 오게 만들기
        int cur = 0;
        while(cur < n - 1 && count > 0){
            int max = numbers[cur];
            int idx = -1;

            for (int i = cur + 1; i < cur + count + 1; i++) {
               if(i >= n){
                   break;
               }

               if(max < numbers[i]){
                    idx = i;
                    max = numbers[i];
               }
            }

            if(idx == -1){
                cur++;
                continue;
            }

            count -= idx - cur;

            // 이동
            for (int i = idx; i > cur ; i--) {
                int temp = numbers[i];
                numbers[i] = numbers[i - 1];
                numbers[i - 1] = temp;
            }

            cur++;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
}
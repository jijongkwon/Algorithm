import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * K 개의 랜선 길이가 제 각각
 * 모두 N 개의 같은 길이의 랜선 목표 -> k개의 랜선을 잘라서 만듬 (자른 래선은 붙일 수 없음 버림)
 *
 * 만들 수 있는 최대 랜선 길이
 *
 * 이분 탐색
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        long start = 0;
        long end = 0;

        int[] lans = new int[k];
        for (int i = 0; i < k; i++) {
            lans[i] = Integer.parseInt(br.readLine());
            end = Math.max(lans[i], end);
        }

        end++;
        long maxLength = Integer.MIN_VALUE;
        while(start < end){
            long mid = (start + end) / 2;
            long count = 0;

            for (int i = 0; i < k; i++) {
                count += (lans[i] / mid);
            }

            if(count >= n){
                maxLength = Math.max(maxLength, mid);
                start = mid + 1;
            }
            else{
                end = mid;
            }
        }

        System.out.println(maxLength);
    }
}
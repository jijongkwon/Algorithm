import java.math.BigInteger;
import java.util.Scanner;

;
;

/**
 * @author 지종권
 * @date 2024-01-29
 * @link https://www.acmicpc.net/problem/1914
 * @keyword_solution 재귀
 * @input 첫째 줄에 첫 번째 장대에 쌓인 원판의 개수 N (1 ≤ N ≤ 100)이 주어진다.
 * @output 첫째 줄에 옮긴 횟수 K를 출력한다.
 * N이 20 이하인 입력에 대해서는 두 번째 줄부터 수행 과정을 출력한다. 두 번째 줄부터 K개의 줄에 걸쳐 두 정수 A B를 빈칸을 사이에 두고 출력하는데, 이는 A번째 탑의 가장 위에 있는 원판을 B번째 탑의 가장 위로 옮긴다는 뜻이다. N이 20보다 큰 경우에는 과정은 출력할 필요가 없다.
 * @time_complex
 * @perf
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 2 ^ 20 까지의 수가 올 수 있으므로 BigInteger 사용
        BigInteger bi = new BigInteger("2");
        BigInteger result = bi.pow(n).subtract(BigInteger.ONE);
        System.out.println(result);
        
        if(n <= 20){
            reqHanoi(n, 1, 3, 2);
        }
    }

    static void reqHanoi(int n, int start, int end, int other) {
        if (n == 1) {
            System.out.println(start + " " + end);
            return;
        }

        reqHanoi(n - 1, start, other, end);

        System.out.println(start + " " + end);

        reqHanoi(n - 1, other, end, start);
    }
}
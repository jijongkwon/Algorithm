import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 지종권
 * @date 2024. 2. 5.
 * @link https://www.acmicpc.net/problem/2493
 * @keyword_solution 
 * stack (앞, 뒤 비교)
 * @input 
 * @output   
 * @time_complex nlogn 이하
 * @perf 
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        Stack<int[]> stack = new Stack<>();
        for(int i = 1; i <= n; i++) {
            int top = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty()) {
                if(stack.peek()[1] >= top) {
                    sb.append(stack.peek()[0] + " ");
                    break;
                }
                stack.pop();
            }
            if(stack.isEmpty()) {
                sb.append(0 + " ");
            }
            stack.push(new int[] {i, top}); 
        }
        
        System.out.println(sb.toString());
    }
}
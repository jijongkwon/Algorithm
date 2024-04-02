import java.io.*;
import java.util.HashMap;
/**
 * BOJ 2086번 피보나치 수의 합
 */
public class Main {
    static long a, b;
    static String[] strs;
    static HashMap<Long, Long> hm;

    static long MOD = 1000000000;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	strs = br.readLine().split(" ");
        a = Long.parseLong(strs[0]);
        b = Long.parseLong(strs[1]);
        hm = new HashMap<>();
        hm.put((long) 1, (long) 1);
        hm.put((long) 2, (long) 1);
        hm.put((long) 3, (long) 2);

        long f1 = fibo(a + 1);
        long f2 = fibo(b + 2);
        long r = (f2 - f1 + MOD) % MOD;

        System.out.println(r);
    }
    
    static long fibo(long idx) {
        if (hm.containsKey(idx)) {
            return hm.get(idx);
        }
        // idx가 even 짝수
        else if ((idx & 1) == 0) {
            long nIdx = idx / 2;
            long f1 = fibo(nIdx - 1);
            long f2 = fibo(nIdx);
            long tf = ((2 * f1) + f2) * f2;

            tf = tf % MOD;
            hm.put(idx, tf);

            return tf;
        }
        // idx가 odd 홀 수
        else {
            long nIdx = (idx + 1) / 2;
            long f1 = fibo(nIdx);
            long f2 = fibo(nIdx - 1);
            long tf = (f1 * f1) + (f2 * f2);
            tf = tf % MOD;

            tf = tf % MOD;
            hm.put(idx, tf);

            return tf;
        }
    }
}
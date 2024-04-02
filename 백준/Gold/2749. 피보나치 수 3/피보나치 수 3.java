import java.util.*;
import java.io.*;
import java.math.BigInteger;

/**
 * 피보나치 수 구하기 
 */
public class Main {
	static final int MOD = 1000000;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		
		// 주기를 구하자
		int p = MOD / 10 * 15;
		int[] fibo = new int[p];
		long index = n % p;
		
		fibo[0] = 0;
		fibo[1] = 1;
		for(int i = 2; i < p; i++) {
			fibo[i] = fibo[i - 1 ] + fibo[i - 2];
			fibo[i] %= MOD;
		}
		
		System.out.println(fibo[(int)index]);
	}
}
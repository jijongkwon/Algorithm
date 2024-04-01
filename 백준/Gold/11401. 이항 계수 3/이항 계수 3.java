import java.util.*;
import java.io.*;

public class Main {
	static final long MOD = 1000000007;
	static long n, r;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Long.parseLong(st.nextToken());
		r = Long.parseLong(st.nextToken());

		// 분자
		long number = factorial(n);

		// 분모
		long denom = factorial(r) * factorial(n - r) % MOD;

		long result = number * pow(denom, MOD - 2) % MOD;

		System.out.println(result);
	}

	static long factorial(long n) {
		long fac = 1L;

		while (n > 1) {
			fac = (fac * n) % MOD;
			n--;
		}

		return fac;
	}

	public static long pow(long base, long expo) {
		if (expo == 1) {
			return base % MOD;
		}

		long temp = pow(base, expo / 2);
		if (expo % 2 == 1) {
			return (temp * temp % MOD) * base % MOD;
		}
		return temp * temp % MOD;

	}
}
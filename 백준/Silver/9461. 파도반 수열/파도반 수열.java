import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		for (int i = 0; i < t; i++) {
			int n = sc.nextInt();

			long[] dp = new long[n + 1];

			for (int j = 1; j <= n; j++) {
				dp[j] = 1;
			}

			if (n <= 3) {
				System.out.println(dp[n]);
				continue;
			}

			for (int j = 4; j <= n; j++) {
				if (j <= 5) {
					dp[j] = 2;
					continue;
				}

				dp[j] = dp[j - 1] + dp[j - 5];
			}

			System.out.println(dp[n]);
		}

	}

}
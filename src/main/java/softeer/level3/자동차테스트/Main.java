package softeer.level3.자동차테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, q;
    static int[] cars;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        cars = new int[n];
        for (int i = 0; i < n; i++) {
            cars[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cars);

        for (int i = 0; i < q; i++) {
            int m = Integer.parseInt(br.readLine());

            int ans = 0;
            int index = binarySearch(m);

            if (index >= 0) {
                ans = index * (n - index - 1);
            }

            System.out.println(ans);
        }
    }

    private static int binarySearch(int value) {
        int left = 0;
        int right = cars.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (cars[mid] == value) {
                return mid;
            } else if (cars[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}

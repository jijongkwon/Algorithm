import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, C;
    static int[] homes;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // home locations
        homes = new int[N];
        for (int i = 0; i < N; i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }

        // sort
        Arrays.sort(homes);

        // binary search
        int min = 1;
        int mid = 1;
        int max = homes[N - 1] - homes[0] + 1;

        while(min < max){
            mid = (max + min) / 2;

            // 설치 가능한 개수
            int start = homes[0];
            int count = 1;

            for (int i = 1; i < homes.length; i++) {
                if(homes[i] - start >= mid){
                    count++;
                    start = homes[i];
                }
            }

            // 공유기 개수보다 적을 때
            if(count < C){
                max = mid;
                continue;
            }

            min = mid + 1;
        }

        System.out.println(min - 1);
    }
}
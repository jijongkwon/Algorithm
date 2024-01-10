package baekjoon.silver.silver1.silver_14889;
import java.util.Scanner;

/**
 * solution :
 * 1.
 * big O :
 * return :
 */
public class Silver_14889 {

    static int N;
    static int[][] map;
    static boolean[] visit;

    static int Min = Integer.MAX_VALUE;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        map = new int[N][N];
        visit = new boolean[N];


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        combi(0, 0);
        System.out.println(Min);

    }

    static void combi(int idx, int count) {
        if(count == N / 2) {
            diff();
            return;
        }

        for(int i = idx; i < N; i++) {

            if(!visit[i]) {
                visit[i] = true;
                combi(i + 1, count + 1);
                visit[i] = false;
            }
        }
    }

    static void diff() {
        int team_start = 0;
        int team_link = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {

                if (visit[i] == true && visit[j] == true) {
                    team_start += map[i][j];
                    team_start += map[j][i];
                }

                else if (visit[i] == false && visit[j] == false) {
                    team_link += map[i][j];
                    team_link += map[j][i];
                }
            }
        }

        int diff = Math.abs(team_start - team_link);


        Min = Math.min(diff, Min);

    }

}

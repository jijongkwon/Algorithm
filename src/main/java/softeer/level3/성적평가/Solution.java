package softeer.level3.성적평가;

import java.util.*;
import java.io.*;

/*
    시간복잡도 : O(nlog) 부터
*/
public class Solution
{
    static class Contest{
        int number;
        int score;

        Contest(int number, int score){
            this.number = number;
            this.score = score;
        }
    }

    public static void main(String args[]) throws IOException
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        Contest[][] contest = new Contest[4][n];
        for(int i=0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                contest[i][j] = new Contest(j, Integer.parseInt(st.nextToken())); //참가자 번호, 점수
            }
        }


        for(int i=0; i<n; i++){
            int sum = 0;
            for(int j=0; j<3; j++){
                sum += contest[j][i].score;
            }
            contest[3][i] = new Contest(i, sum);
        }

        for(Contest[] con:contest){
            Arrays.sort(con, (o1, o2) -> {
                return o2.score - o1.score;
            });
        }

        // 점수 비교
        int[][] ranks = new int[4][n];

        for(int i = 0; i < 4; i++){
            int rank = 1;
            int cnt = 1;
            ranks[i][contest[i][0].number] = rank;

            for(int j = 1; j < n; j++){
                if(contest[i][j-1].score == contest[i][j].score){
                    ranks[i][contest[i][j].number] = rank;
                    cnt++;
                    continue;
                }
                rank += cnt;
                ranks[i][contest[i][j].number] = rank;
                cnt = 1;
            }
        }

        //출력
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < n; j++){
                System.out.print(ranks[i][j] + " ");
            }
            System.out.println();
        }
    }
}

package softeer.level2.GBC;

import java.util.*;

/*
    시간복잡도 여유
    제한속도에서 벗어난 속도 최대값 구하기
*/
public class Solution
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        // N, M 입력
        int N = sc.nextInt();
        int M = sc.nextInt();

        // 구간 및 속도 입력
        int[][] original = new int[N][2];
        int[][] test = new int[M][2];

        for(int i = 0; i < N; i++){
            int part = sc.nextInt();
            int speed = sc.nextInt();

            original[i][0] = part;
            original[i][1] = speed;
        }

        for(int i = 0; i < M; i++){
            int part = sc.nextInt();
            int speed = sc.nextInt();

            test[i][0] = part;
            test[i][1] = speed;
        }

        // 속도 최대값 구하기
        int max = 0;
        int originalIndex = 0;
        int testIndex = 0;

        while(originalIndex < N&& testIndex < M){
            int originalPart = original[originalIndex][0];
            int originalSpeed = original[originalIndex][1];
            int testPart = test[testIndex][0];
            int testSpeed = test[testIndex][1];

            // 속도 구간 처리
            if(testSpeed > originalSpeed){
                max = Math.max(max, testSpeed - originalSpeed);
            }

            // 높이 구간 처리
            if(testPart == originalPart){
                originalIndex++;
                testIndex++;
            }
            else if(testPart > originalPart){
                test[testIndex][0] -= originalPart;
                originalIndex++;
            }
            else{
                original[originalIndex][0] -= testPart;
                testIndex++;
            }
        }

        System.out.println(max);
    }
}

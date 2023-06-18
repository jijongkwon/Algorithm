package programmers.level2.두원사이의정수쌍;

import java.util.*;

/*
    시간복잡도 : N log이하
    return: 교집합 제외 점의 개수

    1. 반지름 제곱 값 구함
    2. y2 - y1의 값을 구함
    3. return 4 * (안쪽 점 + 반지름 차이 + 1)
*/
class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long powR1 = (long)Math.pow(r1,2);
        long powR2 = (long)Math.pow(r2,2);

        for(int i = 1; i <= r2; i++){
            long side = 0;
            long powX = (long)Math.pow(i, 2);
            long y1 = (long)Math.sqrt(powR1 - powX);
            long y2 = (long)Math.sqrt(powR2 - powX);

            if(Math.sqrt(powR1 - powX) % 1 == 0 && y1 != 0){
                side++;
            }

            answer += 4 * (y2 - y1 + side);
        }

        answer += 4 * (r2 - r1 + 1);

        return answer;
    }
}

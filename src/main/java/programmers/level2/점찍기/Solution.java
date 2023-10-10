package programmers.level2.점찍기;

import java.util.*;

/*
    solution:
    1. 제곱 길이 계산 후 제곱d와 거리 비교
    2. d보다 이하면 개수 증가

    bigO: log n
    return: 찍히는 점의 총 개수
*/
class Solution {
    public long solution(int k, int d) {
        long answer = 0;

        // x축을 증가시키면서 y축 길이가 조건에 일치하는지 찾기
        for(int x = 0; x <= d; x += k){
            long y = (long)Math.pow(d, 2) - (long)Math.pow(x, 2);
            y = (int)Math.sqrt(y);

            answer += (y/k) + 1;
        }

        return answer;
    }
}
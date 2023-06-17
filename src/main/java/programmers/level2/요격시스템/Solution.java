package programmers.level2.요격시스템;

import java.util.*;

/*
    시간 복잡도 : nlog 이하
    A 발사 폭격 미사일 x축에 평행한 직성 모양 (s,e)
    B x -> y 축에 수평이 되도록 미사일 발사, x 좌표에 걸쳐있는 모든 폭격 미사일 요격

    s, e 요격 x, 실수 값 가능

    1. s기준으로 오름차순 정렬
    2. (s,e) 범위 내에 다음 요격이 있는 지 확인
    3. 요격이 없으면 count + 1하고 넘김
    4. 요격이 있으면 taget 제거
*/
class Solution {
    public int solution(int[][] targets) {

        // s기준으로 오름차순 정렬
        Arrays.sort(targets, (a, b) ->{
            return a[0] - b[0];
        });

        // 최소값 구하기
        int answer = 0;
        int location = 0;
        int s = targets[location][0];
        int e = targets[location][1];

        while(location < targets.length - 1){
            int ns = targets[location + 1][0];
            int ne = targets[location + 1][1];

            if(ns >= e){
                answer++;
                e = ne;
                s = ns;
                continue;
            }

            if(ns > s){
                s = ns;
            }

            if(ne < e){
                e = ne;
            }

            location++;
        }

        return answer + 1;
    }
}
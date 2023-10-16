package programmers.level2.양궁대회;

/*
    solution:
    1. 백트래킹 사용
    2. 얕은 복사를 통해 값 갱신

    bigO : 2 ^ n 이하
    return 가장 큰 점수차가 나오는 과녁정보
*/
class Solution {
    final int GAME_POINT_RANGE = 11;
    int max = Integer.MIN_VALUE;
    int[] lionPoint;
    int[] answer;

    public int[] solution(int n, int[] info) {
        lionPoint = new int[GAME_POINT_RANGE];
        backTracking(0, info, n);

        if(max <= 0){
            return new int[]{-1};
        }

        return answer;
    }

    public void backTracking(int index, int[] info, int count){

        // 화살 끝
        if(index == count){
            int diff = score(info);

            if(max<=diff) {
                max = diff;
                answer = lionPoint.clone();
            }
            return;
        }

        // 화살 추가
        for(int i = 0 ; i < GAME_POINT_RANGE && lionPoint[i] <= info[i]; i++){
            lionPoint[i] += 1;
            backTracking(index + 1, info, count);
            lionPoint[i] -= 1;
        }
    }

    //점수차 구하기
    public int score(int[] info) {
        int apeach=0, lion=0;
        for(int i=0; i<GAME_POINT_RANGE; i++) {

            if(info[i]==0 && lionPoint[i]==0)
                continue;//i원소에 둘다 0개 맞췄을땐 무시.

            if(info[i]>=lionPoint[i]) {
                apeach += (10-i);
                continue;
            }

            lion += (10-i);
        }

        int diff = lion - apeach;

        return diff;
    }
}
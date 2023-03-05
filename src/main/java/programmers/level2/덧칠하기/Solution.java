package programmers.level2.덧칠하기;

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int lastPainted = 0;

        for(int i = 0; i < section.length; i++){
            if(lastPainted < section[i]){
                lastPainted = section[i] + m - 1;
                answer++;
            }
        }

        return answer;
    }
}

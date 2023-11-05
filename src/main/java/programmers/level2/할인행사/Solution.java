package programmers.level2.할인행사;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        for(int i = 0; i < discount.length - 9; i++){
            boolean flag = true;

            for(int j = 0; j < want.length; j++){
                int check = 0;
                for(int k = 0; k < 10; k++){
                    if(want[j].equals(discount[i + k])){
                        check++;
                    }
                }

                if(check < number[j]){
                    flag = false;
                    break;
                }
            }

            if(flag){
                answer++;
            }
        }
        return answer;
    }
}
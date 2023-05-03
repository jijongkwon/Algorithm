package programmers.level2.카펫;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        if(yellow == 1){
            answer[0] = 3;
            answer[1] = 3;
        }
        else if(checkPrime(yellow)){
            answer[0] = yellow + 2;
            answer[1] = 3;
        }
        else{
            for(int i = 2; i < yellow; i++){
                int height = i;
                int width = yellow / i;
                if(yellow % i == 0 && width >= height){
                    int checkBrown = 2 * (width + height + 2);
                    if(brown == checkBrown){
                        answer[0] = width + 2;
                        answer[1] = height + 2;
                        break;
                    }
                }
            }
        }

        return answer;
    }

    public boolean checkPrime(int number){
        for(int i = 2; i <= Math.sqrt(number); i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
}
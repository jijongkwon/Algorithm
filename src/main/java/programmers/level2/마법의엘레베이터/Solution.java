package programmers.level2.마법의엘레베이터;

class Solution {
    /*
        1. 0미만 작동하지 않음 (현재 값이 양수 값인지 항상 체크)
        2. 최솟값 구하기
    */
    public int solution(int storey) {

        // 자릿 수 구하기
        int storeyLen = String.valueOf(storey).length();

        // 층수
        int storeyValue = storey;

        // 사용한 횟수
        int count = 0;

        // 자릿수 인덱스
        int index = storeyLen - 1;

        // 사이즈 증가
        int size = 1;

        // 일의 자릿수부터 시작
        while(storeyValue != 0){
            if(index == -1 ){
                break;
            }
            // i 자릿수
            int value = String.valueOf(storeyValue).charAt(index) - '0';

            // +1의 조건
            if(value > 5){
                count += 10 - value;
                storeyValue += size * (10 - value);
                if(storeyLen < String.valueOf(storeyValue).length()){
                    index++;
                }
            }
            else{
                if(storeyLen - 1 == index && value == 5){
                    count += 10 - value;
                    storeyValue += size * (10 - value);
                    if(storeyLen < String.valueOf(storeyValue).length()){
                        index++;
                    }
                    continue;
                }
                storeyValue -= size * value;
                count += value;
            }

            size *= 10;
            index--;
        }

        return count;
    }
}

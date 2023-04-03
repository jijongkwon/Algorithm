package programmers.level2.숫자카드나누기;

import java.util.*;

class Solution {
    /*
        1. 철수가 가진 카드들에 모든 숫자를 나눌 수 있지만, 영희가 가진 카드들에서는 나눌 수 없는
            양의 정수
        2. 반대의 경우
    */
    public int solution(int[] arrayA, int[] arrayB) {
        List<Integer> numberList = new ArrayList<>();

        // 조건에 만족하는 수를 모두 구한다. (최대 공약수)

        // 1번째 조건
        numberList.add(findMax(arrayA, arrayB));

        // 2번째 조건
        numberList.add(findMax(arrayB, arrayA));

        Collections.sort(numberList, Collections.reverseOrder());

        return numberList.get(0);
    }

    public int findMax(int[] arrayA, int[] arrayB){
        boolean check = true;
        int max = arrayA[0];

        for(int i = 1; i < arrayA.length; i++){
            max = gcd(arrayA[i], max);
        }

        for(int i = 0; i < arrayB.length; i++){
            if(arrayB[i] % max == 0){
                check = false;
                break;
            }
        }

        if(check == true){
            return max;
        }

        return 0;
    }

    public int gcd(int a, int b){
        int t;
        while (a % b != 0){
            t = a % b;
            a = b;
            b = t;
        }
        return b;
    }
}

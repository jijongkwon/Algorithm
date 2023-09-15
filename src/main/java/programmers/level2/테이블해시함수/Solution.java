package programmers.level2.테이블해시함수;

import java.util.*;

/*
    Solution
    1. col번째 column값 기준으로 정렬 (값 동일시 첫번째 칼럼 값 기준으로 정렬)
    2. i번째 행 모든 값 S_i mod i 해서 합을 구함
    3. begin xor end 값 구함

    bigO : nlog 이하
    return : begin xor end
*/
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {

        // sort
        Arrays.sort(data, (o1, o2) -> {
            return o1[col - 1] == o2[col - 1] ? o2[0] - o1[0] : o1[col - 1] - o2[col - 1];
        });


        // row_begin, row_end mod sum
        List<Integer> modSums = new ArrayList<>();

        for(int i = row_begin; i <= row_end; i++){
            int modSum = 0;

            for(int j = 0; j < data[0].length; j++){
                modSum += data[i - 1][j] % i;
            }

            modSums.add(modSum);
        }

        // xor
        int xorSum = modSums.get(0);

        for(int i = 1; i < modSums.size(); i++){
            xorSum ^= modSums.get(i);
        }

        return xorSum;
    }
}
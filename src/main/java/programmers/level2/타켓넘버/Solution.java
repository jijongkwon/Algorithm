package programmers.level2.타켓넘버;

class Solution {
    /*
        탐색 알고리즘 사용
        +,- 만 사용 (부호의 개수는 number 길이와 동일)
        순서는 바꾸지 않음

    */

    public int solution(int[] numbers, int target) {

        return dfs(numbers, target, 0, 0);
    }

    public int dfs(int[] numbers, int target, int sum, int size){
        int ans = 0;

        if(numbers.length == size){
            if(sum == target){
                return 1;
            }
            return 0;
        }

        ans += dfs(numbers, target, sum + numbers[size], size + 1);
        ans += dfs(numbers, target, sum + numbers[size] * -1, size + 1);

        return ans;
    }
}

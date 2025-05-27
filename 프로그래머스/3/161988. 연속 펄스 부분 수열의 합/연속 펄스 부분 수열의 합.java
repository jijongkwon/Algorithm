/*
    펄스수열: 1과 -1이 번갈아서 나오는 수열
    
    풀이
    dp
*/
class Solution {
    public long solution(int[] sequence) {
        long answer;
        
        int size = sequence.length;
        int[] a = new int[size];
        int[] b = new int[size];
        int n = 1;
        
        for(int i = 0; i<size; i++) {
            a[i] = sequence[i]*n;
            n *= -1;
            b[i] = sequence[i]*n;
        }
        
        long[] dpA = new long[size];
        long[] dpB = new long[size];
        
        dpA[0] = a[0];
        dpB[0] = b[0];
        answer = Math.max(dpA[0], dpB[0]);
        
        for(int i = 1; i<size; i++) {
            dpA[i] = Math.max(dpA[i-1] + a[i], a[i]);
            dpB[i] = Math.max(dpB[i-1] + b[i], b[i]);
    
            long max = Math.max(dpA[i], dpB[i]);
            answer = Math.max(answer, max);
        }

        return answer;
    }
}
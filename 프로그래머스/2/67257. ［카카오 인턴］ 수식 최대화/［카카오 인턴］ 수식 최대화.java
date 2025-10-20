import java.util.*;

/*
    연산자 우선순위 구하기 (절대값 포함)
    
    1. 조합 구현
    2. 계산함수 구현
    3. 비교
*/
class Solution {
    List<String> exprList = new ArrayList<>();
    List<Long> numList = new ArrayList<>();
    List<String> unqList;
    boolean[] checked;
    long answer = 0;
    
    public long solution(String expression) {
        String num = "";
        
        for(int i = 0 ; i < expression.length(); i++){
            char c = expression.charAt(i);
            if(c=='*' || c=='+' || c=='-'){
                exprList.add(c+""); 
                numList.add(Long.parseLong(num));
                num = "";
            }
            else{
                num += c;
            }
        }
        
        numList.add(Long.parseLong(num));
        Set<String> uniqueExprs = new HashSet<>(exprList);
        unqList = new ArrayList<>(uniqueExprs);
        checked = new boolean[exprList.size()];
        
        makeCombnation(0, uniqueExprs.size(), "", expression);
        return answer;
    }
    
    // 순열
    void makeCombnation(int depth, int combExprSize, String priorExpr, String expression){
        // 종료조건
        if(depth == combExprSize){
            System.out.println(priorExpr);
            // 계산
            long value = calPrior(priorExpr);
            
            // 비교
            answer = Math.max(answer, value);
            return;
        }
        
        for(int i = 0; i < combExprSize; i++){
            if(checked[i]){
                continue;
            }
            
            checked[i] = true;
            makeCombnation(depth + 1, combExprSize, priorExpr + unqList.get(i), expression);
            checked[i] = false;
        }
    }
    
    // 우선순위 계산
    long calPrior(String priorExpr){ 
        ArrayList<String> opers = new ArrayList<String>();
        opers.addAll(exprList);
        
        ArrayList<Long> nums = new ArrayList<Long>();
        nums.addAll(numList);
        
        for(int i=0; i< priorExpr.length(); i++){
            String op = Character.toString(priorExpr.charAt(i));
            
            for(int j=0; j< opers.size(); j++){
                if(opers.get(j).equals(op)){
                    long n1 = nums.get(j);
                    long n2 = nums.get(j+1);
                    long res = cal(n1, n2, op);
                    
                    nums.remove(j+1);
                    nums.remove(j);
                    opers.remove(j);
                    
                    nums.add(j, res);
                    
                    j--;
                }
            }
        }
        
        return Math.abs(nums.get(0));
    }
    
    // 연산자 계산
    long cal(long n1, long n2, String op){
        long res = 0;
        switch(op){
            case "*": 
                res = n1 * n2;
                break;
            case "+":
                res = n1 + n2;
                break;
            case "-":
                res = n1 - n2;
                break;
        }
        
        return res;
    }
}
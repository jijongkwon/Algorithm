import java.util.*;

/*
   PQ 단순구현
*/
class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minpq = new PriorityQueue<>();
        PriorityQueue<Integer> maxpq = new PriorityQueue<>((o1, o2) -> {
            return o1.compareTo(o2) * -1;
        });
        
        for(int i = 0; i < operations.length; i++){
            String[] om = operations[i].split(" ");
            String op = om[0];
            int num = Integer.parseInt(om[1]);
            
            if(op.equals("D") && maxpq.isEmpty()){
                continue;
            }
            
            if(op.equals("I")){
                minpq.add(num);
                maxpq.add(num);
                continue;
            }
            
            if(num > 0){
                minpq.remove(maxpq.poll());
                continue;
            }
            
            maxpq.remove(minpq.poll());
        }
        
        return maxpq.isEmpty() ? new int[] {0,0} : new int[] {maxpq.poll(), minpq.poll()};
    }
}
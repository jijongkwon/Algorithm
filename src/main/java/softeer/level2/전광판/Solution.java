package softeer.level2.전광판;
import java.util.*;

/*
    시간복잡도 여유
    자연수 A -> B 로 갈 때 스위치 몇 번 누르는지
    배열을 사용하여 숫자를 표현
*/
public class Solution
{
    public static void main(String args[])
    {
        List<int[]> numbers = new ArrayList<>();
        numbers.add(new int[]{1,1,1,0,1,1,1}); // 0
        numbers.add(new int[]{0,0,1,0,0,1,0}); // 1
        numbers.add(new int[]{1,0,1,1,1,0,1}); // 2
        numbers.add(new int[]{1,0,1,1,0,1,1}); // 3
        numbers.add(new int[]{0,1,1,1,0,1,0}); // 4
        numbers.add(new int[]{1,1,0,1,0,1,1}); // 5
        numbers.add(new int[]{1,1,0,1,1,1,1}); // 6
        numbers.add(new int[]{1,1,1,0,0,1,0}); // 7
        numbers.add(new int[]{1,1,1,1,1,1,1}); // 8
        numbers.add(new int[]{1,1,1,1,0,1,1}); // 9
        numbers.add(new int[]{0,0,0,0,0,0,0}); // -

        Scanner sc = new Scanner(System.in);

        // 테스트 케이스
        int T = sc.nextInt();

        for(int i = 0; i < T; i++){
            int count = 0;
            String A = sc.next();
            String B = sc.next();


            if(A.length() != 5){
                int len = A.length();
                for(int j = 0; j < 5 - len; j++){
                    A = "-" + A;
                }
            }

            if(B.length() != 5){
                int len = B.length();
                for(int j = 0; j < 5 - len; j++){
                    B = "-" + B;
                }
            }

            for(int j = 0; j < 5; j++){
                int[] a;
                int[] b;
                if(A.charAt(j) == '-'){
                    a = numbers.get(10);
                }
                else{
                    a = numbers.get(A.charAt(j) - '0');
                }

                if(B.charAt(j) == '-'){
                    b = numbers.get(10);
                }
                else{
                    b = numbers.get(B.charAt(j) - '0');
                }


                for(int k = 0; k < 7; k++){
                    if(b[k] != a[k]){
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }
}

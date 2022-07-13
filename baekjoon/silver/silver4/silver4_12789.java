package baekjoon.silver.silver4;

import java.util.Scanner;
import java.util.Stack;

public class silver4_12789 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        int order = 1;
        int n = sc.nextInt();
        int arr[] = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        for(int i = 0; i < n; i++){// 순서가 맞는지 확인
            if (arr[i] == order){ 
                order++;
            }
            else{
                if(!stack.empty() && stack.peek() == order){ // 
                    stack.pop();
                    order++;
                    i--;
                }
                else{
                    stack.push(arr[i]);
                }
            }
        }

        for (int i = 0; i < stack.size(); i++){ 
            if(stack.pop() == order){
                order++;
            }
            else{ // 순서가 틀리면 return
                System.out.println("Sad");
                return;
            }
        }

        System.out.println("Nice");
    }
}

package softeer.level2.비밀메뉴;

import java.util.*;
import java.io.*;


public class Solution
{
    /*
        숫자 리스트 안에 비밀번호가 순서대로 있는지 확인
    */
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        String[] inputValue = sc.nextLine().split(" ");

        int M = Integer.parseInt(inputValue[0]);
        int N = Integer.parseInt(inputValue[1]);
        int K = Integer.parseInt(inputValue[2]);

        int[] secretKey = new int[M];
        int[] userKey = new int[N];
        boolean isTrue = false;

        // 시크릿 키
        for(int i = 0; i < M; i++){
            secretKey[i] = sc.nextInt();
        }

        // 유저 키
        for(int i = 0; i < N; i++){
            userKey[i] = sc.nextInt();
        }

        for(int i = 0; i < N; i++){
            if(userKey[i] == secretKey[0]){
                int index = i;
                for(int j : secretKey){
                    if(index >= N){
                        isTrue = false;
                        break;
                    }
                    if(j == userKey[index]){
                        isTrue = true;
                        index++;
                    }else{

                        isTrue = false;
                        break;
                    }
                }
            }

            if(isTrue){
                break;
            }
        }

        if(isTrue){
            System.out.println("secret");
        }else{
            System.out.println("normal");
        }
    }
}

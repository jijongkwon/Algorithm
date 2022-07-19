package baekjoon.silver.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * silver5_10845
 */
public class silver5_10845 {
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> que = new LinkedList<Integer>();
		int back = -1;

        for (int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();

            switch(order){
                case "push":
                    back = Integer.parseInt(st.nextToken());
                    que.add(back);
                    break;
                case "pop":
                    if(que.isEmpty()){System.out.println("-1");}
                    else{System.out.println(que.remove());}
                    break;
                case "size":
                    System.out.println(que.size()); 
                    break;                 
                case "empty":
                    if(que.isEmpty()){System.out.println("1");}
                    else {System.out.println("0");} 
                    break;
                case "front":
                    if(que.isEmpty()){System.out.println("-1");}
                    else {System.out.println(que.peek());} 
                    break;
                case "back":
                    if(que.isEmpty()){System.out.println("-1");}
                    else {System.out.println(back);} 
                    break;
            }
        }
        
    }
}
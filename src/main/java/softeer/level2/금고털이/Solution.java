package softeer.level2.금고털이;

import java.util.*;
import java.io.*;


public class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        ArrayList<int[]> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(in.readLine());
            list.add(new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        }

        Collections.sort(list,(o1,o2)->o2[1]-o1[1]);

        int answer = 0;

        for(int[] item:list){
            if(W<=item[0]){
                answer += W*item[1];
                break;
            }else{
                answer += item[0]*item[1];
                W-=item[0];
            }
        }

        System.out.println(answer);
    }
}

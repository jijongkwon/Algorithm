package baekjoon.silver.silver4.silver_1620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Silver_1620 {
    public static void main(String[] args) throws IOException {
        HashMap<Integer,String> pocketMonListNumber = new HashMap<>();
        HashMap<String,Integer> pocketMonListName = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // reg pocketMon
        for(int i = 1; i <= n; i++){
            String pocketMon = br.readLine();
            pocketMonListNumber.put(i,pocketMon);
            pocketMonListName.put(pocketMon,i);
        }

        // output
        for(int i = 0; i < m; i++){
            String inputForOutput = br.readLine();
            try{
                int number = Integer.parseInt(inputForOutput);
                System.out.println(pocketMonListNumber.get(number));
            }
            catch (Exception e){
                System.out.println(pocketMonListName.get(inputForOutput));
            }
        }
    }
}

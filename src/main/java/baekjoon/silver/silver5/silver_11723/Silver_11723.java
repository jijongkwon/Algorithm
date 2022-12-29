package baekjoon.silver.silver5.silver_11723;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Silver_11723 {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // number of instrument
        int n = Integer.parseInt(br.readLine());

        // run
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            String instrument = st.nextToken();

            if(instrument.equals("add")){
                int x = Integer.parseInt(st.nextToken());
                if(!list.contains(x)){
                    list.add(x);
                }
            }

            if(instrument.equals("remove")){
                int x = Integer.parseInt(st.nextToken());
                if(list.contains(x)){

                    list.remove(list.indexOf(x));
                }
            }

            if(instrument.equals("check")){
                int x = Integer.parseInt(st.nextToken());
                if(!list.contains(x)){
                    sb.append("0").append("\n");
                    continue;
                }
                sb.append("1").append("\n");
            }

            if(instrument.equals("toggle")){
                int x = Integer.parseInt(st.nextToken());
                if(list.contains(x)){
                    list.remove(list.indexOf(x));
                    continue;
                }
                list.add(x);
            }

            if(instrument.equals("all")){
                list.clear();
                for(int j = 0 ; j < 20 ; j++){
                    list.add(j+1);
                }
            }

            if(instrument.equals("empty")){
                list.clear();
            }
        }

        //print
        System.out.println(sb);
    }

}

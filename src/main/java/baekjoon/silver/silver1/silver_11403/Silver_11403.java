package baekjoon.silver.silver1.silver_11403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver_11403 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 행열 크기
        int n = Integer.parseInt(br.readLine());
        int map[][] = new int [n][n];

        // 입력
        for(int x = 0; x < n; x++){
            st = new StringTokenizer(br.readLine());
            for(int y = 0; y < n; y++){
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드 와샬
        for(int z = 0; z < n; z++){
            for(int x = 0; x < n; x++){
                for(int y = 0; y < n; y++){
                    if(map[x][z] == 1 && map[z][y] == 1){
                        map[x][y] = 1 ;
                    }
                }
            }
        }

        //출력
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.printf(sb.toString());
    }
}

package baekjoon.silver.silver1.silver_2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Silver_2468 {
    static int N;
    static int [][] nList;
    static boolean [][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 최댓값
        int max = 0;

        // 크기 설정
        N = Integer.parseInt(br.readLine());
        nList = new int[N][N];

        // 높이 설정
        int maxHeight = 0;
        for(int i = 0; i < N; i++){
            String height = br.readLine();
            String heightSplit[] = height.split(" ");
            for(int j = 0; j < N; j++){
                nList[i][j] = Integer.parseInt(heightSplit[j]);
                if(nList[i][j] > maxHeight){
                    maxHeight = nList[i][j];
                }
            }
        }

        // 높이에 따른 인접 구역 갯수 구하기
        for(int i = 0; i < maxHeight + 1; i++){
            visit = new boolean[N][N];
            int cnt = 0;
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    if(!visit[j][k] && nList[j][k] > i){
                        cnt += dfs(j,k,i);
                    }
                }
            }
            max = Math.max(max,cnt);
        }
        System.out.println(max);
    }

    public static int dfs(int x, int y, int h){

        visit[x][y] = true;

        int [] xDirection = {-1,1,0,0};
        int [] yDirection = {0,0,1,-1};

        for(int i = 0; i < 4; i++){
            int nx = x + xDirection[i];
            int ny = y + yDirection[i];
            if(nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) continue;
            if(visit[nx][ny]) continue;
            if(nList[nx][ny] > h){
                dfs(nx,ny,h);
            }
        }
        return 1;
    }
}

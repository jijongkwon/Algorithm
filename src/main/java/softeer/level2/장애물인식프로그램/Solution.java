package softeer.level2.장애물인식프로그램;

import java.util.*;
import java.io.*;

/*
    dfs or bfs
*/
public class Solution
{
    static int N;
    static String[] map;
    static boolean[][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        List<Integer> blockList = new ArrayList<>();
        int blockCount = 0;

        // 정사각형 N
        N = Integer.parseInt(br.readLine());
        map = new String[N];
        visited = new boolean[N][N];


        // 블록 입력
        for(int i = 0; i < N; i++){
            String temp = br.readLine();
            map[i] = temp;
        }

        // 블록 찾기
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j] && map[i].charAt(j) - '0' == 1){
                    int blockSize = dfs(i,j);
                    blockList.add(blockSize);
                    blockCount++;
                }
            }
        }

        // 정렬
        Collections.sort(blockList);

        // 출력
        System.out.println(blockCount);

        for(int i : blockList){
            System.out.println(i);
        }

    }

    static int dfs(int x, int y){
        int count = 1;
        visited[x][y] = true;

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N){
                continue;
            }

            if(!visited[nx][ny] && map[nx].charAt(ny) - '0' == 1){
                count += dfs(nx, ny);
            }
        }

        return count;
    }
}

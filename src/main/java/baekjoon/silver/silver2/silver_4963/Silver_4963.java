package baekjoon.silver.silver2.silver_4963;

import java.util.*;
import java.io.*;

public class Silver_4963 {
    static int map[][];
    static boolean visited[][];
    static int dx[] = {0, 0, -1 ,1, -1, 1, -1, 1};
    static int dy[] = {-1, 1, 0, 0, 1, 1, -1, -1};

    static int w, h;

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = " ";
        while( !(str = br.readLine()).equals("0 0") ) {
            st = new StringTokenizer(str);


            w = Integer.parseInt(st.nextToken()); // 너비
            h = Integer.parseInt(st.nextToken()); // 높이
            map = new int[h][w];
            visited = new boolean[h][w];

            for(int i=0; i<h; i++) {
                st = new StringTokenizer(br.readLine());

                for(int j=0; j<w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                }
            }

            int island_count = 0;
            for(int i=0; i<h; i++) {
                for(int j=0; j<w; j++) {

                    if(!visited[i][j] && map[i][j] == 1) {
                        BFS(i, j);
                        island_count++;
                    }
                }
            }

            sb.append(island_count).append('\n');
        }

        System.out.println(sb);
    } // End of main

    static void BFS(int x, int y) {
        Queue<Node> que = new LinkedList<Node>();
        visited[x][y] = true;
        que.offer(new Node(x, y));

        while( !que.isEmpty() ) {
            Node node = que.poll();

            for(int i=0; i<8; i++) {
                int nx = dx[i] + node.x;
                int ny = dy[i] + node.y;

                if(nx < 0 || ny < 0 || nx >= h || ny >= w){
                    continue;
                }

                if(!visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    que.offer(new Node(nx, ny));
                }
            }
        }

    }

}
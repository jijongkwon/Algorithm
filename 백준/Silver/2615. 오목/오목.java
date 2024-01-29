import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static boolean[][] visited = new boolean[19][19];
    static char[][] map = new char[19][19];
    static int[] dx = {1, 0, 1, -1};
    static int[] dy = {1, 1, 0, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<19; i++) {
            String input = br.readLine().replace(" ", "");

            for(int j=0; j<19; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        for(int i=0; i<19; i++) {
            for(int j=0; j<19; j++) {
                if(map[i][j]!='0') {
                    for(int k=0; k<4; k++) {
                        int cnt = bfs(i, j, k);

                        if(cnt==5) {
                            System.out.println(map[i][j]);
                            System.out.println((i+1)+" "+(j+1));
                            return;
                        }
                    }
                }
            }
        }

        System.out.println(0);
    }

    public static int bfs(int x, int y, int idx) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y, 1));
        int max = 0;

        while(!queue.isEmpty()) {
            Pair temp = queue.poll();
            max = Math.max(temp.cnt, max);

            int nx = temp.x+dx[idx];
            int ny = temp.y+dy[idx];

            if(nx<0 || nx>=19 || ny<0 || ny>=19 || map[nx][ny]!=map[temp.x][temp.y]) continue;

            queue.add(new Pair(nx, ny, temp.cnt+1));
        }

        if(max==5) {
            int nx = x-dx[idx];
            int ny = y-dy[idx];

            if(nx>=0 && nx<19 && ny>=0 && ny<19 && map[nx][ny]==map[x][y])
                max++;
        }

        return max;
    }

    public static class Pair {
        int x;
        int y;
        int cnt;

        public Pair(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
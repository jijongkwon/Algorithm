import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static List<Integer>[] graph;
    static List<Integer> fans;
    static boolean isFan = true;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n ; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
        }

        int fanNodes = Integer.parseInt(br.readLine());
        fans = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < fanNodes; i++) {
            fans.add(Integer.parseInt(st.nextToken()));
        }

        // dfs
        visited = new boolean[n + 1];
        dfs(1);

        if(isFan){
            System.out.println("Yes");
        }
        else {
            System.out.println("yes");
        }
    }

    static void dfs(int start){
        if(fans.contains(start)){
            return;
        }

        if(graph[start].isEmpty()){
            isFan = false;
            return;
        }

        visited[start] = true;

        for(int next : graph[start]){
            if(visited[next]){
                continue;
            }

            dfs(next);
        }
    }
}
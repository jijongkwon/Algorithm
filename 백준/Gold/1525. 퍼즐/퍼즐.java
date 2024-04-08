import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String puzzle;
    static Map<String , Integer> map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 퍼즐 입력
        puzzle = "";
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                puzzle += st.nextToken();
            }
        }

        // 이미 한 패턴인지 확인하는 자료구조 map
        map = new HashMap<>();
        map.put(puzzle, 0);

        // bfs로 pattern 찾기
        Queue<String> queue = new LinkedList<>();
        queue.add(puzzle);

        while(!queue.isEmpty()){
            String pattern = queue.poll();
            int move = map.get(pattern);
            int index = pattern.indexOf("0");
            int px = index / 3;
            int py = index % 3;

            if(pattern.equals("123456780")){
                System.out.println(move);
                return;
            }

            for(int i = 0; i < 4; i++){
                int nx = px + dx[i];
                int ny = py + dy[i];

                if(nx < 0 || ny < 0 || nx >= 3 || ny >= 3){
                    continue;
                }

                int loc = nx * 3 + ny;
                char ch = pattern.charAt(loc);
                String next = pattern.replace(ch, 'c');
                next = next.replace('0', ch);
                next = next.replace('c', '0');

                if(!map.containsKey(next)){
                    queue.add(next);
                    map.put(next, move + 1);
                }
            }
        }

        System.out.println(-1);
    }
}
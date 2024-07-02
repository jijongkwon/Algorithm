import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * n * n 정사각형 보드
 *
 * 칸에는 사과가 놓아져 있고
 *
 * 상하좌우 벽 ( 닿이면 게임 끝 )
 *
 * 뱀의 길이는 1에서 시작하고, 처음은 오른쪽 향함, (0,0) 에서 시작
 *
 * 길이를 늘려 머리를 다음칸에 위치
 * 벽이나 자기자신의 몸에 부딪히면 끝남
 * 사과가 있으면 사과는 없어지고, 꼬리는 움직이지 않음
 * 사과가 없으면, 몸길이 줄여서 고리가 위차한 칸을 비워줌
 *
 * 이동경로가 주어질 때 몇 초에 끝나는지 계산
 *
 * 풀이
 * 뱀 : 1 사과 : 2
 *
 *
 */


public class Main {
    // 맵
    static int size;
    static int[][] map;

    // 사과 개수
    static int appleCount;

    // 사과의 위치
    static int[][] appleLocation;

    // 뱀 움직임 ( 초, (왼쪽, 오른쪽) )
    static int snakeMoveCount;
    static String[] snakeChangeDirectionTime;

    // 우하좌상
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    // 뱀
    static List<int[]> snake;

    static boolean endGame = false;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        size = Integer.parseInt(br.readLine());
        map = new int[size + 1][size + 1];

        // 사과 저장
        appleCount = Integer.parseInt(br.readLine());

        for(int i = 0; i < appleCount; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());


            map[x][y] = 2;
        }

        // 뱀 움직임 저장
        snakeMoveCount = Integer.parseInt(br.readLine());
        snakeChangeDirectionTime = new String[10001];

        for(int i = 0; i < snakeMoveCount; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String  direction = st.nextToken();
            snakeChangeDirectionTime[time] = direction;
        }

        // 출발
        snake = new ArrayList<>();
        snake.add(new int[]{1,1});
        map[1][1] = 1;
        int direction = 0;

        while(!endGame){
            direction = checkAndReturnDirection(direction);
            moveSnake(direction);
            answer++;

//            for (int[] m : map){
//                System.out.println(Arrays.toString(m));
//            }
//
//            System.out.println();
        }

        System.out.println(answer);
    }

    // 방향 확인
    static public int checkAndReturnDirection(int dir){
        int direction = dir;

        if(answer > 10000 || snakeChangeDirectionTime[answer] == null){
            return direction;
        }

        if(snakeChangeDirectionTime[answer].equals("D")){
            direction++;
        }
        else if(snakeChangeDirectionTime[answer].equals("L")){
            direction--;
        }

        if(direction > 3){
            direction = 0;
        }
        else if(direction < 0){
            direction = 3;
        }

        return direction;
    }

    // 뱀 움직이기
    static public void moveSnake(int direction){
        int[] headLoc = snake.get(snake.size() - 1);
        int nx = headLoc[0] + dx[direction];
        int ny = headLoc[1] + dy[direction];

        if(hitWall(nx, ny) || hitBody(nx, ny)){
            endGame = true;
            return;
        }

        snake.add(new int[]{nx, ny});

        if(!checkApple(nx, ny)){
            removeTail(direction);
            map[nx][ny] = 1;
        }

        map[nx][ny] = 1;
    }

    // 다음 꼬리 찾기
    static public void removeTail(int direction){
        int[] tailLoc = snake.get(0);
        map[tailLoc[0]][tailLoc[1]] = 0;
        snake.remove(0);
    }

    // 사과가 유무 확인
    static public boolean checkApple(int x, int y){
        return map[x][y] == 2;
    }

    // 종료조건 1 : 벽에 부딪힐 때
    static public boolean hitWall(int x, int y){
        return x < 1 || y < 1 || x >= size + 1 || y >= size + 1;
    }

    // 종료조건 2: 몸통에 부딪힐 때
    static public boolean hitBody(int x, int y){
        return map[x][y] == 1;
    }
}
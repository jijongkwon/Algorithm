package baekjoon.gold.gold4.gold15686;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * solution :
 * bigO :
 * return : 최소 도시의 치킨거리 값
 */
public class Gold_15686 {
    static int min = Integer.MAX_VALUE;
    static int n, m;
    static int[][] map;
    static List<Point> house;
    static List<Point> chicken;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // input value
        n = sc.nextInt();
        m = sc.nextInt();

        // setting
        map = new int[n][n];
        house = new ArrayList<>();
        chicken = new ArrayList<>();

        // input map
        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < n; j++){
                int inputNum = sc.nextInt();
                map[i][j] = inputNum;

                if(inputNum == 1){
                    house.add(new Point(i,j));
                }

                if(inputNum == 2){
                    chicken.add(new Point(i,j));
                }
            }
        }

        visited = new boolean[chicken.size()];

        backTracking(0,0);

        System.out.println(min);
    }

    static void backTracking(int start, int depth){
        if(depth == m){
            int sum = 0;

            for(int i = 0; i < house.size(); i++){
                int lengthMin = Integer.MAX_VALUE;

                for(int j = 0; j < chicken.size(); j++){
                    if(visited[j]){
                        int length = Math.abs(house.get(i).x -chicken.get(j).x) + Math.abs(house.get(i).y - chicken.get(j).y);
                        lengthMin = Math.min(lengthMin, length);
                    }
                }
                sum += lengthMin;
            }

            min = Math.min(min, sum);
            return;
        }

        for(int i = start; i < chicken.size(); i++){
            visited[i] = true;
            backTracking(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

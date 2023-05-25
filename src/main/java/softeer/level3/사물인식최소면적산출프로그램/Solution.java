package softeer.level3.사물인식최소면적산출프로그램;

import java.util.*;

/*
    시간 복잡도 : 여유

    점 또는 선분의 넓이 = 0
    색깔을 적어도 하나씩 포함하는 가장 작은 직사각형 넓이 출력
*/
public class Solution
{
    static int min = Integer.MAX_VALUE;
    static List<List<int[]>> datList;

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        // 점의 개수 n, 색깔 개수 k
        int n = sc.nextInt();
        int k = sc.nextInt();

        datList = new ArrayList<>();

        for(int i = 0; i <= k; i++){
            datList.add(new ArrayList<>());
        }

        // 점의 정보 저장
        for(int i = 0; i < n; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int c = sc.nextInt();

            datList.get(c).add(new int[]{x,y});
        }

        for(int[] point : datList.get(1)){
            calBox(point[0], point[1], point[0], point[1], 2, min, k);
        }

        System.out.println(min);
    }

    static public void calBox(int x, int y, int x1, int y1, int cur, int value, int color){
        if(cur == color + 1){
            min = Math.min(min, value);
            return;
        }

        // k번 반복
        for(int[] point : datList.get(cur)){
            int xMax = Math.max(x, point[0]);
            int yMax = Math.max(y, point[1]);
            int xMin = Math.min(x1, point[0]);
            int yMin = Math.min(y1, point[1]);

            int width = xMax - xMin;
            int height = yMax - yMin;

            value = width * height;

            if(min > value){
                calBox(xMax,yMax,xMin,yMin,cur + 1,value,color);
            }

        }
    }

}
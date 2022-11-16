package baekjoon.gold.gold4.gold_1027;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Gold_1027 {
    static List<Integer> buildingList;
    static List<Integer> visible;
    static int buildingNumber;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        buildingList = new ArrayList<>();
        visible = new ArrayList<>();
        // 빌딩 수 입력
        buildingNumber = sc.nextInt();

        // 빌딩 높이 입력
        for (int i = 0; i < buildingNumber; i++) {
            buildingList.add(sc.nextInt());
            visible.add(0);
        }

        // 최고층 빌딩 구하기
        countBuilding();
        Collections.sort(visible);

        System.out.println(visible.get(visible.size() - 1));

    }


    private static void countBuilding() {
        for (int i = 0; i < buildingNumber - 1; i++) {
            //건물 보이는 건물 수 증가
            visible.set(i, visible.get(i) + 1);
            visible.set(i + 1, visible.get(i + 1) + 1);

            //기울기
            double lean = calculateLean(i + 1, i);

            for (int j = i + 2; j < buildingNumber; j++) {
                double nextLean = calculateLean(j, i);
                if (lean >= nextLean) {
                    continue;
                }
                lean = nextLean;
                visible.set(i, visible.get(i) + 1);
                visible.set(j, visible.get(j) + 1);
            }
        }
    }

    private static double calculateLean(int x, int y) {
        return (double) (buildingList.get(y) - buildingList.get(x)) / (y - x);
    }
}

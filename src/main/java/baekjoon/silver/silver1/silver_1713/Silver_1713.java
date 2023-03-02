package baekjoon.silver.silver1.silver_1713;

import java.util.*;

public class Silver_1713 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 학생번호별 추천 횟수
        int[] student = new int[101];

        // 사진틀
        List<Integer> pictureBox = new ArrayList<>();

        // 사진틀 갯수
        int N = sc.nextInt();

        // 추천 갯수
        int M = sc.nextInt();

        // 추천
        for(int i = 0; i < M ; i++){
            int studentChoice = sc.nextInt();

            // 이미 사진틀 안에 있는 경우
            if (pictureBox.contains(studentChoice)) {
                student[studentChoice] += 1;
                continue;
            }

            // 사진틀이 비어 있는 경우
            if(pictureBox.size() < N){
                pictureBox.add(studentChoice);
                student[studentChoice] += 1;
                continue;
            }

            // 추천 회수가 적은 학생 선택 후 삭제, 오래된 학생은 인덱스가 작은 순으로 삭제
            int min = M + 1;

            for(int j = 0 ; j < pictureBox.size(); j++){
                if(min > student[pictureBox.get(j)]){
                    min = student[pictureBox.get(j)];
                }
            }

            for(int j = 0 ; j < pictureBox.size(); j++){
                if(min == student[pictureBox.get(j)]){
                    student[pictureBox.get(j)] = 0;
                    pictureBox.remove(j);
                    pictureBox.add(studentChoice);
                    student[studentChoice] += 1;
                    break;
                }
            }
        }

        Collections.sort(pictureBox);

        for(int i = 0; i < pictureBox.size(); i++){
            System.out.print(pictureBox.get(i) + " ");
        }
    }
}
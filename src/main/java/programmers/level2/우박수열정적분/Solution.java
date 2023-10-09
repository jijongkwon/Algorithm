package programmers.level2.우박수열정적분;

import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {

        // k에 따른 그래프 그리기
        int input = k;
        List<Integer> graph = new ArrayList<>();
        graph.add(input);

        while (input > 1){
            if(input % 2 == 0){
                input /= 2;
                graph.add(input);
            }
            else{
                input = input * 3 + 1;
                graph.add(input);
            }
        }

        // 구간별 넓이 누적합 구하기
        List<Double> areas = new ArrayList<>();
        areas.add(0.0);

        for(int i = 0; i < graph.size() - 1; i++){
            int length = graph.get(i);
            int height = graph.get(i + 1);
            double area = (double)(length + height) / 2;

            if(i == 0){
                areas.add(area);
                continue;
            }

            double prior = areas.get(i);
            areas.add(prior + area);
        }

        // 적정분 결과값 구하기
        double[] answer = new double[ranges.length];

        for(int i = 0; i < ranges.length; i++){
            int a = ranges[i][0];
            int b = areas.size() + ranges[i][1] - 1;

            if(a > b){
                answer[i] = -1.0;
                continue;
            }

            double result = areas.get(b) - areas.get(a);
            answer[i] = result;
        }


        return answer;
    }
}
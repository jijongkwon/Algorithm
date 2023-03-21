package programmers.level1.신고결과받기;

import java.util.*;

class Solution {
    /*
        조건
        1. 한 번에 한 명 신고 가능
        2. 한 유저를 여러번 신고할 수 있으나 1회로 처리
        3. 이용자id와 신고한id는 공백(스페이스)하나로 구분

        해결방안
        1. 신고 내용에 따라 아이디에 따른 신고 횟수 저장 (hashMap 사용)
        2. 다중 리스트를 이용하여 누가 누구를 신고했는지 저장
        3. 한 유저를 여러번 신고한 사례가 있는지 검사
        4. 신고 횟수가 k번 이상인지 검사
        5. 처리 결과 리턴

    */
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        HashMap<String, Integer> idAndCount = new HashMap<>();

        // 순서대로 저장
        List<List<String>> reportList = new ArrayList<>();

        // 초기 값 설정
        for(int i = 0; i < id_list.length; i++){
            idAndCount.put(id_list[i], 0);
            reportList.add(new ArrayList<String>());
        }

        // 신고 내용 확인
        for(int i = 0; i < report.length; i++){
            int index = 0;

            // 공백으로 분리
            String[] reportAndreported = report[i].split(" ");

            // 리스트에서 확인
            for(int j = 0; j < id_list.length; j++){
                if(id_list[j].equals(reportAndreported[0])){
                    break;
                }
                index++;
            }

            // 신고 횟수 넣기
            if(!reportList.get(index).contains(reportAndreported[1])){
                reportList.get(index).add(reportAndreported[1]);

                // index 찾기 넣기
                for(int j = 0; j < id_list.length; j++){
                    if(id_list[j].equals(reportAndreported[1])){
                        int temp = idAndCount.get(id_list[j]) + 1;
                        idAndCount.put(id_list[j], temp);
                        break;
                    }
                }
            }
        }

        List<String> reportedPeople = new ArrayList<>();

        // k번 이상인지 확인
        for(String name : idAndCount.keySet()){
            if(idAndCount.get(name) >= k){

                reportedPeople.add(name);
            }
        }

        // answer에 저장
        for(int i = 0; i < reportList.size(); i++){
            for(int j = 0 ; j < reportedPeople.size(); j++){
                if(reportList.get(i).contains(reportedPeople.get(j))){
                    answer[i] += 1;
                }
            }
        }
        return answer;
    }
}

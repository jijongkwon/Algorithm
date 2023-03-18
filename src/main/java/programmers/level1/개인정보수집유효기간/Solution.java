package programmers.level1.개인정보수집유효기간;

import java.util.*;

class Solution {
    /*
        1. string 분리
        2. 월을 년으로 변환
        3. 폐기 날짜 확인
    */
    public int[] solution(String today, String[] terms, String[] privacies) {

        // 폐기 처리할 목록들
        List<Integer> endList = new ArrayList<>();

        // 유통기간까지 더한 날짜 (년,월)
        List<String> endTerms = new ArrayList<>();

        // 분리
        String[] splitToday = today.split("\\.");

        // 폐기 목록 찾기
        for(int i = 0; i < privacies.length; i++){
            String[] tempPrivacies = privacies[i].split(" ");

            for(int j = 0; j < terms.length; j++){
                String[] tempTerms = terms[j].split(" ");

                // 문자가 같으면 날짜를 더함
                if(tempTerms[0].equals(tempPrivacies[1])){
                    String[] tempPrivaciesDate = tempPrivacies[0].split("\\.");
                    int year = Integer.parseInt(tempPrivaciesDate[0]);
                    int day = Integer.parseInt(tempPrivaciesDate[2]);
                    int PrivaciesMonth = Integer.parseInt(tempPrivaciesDate[1]);
                    int addMonth = Integer.parseInt(tempTerms[1]);
                    int sumMonth = PrivaciesMonth + addMonth;

                    // 월을 년으로 변환
                    int month = sumMonth % 12;
                    year += sumMonth / 12;

                    if(month == 0){
                        month = 12;
                        year -= 1;
                    }


                    String date = Integer.toString(year) + "." + Integer.toString(month) + "." + Integer.toString(day);

                    endTerms.add(date);
                    continue;
                }
            }

        }

        // 비교
        for(int i = 0; i < endTerms.size(); i++){
            String[] tempEnd = endTerms.get(i).split("\\.");
            // 년 비교
            if(Integer.parseInt(splitToday[0]) > Integer.parseInt(tempEnd[0])){
                endList.add(i+1);
                continue;
            }

            // 월 비교
            if(Integer.parseInt(splitToday[0]) == Integer.parseInt(tempEnd[0])
                    && Integer.parseInt(splitToday[1]) > Integer.parseInt(tempEnd[1])){
                endList.add(i+1);
                continue;
            }

            // 일 비교
            if(Integer.parseInt(splitToday[0]) == Integer.parseInt(tempEnd[0])
                    && Integer.parseInt(splitToday[1]) == Integer.parseInt(tempEnd[1]) && Integer.parseInt(splitToday[2]) >= Integer.parseInt(tempEnd[2])){
                endList.add(i+1);
                continue;
            }
        }

        int answer[] = new int[endList.size()];

        for(int i = 0; i < endList.size(); i++){
            answer[i] = endList.get(i);
        }

        return answer;
    }
}

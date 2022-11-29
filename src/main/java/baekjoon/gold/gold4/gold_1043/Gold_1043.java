package baekjoon.gold.gold4.gold_1043;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gold_1043 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> truePeopleList = new ArrayList<>();
        List<List<Integer>> peopleInPartyList = new ArrayList<>();

        // 사람 수, 파티수, 최대 과장해서 말을 할 수 있는 파티 수
        int numberOfPeople = sc.nextInt();
        int numberOfParty = sc.nextInt();
        int maxNumberOfLie = numberOfParty;

        // 진실을 아는 사람 번호 및 수
        int numberOfTurePeople = sc.nextInt();
        for (int i = 0; i < numberOfTurePeople; i++) {
            int truePeople = sc.nextInt();
            truePeopleList.add(truePeople);
        }

        // 파티에 참가한 사람 번호 목록
        for (int i = 0; i < numberOfParty; i++) {
            List<Integer> peopleInParty = new ArrayList<>();
            int numberOfPeopleInParty = sc.nextInt();

            for (int j = 0; j < numberOfPeopleInParty; j++) {
                peopleInParty.add(sc.nextInt());
            }
            peopleInPartyList.add(peopleInParty);
        }

        // 진실을 아는 사람 추가
        for (int i = 0; i < numberOfParty; i++) {
            for (int j = 0; j < peopleInPartyList.get(i).size(); j++) {
                if (truePeopleList.contains(peopleInPartyList.get(i).get(j))) {
                    for (int k = 0; k < peopleInPartyList.get(i).size(); k++) {
                        if (!truePeopleList.contains(peopleInPartyList.get(i).get(k))) {
                            truePeopleList.add(peopleInPartyList.get(i).get(k));
                            i = 0;
                            j = 0;
                            j--;
                        }
                    }
                }
            }
        }

        // 최댓값 구하기
        for (int i = 0; i < numberOfParty; i++) {
            for (int j = 0; j < peopleInPartyList.get(i).size(); j++) {
                if (truePeopleList.contains(peopleInPartyList.get(i).get(j))) {
                    maxNumberOfLie--;
                    break;
                }
            }
        }

        System.out.print(maxNumberOfLie);
    }

}

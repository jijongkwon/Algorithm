package baekjoon.silver.silver4.silver_1764;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Silver_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /*
            듣도, 듣보잡 사람 명단
        */
        HashSet<String> noHearPeople = new HashSet<>();
        List<String> noLookAndHearPeople = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        // 듣도 못한 사람 수
        int N = Integer.parseInt(st.nextToken());

        // 보도 못한 사람 수
        int M = Integer.parseInt(st.nextToken());

        /*
            입력
         */
        for (int i = 0; i < N; i++) {
            noHearPeople.add(br.readLine());
        }

        // 동시에 있는 사람 찾아서 리스트에 넣기
        for (int i = 0; i < M; i++){
            String person = br.readLine();
            if(noHearPeople.contains(person)){ // 시간 복잡도 O(1)
                noLookAndHearPeople.add(person);
            }
        }

        // 정렬
        Collections.sort(noLookAndHearPeople);

        // 출력
        System.out.println(noLookAndHearPeople.size());
        for(int i = 0; i < noLookAndHearPeople.size(); i++){
            System.out.println(noLookAndHearPeople.get(i));
        }
    }
}

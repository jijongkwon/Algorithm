package softeer.level2.회의실예약;

import java.util.*;

/*
    회의실 : 9 ~ 18
    회의정보 : 회의실, 시작/종료 시각 (단위: h)
    회의는 겹칠 수 없음

    시간복잡도: 여유
    메모리: 여유

    출력 : 회의실이 비어있는 시간대
*/
public class Solution
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 회의실 수
        int M = sc.nextInt(); // 회의 수
        HashMap<String, int[]> map = new HashMap<>(); // 강의실 별 시간
        List<String> roomNames = new ArrayList<>();

        // 회의실 입력
        for(int i = 0; i < N; i++){
            int[] time = new int[19]; // 시간

            for(int j = 9; j < 19; j++){
                time[j] = j;
            }
            String name = sc.next();
            roomNames.add(name);
            map.put(name, time);
        }

        Collections.sort(roomNames);

        // 회의실 시간 제거
        for(int i = 0; i < M; i++){
            String name = sc.next();
            int s = sc.nextInt();
            int e = sc.nextInt();

            int[] updateTime = map.get(name);
            for(int j = s; j < e; j++) {
                updateTime[j] = 0;
                if(j + 1 == 18){
                    updateTime[j + 1] = 0;
                }
            }
            map.put(name, updateTime);
        }

        //찾기
        int size = 0;
        for(String name : roomNames){
            size++;
            System.out.println("Room " + name + ":");

            // 남은 시간 계산
            int[] remainTime = map.get(name);
            int count = 0;
            List<String> start = new ArrayList<>();
            List<String> end = new ArrayList<>();
            for(int i = 0; i < 19; i++){
                if(remainTime[i] != 0){
                    start.add(Integer.toString(i));

                    while(remainTime[i] != 0){
                        i++;
                        if(i == 19){
                            i--;
                            break;
                        }
                    }

                    end.add(Integer.toString(i));
                    count++;
                }
            }

            if(count == 0){
                System.out.println("Not available");
            }
            else{
                System.out.println(count + " available:");
                for(int i = 0; i < start.size(); i++){
                    if(start.get(i).equals("9")){
                        System.out.print("0" + start.get(i));
                    }
                    else{
                        System.out.print(start.get(i));
                    }

                    System.out.println("-" + end.get(i));
                }
            }
            if(size != roomNames.size()){
                System.out.println("-----");
            }
        }
    }
}
package programmers.level2.호텔대실;

class Solution {
    private static final int MAX_MINUTE = 1450; //24 * 60 + 10
    private static final int HOUR = 60;
    private static final int cleanRoom = 10;
    public int solution(String[][] book_time) {
        int answer = 0;

        // 방을 분으로 나눔
        int [] room = new int[MAX_MINUTE];

        for(String [] book : book_time){
            String inBook = book[0];
            String outBook = book[1];

            room[calTime(inBook)] += 1;
            room[calTime(outBook) + cleanRoom] += -1;
        }

        for(int i = 1; i < MAX_MINUTE; i++){
            room[i] += room[i-1];
            answer = Math.max(answer, room[i]);
        }

        return answer;
    }

    public int calTime(String time){
        String [] split = time.split(":");
        int hourToMinute = Integer.parseInt(split[0])  * HOUR;
        int minute = Integer.parseInt(split[1]);
        return hourToMinute + minute;

    }
}
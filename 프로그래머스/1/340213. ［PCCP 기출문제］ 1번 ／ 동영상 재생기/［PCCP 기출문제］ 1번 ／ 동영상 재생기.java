/*
10 초 전 이동 ( prev ) -> 10초 미만인 경우 -> 0분 0초
10 초 후 이동 ( next ) -> 남은시간이 10초 미만인 경우 -> 마지막 위치 이동
오프닝 건너뛰기 -> 오프닝 시간안에 있으면 오프닝 끝나는 위치로 이동

동영상 길이 video_len
재생 위치 pos
오프닝 시작 시각 op_start
오프닝 끝 시각 op_end
명령어 commends

풀이
분을 초로 변경
그리고 계산 다하고 분:초 나타냄

return 입력이 끝난후 mm:ss 형식으로 return
*/
class Solution {
    int curTime, posInt, opStartInt, opEndInt, videoLenInt;
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        
        posInt = minutesToSec(pos);
        opStartInt = minutesToSec(op_start);
        opEndInt = minutesToSec(op_end);
        videoLenInt = minutesToSec(video_len);
    
        // 초기화
        curTime = posInt;
        
        // 오프닝 건너뛰기
        doneOp();
        
        // 명령어 수행
        for(String commend : commands){
            if(commend.equals("prev")){
                prevTen();
                doneOp();
                continue;
            }
            
            nextTen();
            doneOp();
        }
        
        return secToMinutes(curTime);
    }
    
    // 분을 초로
    int minutesToSec(String time){
        String[] temp = time.split(":"); 
        int makedTime = 0;
        
        makedTime += Integer.parseInt(temp[0]) * 60;
        makedTime += Integer.parseInt(temp[1]);
        
        return makedTime;
    }
    
    // 초를 분으로
    String secToMinutes(int time){
        String minutes = String.valueOf(time / 60);
        String second = String.valueOf(time % 60);
        
        if(minutes.length() == 1){
            minutes = "0" + minutes;
        }
        
        if(second.length() == 1){
            second = "0" + second;
        }
        
        return minutes + ":" + second;
    }
    
    // 10초 전 이둥
    void prevTen(){
        if(curTime < 10){
            curTime = 0;
            return;
        }
        
        curTime -= 10;
    }
    
    // 10초 후 이동
    void nextTen(){
        if(videoLenInt - curTime < 10){
            curTime = videoLenInt;
            return;
        }
        
        curTime += 10;
    }
    
    // 오프닝 끝
    void doneOp(){
        if(opStartInt <= curTime && curTime <= opEndInt){
            curTime = opEndInt;
        }
    }
}
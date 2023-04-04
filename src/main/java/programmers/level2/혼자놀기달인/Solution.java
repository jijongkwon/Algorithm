package programmers.level2.혼자놀기달인;

class Solution {
    /*
        1. 상자가 열렸는지 확인
        2. 안열렸다면, 리스트에 추가
        3. 상자 그룹 중 개수가 많은 순으로 2개 선택
    */
    public int solution(int[] cards) {

        // 열렸는지 확인용 리스트
        boolean[] checkBox = new boolean[cards.length];
        int[] boxSize = new int[cards.length];

        // 상자 열기
        for(int i = 0; i < cards.length; i++){
            int count = 0;
            int index = i;
            while(true){
                if(checkBox[index]){
                    break;
                }
                checkBox[index] = true;
                count++;
                index = cards[index] - 1;
            }
            boxSize[i] = count;
        }

        // 정렬
        for(int i = 0 ; i < boxSize.length; i++){
            for(int j = i + 1 ; j < boxSize.length; j++){
                if(boxSize[i] < boxSize[j]){
                    int temp = boxSize[i];
                    boxSize[i] = boxSize[j];
                    boxSize[j] = temp;
                }
            }
        }

        return boxSize[0] * boxSize[1];
    }
}
import java.util.*;
import java.io.*;

/**
 * 1분에 한 박스, 크레인은 동시에 움직임
 * 무게 제한 존재, 모든 박스를 배로 옮기는데 드는 시간의 최솟값
 *
 * 1. 정렬
 * 2. queue 써서 되는 것만 빠져나가게
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        List<Integer> crains = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            crains.add(Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        List<Integer> boxs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            boxs.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(crains, Collections.reverseOrder());
        Collections.sort(boxs, Collections.reverseOrder());
        
        if(crains.get(0) < boxs.get(0)){
            System.out.println(-1);
            return;
        }

        int ans = 0;
        while(!boxs.isEmpty()){
            int boxIdx = 0;
            int crainIdx = 0;
            
            while(crainIdx < n){
                // 박스 순회 끝
                if(boxIdx == boxs.size()){
                    break;
                }

                // 박스 꺼내기
                if(boxs.get(boxIdx) <= crains.get(crainIdx)){
                    boxs.remove(boxIdx);
                    crainIdx++;
                }
                // 없다면 다음 박스 탐색
                else{
                    boxIdx++;
                }
            }

            ans++;
        }

        System.out.println(ans);
    }
}
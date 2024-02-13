import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;

    public static void main(String[] args) throws IOException {

        // 비디오 사이즈 입력
        int videoSize = Integer.parseInt(br.readLine());
        map = new int[videoSize][videoSize];

        // 비디오 내용 입력
        for(int i = 0; i < videoSize; i++){
            String input[] = br.readLine().split("");
            for(int j = 0; j < input.length; j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        quadTree(0,0,videoSize);

        System.out.println(sb.toString());
    }

    static void quadTree(int x, int y, int size){
        if(isPossible(x,y,size)){
            sb.append(map[x][y]);
            return;
        }

        int newSize = size / 2;

        sb.append("(");

        quadTree(x, y, newSize); // 왼쪽 위
        quadTree(x,y+newSize,newSize); // 오른족 위
        quadTree(x+newSize,y,newSize); // 왼쪽 아래
        quadTree(x+newSize,y+newSize,newSize); // 오른쪽 아래

        sb.append(")");
    }

    // 0만 있는지, 1만 있는지 확인
    static boolean isPossible(int x, int y, int size){
        int value = map[x][y];

        for(int i = x; i < x + size; i++){
            for(int j = y; j < y + size; j++){
                if(value != map[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}

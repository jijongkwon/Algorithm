import java.util.Scanner;

public class Main {

    static int count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 맵 크기 입력
        int N = sc.nextInt();
        int size = (int) Math.pow(2, N);

        // 출력 할 행 열 입력
        int r = sc.nextInt();
        int c = sc.nextInt();

        visit(size,r,c);

        System.out.println(count);
    }

    public static void visit(int size, int r, int c){

        // 좌표 찾음
        if(size == 1){
            return;
        }

        // 등분
        int half = size / 2;

        // 왼쪽 위 좌표 확인
        if(r < half && c < half){
            visit(half,r,c);
        }

        // 오른쪽 위 좌표 확인
        if(r < half && c >= half){
            count += (half * half) * 1;
            visit(half,r,c - half);
        }

        // 왼쪽 밑 좌표 확인
        if(r >= half && c < half){
            count += (half * half) * 2;
            visit(half,r - half ,c);
        }

        // 오른쪽 밑 좌표 확인
        if(r >= half && c >= half){
            count += (half * half) * 3;
            visit(half,r - half,c - half);
        }
    }
}

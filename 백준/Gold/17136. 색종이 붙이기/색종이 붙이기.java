import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] board = new int[10][10];
    static int[] squares = {0, 5, 5, 5, 5, 5};
    static int countOne;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            String input = br.readLine();
            for (int j = 0; j < 10; j++) {
                board[i][j] = input.charAt(j<<1) - '0';
                if (board[i][j] == 1) countOne++;
            }
        }

        solve(0, 0, 0);
        System.out.println(answer > 25 ? -1 : answer);
    }

    private static void solve(int x, int y, int used) {
        if (countOne == 0) {
            answer = Math.min(answer, used);
            return;
        }

        int nx, ny = 10;
        outer: for (nx = x; nx < 10; nx++) {
            for (ny = 0; ny < 10; ny++) {
                if (board[nx][ny] == 1) break outer;
            }
        }
        if (nx == 10 || ny == 10) return;

        boolean checkSquare = true;
        for (int n = 5; n > 0; n--) {
            if (!checkSquare(nx, ny, n)) continue;

            fillSquare(nx, ny, n, n+1);
            squares[n]--;
            countOne -= n*n;

            solve(nx, ny, used+1);

            fillSquare(nx, ny, n, 1);
            squares[n]++;
            countOne += n*n;
        }
    }

    private static boolean checkSquare(int x, int y, int n) {
        if (squares[n] == 0) return false;
        for (int nx = x; nx < x + n; nx++) {
            for (int ny = y; ny < y + n; ny++) {
                if (nx < 0 || ny < 0 || nx >= 10 || ny >= 10) return false;
                if (board[nx][ny] != 1) return false;
            }
        }
        return true;
    }

    	private static void fillSquare(int x, int y, int n, int val) {
    		for (int nx = x; nx < x + n; nx++) {
    			for (int ny = y; ny < y + n; ny++) {
    				board[nx][ny] = val;
    			}
    		}
    	}
}
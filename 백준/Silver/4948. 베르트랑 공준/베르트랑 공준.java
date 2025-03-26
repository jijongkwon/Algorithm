import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            int n = Integer.parseInt(br.readLine());

            if(n == 0){
                break;
            }

            System.out.println(findPrime(n, 2 * n));
        }
    }

    public static int findPrime(int start, int end){
        int count = 0;
        for (int i = start + 1; i <= end ; i++) {
            if(isPrime(i)){
                count++;
            }
        }

        return count;
    }

    public static boolean isPrime(int number){
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if(number % i == 0){
                return false;
            }
        }

        return true;
    }
}
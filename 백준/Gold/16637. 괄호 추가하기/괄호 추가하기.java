import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int max = Integer.MIN_VALUE;
    static int n;
    static List<Integer> numbers;
    static List<Character> cals;

public static void main(String[] args) {
    // TODO Auto-generated method stub
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    numbers = new ArrayList<>();
    cals = new ArrayList<>();

    String[] tmp = sc.next().split("");

    for (int i = 0; i < n; i++) {
        if (i % 2 == 0) {
            numbers.add(Integer.valueOf(tmp[i]));
            continue;
        }

        cals.add(tmp[i].charAt(0));
    }

    rq(numbers.get(0), 0);

    System.out.println(max);

}

static void rq(int result, int opIndex) {

    if (opIndex >= cals.size()) {
        max = Math.max(result, max);
        return;
    }

    // 앞 괄호
    int res1 = cal(result, numbers.get(opIndex + 1), cals.get(opIndex));
    rq(res1, opIndex + 1);

    // 뒷 괄호
    if (opIndex + 1 < cals.size()) {
        int res2 = cal(numbers.get(opIndex + 1), numbers.get(opIndex + 2), cals.get(opIndex + 1));

        rq(cal(result, res2, cals.get(opIndex)), opIndex + 2);
    }
}

static int cal(int a, int b, char c) {
    if (c == '+') {
        return a + b;
    }

    if (c == '-') {
        return a - b;
    }

    return a * b;
}
}
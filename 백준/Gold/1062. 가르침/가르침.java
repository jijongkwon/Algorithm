import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * k개의 글자
 *
 * 단어의 시작은 anta 로 시작하고 tica로 끝이난다.
 *
 * 즉 k가 5개 이하라면 단어는 0개
 *
 */
public class Main {
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 종료
        if(k < 5){
            System.out.println(0);
            return;
        }

        String[] word = new String[n];
        for(int i = 0; i < n; i++){
            word[i] = br.readLine();
        }


        boolean[] alphabets = new boolean[26];
        alphabets['a' - 'a'] = true;
        alphabets['n' - 'a'] = true;
        alphabets['t' - 'a'] = true;
        alphabets['c' - 'a'] = true;
        alphabets['i' - 'a'] = true;

        backTracking(0,5, k, word, alphabets);

        System.out.println(answer);
    }

    static void backTracking(int alphaIdx, int depth, int limitLen, String[] word, boolean[] alphabets){

        if(depth == limitLen){
            int count = 0;
            for(int i = 0; i < word.length; i++){
                boolean flag = true;
                for (int j = 0; j < word[i].length(); j++) {
                    char c = word[i].charAt(j);

                    if(!alphabets[c - 'a']){
                        flag = false;
                        break;
                    }
                }

                if(flag){
                    count++;
                }
            }

            answer = Math.max(count, answer);
            return;
        }

        for(int i = alphaIdx; i < 26; i++){
            if(!alphabets[i]){
                alphabets[i] = true;
                backTracking(i,depth + 1, limitLen, word, alphabets);
                alphabets[i] = false;
            }
        }
    }
}
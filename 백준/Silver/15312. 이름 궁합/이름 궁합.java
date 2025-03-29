import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String first_name = br.readLine(); //첫번째 이름 문자열
		String second_name = br.readLine(); //두번째 이름 문자열
		int[] number = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1}; //대문자 획순
		Vector<Integer> totalNum = new Vector<Integer>(); //번갈아가면서 이름 획순
		Vector<Integer> temp = new Vector<Integer>();
		
		if(first_name.length()<2 || first_name.length()>2000) System.exit(0);
		
		for(int i=0; i<first_name.length(); i++) { //번갈아가면서 이름 획순 저장
			char first = first_name.charAt(i);
			char second = second_name.charAt(i);
			totalNum.add(number[first-'A']);
			totalNum.add(number[second-'A']);
		}
		
		while(totalNum.size()>2) {
			int sum=0;
			for(int i=0; i<totalNum.size()-1; i++) {
				sum = (totalNum.get(i)+totalNum.get(i+1)) % 10;
				temp.add(sum);
			}
			totalNum.removeAllElements();
			totalNum.addAll(temp);
			temp.removeAllElements();
		}
		System.out.println(totalNum.get(0)+""+totalNum.get(1));
	}
}
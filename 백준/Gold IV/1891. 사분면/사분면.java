import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 13.
 * @link https://www.acmicpc.net/problem/1891
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static long mx, my, d;
	static String number;
	static long x, y, n;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		d = sc.nextInt();
		number = sc.next();
		mx = sc.nextLong();
		my = sc.nextLong();
		
		
		n = (long) Math.pow(2, d);
		findXY(0,0,n,0,n);
		x -= my;
		y += mx;
		
		if(x < 0 || y < 0 || x >=n || y >= n) {
			System.out.println(-1);
		}
		else {
			findLoc(x, y, n);
			System.out.println(sb.toString());
		}		
	}
	
	static void findXY(long depth, long lx, long rx, long ly, long ry) {
		// base
		if(depth == d) {
			x = lx;
			y = ly;
			return;
		}
		
		// inductive
		int loc = number.charAt((int) depth) - '0';
		
		switch (loc) {
		case 1: findXY(depth + 1, lx, (lx + rx) / 2, (ly + ry) / 2, ry); break;
		case 2: findXY(depth + 1, lx, (lx + rx) / 2, ly, (ly + ry) / 2); break;
		case 3: findXY(depth + 1, (lx + rx) / 2, rx, ly, (ly + ry) / 2); break;
		case 4: findXY(depth + 1, (lx + rx) / 2, rx, (ly + ry) / 2, ry); break;
		}
	}
	
	static void findLoc(long r, long c, long size) {
		// base
		if(size == 1) {
			return;
		}
		
		// inductive
		long half = size / 2;
		
		if(r < half && c >= half) {
			sb.append(1);
			findLoc(r, c - half, half);
		}
		
		if(r < half && c < half) {
			sb.append(2);
			findLoc(r, c, half);
		}
		
		if(r >= half && c < half) {
			sb.append(3);
			findLoc(r - half, c, half);
		}
		
		if(r >= half && c >= half) {
			sb.append(4);
			findLoc(r - half, c - half, half);
		}
	}
}
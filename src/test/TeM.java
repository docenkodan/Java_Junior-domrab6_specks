package test;

import java.util.Scanner;

public class TeM {

	public static void main(String[] args) {
		Test test = new Test();
		Scanner scan = new Scanner(System.in);
		
		test.calc_d(); 
		int t; t = scan.nextInt();
	    for (int it = 1; it <= t; it ++) test.solve();
	}

}

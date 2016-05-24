package sample;

import java.io.IOException;
import java.math.BigInteger;

public class Test {
	public static void main(String[] ads) throws IOException {
		CombinationGenerator cg = new CombinationGenerator();
		String startNum = "001010010100101001010010100101";
		String endNum   = "002010010100101001010010100101";
//		BigInteger b = new BigInteger();
//		System.out.println(b);
		cg.generate(startNum, endNum);
//		long start = System.currentTimeMillis();
//		for (int i = 0; i < 1000000000; i++) {
//			
//		}
//		long end = System.currentTimeMillis();
//		System.out.println((end - start)/1000/60);
	}
}

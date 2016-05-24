package sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;

public class Test {
	public static void main(String[] ads) throws IOException {
		CombinationGenerator cg = new CombinationGenerator();
		cg.thread_name = ads[2];
//		String startNum = "001010010100101001010010100101";
//		String endNum   = "002010010100101001010010100101";
		
		String startNum = ads[0].trim();
		String endNum = ads[1].trim();
//		String startNum = "900101001010010100101001010010";
//		String endNum   = "900101001010010100101001090010";
//		String endNum   = "999899989998999899989998999899";
		
		File file = new File(ads[2] + "_result_" + System.currentTimeMillis() + ".txt");
		FileOutputStream fos = new FileOutputStream(file);

      // Create new print stream for file.
		PrintStream ps = new PrintStream(fos);

      // Set file print stream.
      	System.setOut(ps);
		
//		BigInteger b = new BigInteger();
//		System.out.println(b);

		long start = System.currentTimeMillis();
		cg.generate(startNum, endNum);
		long end = System.currentTimeMillis();
		System.out.println(ads[2] + "_" + (end - start)/1000/60);
		

//        System.out.println("file 1");
//        
//        File file2 = new File("file2.txt");
//        FileOutputStream fos2 = new FileOutputStream(file2);
//
//        // Create new print stream for file.
//        PrintStream ps2 = new PrintStream(fos2);
//        System.setOut(ps2);
//        System.out.println("file 2");
	}
}

package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

//import model.Abn;

public class CombinationGenerator {
//	byte[] numbers = new byte[30];
	
	public List<String> exceptions = new ArrayList<>(974);
	public List results = new LinkedList<>(); 
	
	public void init() throws IOException {
		File f = new File("src/input/Exceptions.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));

		String line = null;
		while ((line = br.readLine()) != null) {
			exceptions.add(line.trim());
		}

		br.close();

//		return exceptions;
	}
	
	public void generate(String startNumStr, String endNumStr) throws IOException {
		init();
//		byte[] startNums = updateNusmbersFromString(startNumStr);
		BigInteger startNum = new BigInteger(startNumStr);
		BigInteger endNum = new BigInteger(endNumStr);
		String tmp = "";
		long count = 0;
		String prefix = "0.";
		do {
//			tmp = String.format("%30s", startNum.toString());
			tmp = StringUtils.leftPad(startNum.toString(), 30, "0");
			
//			System.out.println("tmp: " + tmp);
			
			if (isValidNumber(tmp)) {
//				results.add(tmp);
				System.out.println(prefix + tmp);
			}
			
			startNum = startNum.add(new BigInteger("1"));
//			System.out.print(count);
			count++;
			
//			if (count == 10000) {
//				System.out.println("=====" + results);
//			}
//		} while (startNum.compareTo(endNum) <= 0);
		} while (count < 10000000000l);
		
//		System.out.println("=====" + results);
	}
	
	private byte[] updateNumbersFromString(String numString) {
		if (numString.length() != 30) {
			throw new IllegalArgumentException("Invalid length of numbers");
		}
		
		byte[] numbers = new byte[30];
		String[] numStrings = numString.trim().split("");
		for (int i = 0; i < numStrings.length; i++) {
			numbers[i] = Byte.parseByte(numStrings[i]);
		}
		
		return numbers;
		
	}
	
	private boolean isValidNumber(String numStr) {
		for (String s : exceptions) {
			if (numStr.contains(s)) {
				return false;
			}
		}
		
		return true;
	}
}

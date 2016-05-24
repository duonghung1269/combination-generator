package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

//import model.Abn;

public class CombinationGenerator {
	// byte[] numbers = new byte[30];
	public String thread_name;

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

		// return exceptions;
	}

	public void generate(String startNumStr, String endNumStr)
			throws IOException {
		init();
		// byte[] startNums = updateNusmbersFromString(startNumStr);
		BigInteger startNum = new BigInteger(startNumStr);
		BigInteger endNum = new BigInteger(endNumStr);

		String tmp = "";
		long count = 0;
		String prefix = "0.";
//		do {
//			tmp = StringUtils.leftPad(startNum.toString(), 30, "0");
//
//			NumberWrapper isValidNumber = isValidNumber(tmp); 
//			if (!isValidNumber.isValid) {
//				System.out.println(prefix + tmp);
//			}
//
//			startNum = startNum.add(new BigInteger("1"));
//			
//			count++;
//
//			
//		} while (count < 10000000000l);
		
		while (startNum.compareTo(endNum) < 0) {
			// TODO: temporary comment this one cause dont have prefix 0
//			tmp = StringUtils.leftPad(startNum.toString(), 30, "0");
			tmp = startNum.toString();

			NumberWrapper isValidNumber = isValidNumber(tmp); 
			if (!isValidNumber.isValid) {	
				startNum = new BigInteger(isValidNumber.numString);
				continue;								
			}
			
			System.out.println(prefix + tmp);
			count++;
			startNum = startNum.add(new BigInteger("1"));
			
			if (count % 10000000 == 0) {
				File file = new File(thread_name + "_result_" + System.currentTimeMillis() + "_" + startNum + ".txt");
		        FileOutputStream fos = new FileOutputStream(file);

		        // Create new print stream for file.
		        PrintStream ps = new PrintStream(fos);

		        // Set file print stream.
		        System.setOut(ps);
				count = 0;
			}
			
			
		}
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

	private NumberWrapper isValidNumber(String numStr) {
		for (String rulePattern : exceptions) {
			int index = numStr.lastIndexOf(rulePattern);
			if (index != -1) { // invalid number
				String[] nums = numStr.split("");
				int lastIndex = index + rulePattern.length();
				
				// reset all 0 after lastIndex, follow format should be 0010100101
				for (int i = lastIndex + 1; i < numStr.length(); i++) {
					int distance = (i - lastIndex) % 6;
					switch (distance) {
						case 0: 
						case 1:
						case 2:
						case 4:
							nums[i] = "0";
							break;						
						case 3:
						case 5:
							nums[i] = "1";
							break;
						default:
							break;
					}
					
					nums[i] = "0";
				}
				
				int remain = 0;
				do {
					int n = Byte.parseByte(nums[lastIndex]) + 1 + remain;
					remain = n / 10;
					int result = n % 10;
					nums[lastIndex] = result + "";
					lastIndex--;
				} while (lastIndex >= 0 && remain > 0);

				return new NumberWrapper(false, StringUtils.join(nums, ""));
//				return false;
			}
		}

		return new NumberWrapper(true, null);
	}
}

class NumberWrapper {
	public boolean isValid = false;
	public String numString;

	public NumberWrapper(boolean isValid, String numString) {
		super();
		this.isValid = isValid;
		this.numString = numString;
	}

}

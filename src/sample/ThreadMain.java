package sample;

import java.io.IOException;

public class ThreadMain {
	public static void main(String[] s) {
		Thread thread1 = new Thread() {
			public void run() {
				try {
//					Test.main(new String[]{"900101001010010100101999899989",
//										   "900101001010010100501001010010",
//										   "thread1"});
					new Test().start(new String[]{"900101001010010100101999899989",
										   "900101001010010100501001010010",
										   "thread1"});
				} catch (IOException e) {
					System.out.println("thread1 exception");
				}
			}
		};
		
		Thread thread2 = new Thread() {
			public void run() {
				try {
//					Test.main(new String[]{"900101001010010100501001010010",
//										   "900101001010010100999899989998",
//										   "thread2"});
					new Test().start(new String[]{"900101001010010100501001010010",
										   "900101001010010100999899989998",
										   "thread2"});
				} catch (IOException e) {
					System.out.println("thread2 exception");
				}
			}
		};
		
		thread1.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("interupt exception");
		}
		
		thread2.start();
	}
}

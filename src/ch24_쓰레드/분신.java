package ch24_쓰레드;

public class 분신 extends Thread {
	
	private static int number;
	private int num;
	
	public 분신() {
		number += 1;
		num = number;
	}
	
	@Override
	public void run() {
		System.out.println(num + "분신 실행");
		
		while(true) {
			System.out.println(num + "분신 반복");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

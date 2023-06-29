package ch13_스태틱;

import ch10_배열.Array01;

public class MessageUtilMain {
	
	private int a;

	public static void main(String[] args) {
		MessageUtilMain messageUtilMain = new MessageUtilMain();
		
		System.out.println(messageUtilMain.a);
		
		Array01.main(null);
		
		MessageUtil messageUtil = new MessageUtil();
//		
//		messageUtil.sendmail();
//		messageUtil.sendFile();
		
		MessageUtil.sendFile();
		System.out.println();
		
		MessageUtil.data = "안녕";
		System.out.println(MessageUtil.data);
	}

}
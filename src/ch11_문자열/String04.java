package ch11_문자열;

public class String04 {
	public static void main(String[] args) {
		String phone = "   010-1234.                             5678  ";
		
		String replacePhone = phone
				.replaceAll("-", "")
				.replaceAll(" ", "")
				.replaceAll("[.]", ""); // .은 이렇게 써야 함
		
		System.out.println(replacePhone);
		
		String phone2 = phone.trim();
		System.out.println(phone2);
	}
}
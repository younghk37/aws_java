package ch11_문자열;

public class String05 {
	public static void main(String[] args) {
		String name = " ";
		//name = ""; // 이건 둘다 true
		
		boolean flag = name.isBlank();// 공백은 문자열로 보지 않는다
		System.out.println(flag);
		
		boolean flag2 = name.isEmpty();// 공백도 문자열로 본다
		System.out.println(flag2);
	}
}
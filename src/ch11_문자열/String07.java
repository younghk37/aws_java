package ch11_문자열;

public class String07 {
	public static void main(String[] args) {
		String name = "김영훈";
		String address = "부산 동래구";
		
		System.out.println(name.concat(" ".concat(address)));// + 없이 문자열을 합치는 용도로 사용
	}
}
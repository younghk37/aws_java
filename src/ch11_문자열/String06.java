package ch11_문자열;

public class String06 {
	public static void main(String[] args) {
		String token = "Bearer aaaaaabbbbbb.cccccccddddddd.eeeeeeeffffff";//JWT
		
		boolean flag = token.startsWith("Bearer");// ~로 시작하는가?
		System.out.println(flag);
		
		String url = "/api/v1/user/1";
		
		boolean flag2 = url.startsWith("/api/v1");
		
		if(flag2) {
			System.out.println("api 버전1 사용");
		}
	}
}
//startsWith는 많이 쓴다
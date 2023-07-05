package ch22_익명클래스;

public class LambdaTest {
	public static void main(String[] args) {
		GrantedAuthorities authorities2 = new GrantedAuthorities() {
			
			@Override
			public String getAuthority() {
				System.out.println("권한 출력");
				return "ROLE_USER";
			}
		};
		
		GrantedAuthorities authorities = () -> {
			System.out.println("권한 출력");
			return "ROLE_USER";
		};
		
		System.out.println(authorities.getAuthority());
		

	}
}
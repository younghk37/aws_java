package ch18_빌더;

public class KoreaUserMain {

	public static void main(String[] args) {
		KoreaUser koreaUser = KoreaUser.builder()
				.userId(1)
				.username("aaa")
				.password("1234")
				.name("김준일")
				.email("aaa@gmail.com")
				.build();
		
		KoreaUser koreaUser1 = KoreaUser.builder().userId(1).build();
		KoreaUser koreaUser2 = new KoreaUser(1, null, null, null, null);
		KoreaUser koreaUser3 = new KoreaUser();
		koreaUser3.setUserId(1);
	}

}

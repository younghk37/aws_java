package ch18_빌더;

public class UserTest {
	
	public static void printUser() {
		System.out.println("사용자 정보 출력");
		(new UserTest()).test();
		(new UserTest()).new UserTestTest();
		UserTest.UserTestTest userTestTest = new UserTest().new UserTestTest();
	}
	
	public void test() {
		System.out.println("테스트 메소드 호출");
	}
	
	public class UserTestTest {
		public void testTest() {
			System.out.println("테스트테스트 메소드 호출");
		}
	}
}

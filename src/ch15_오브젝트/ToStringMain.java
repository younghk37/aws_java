package ch15_오브젝트;

public class ToStringMain {
	public static void main(String[] args) {
		KoreaStudent koreaStudent = new KoreaStudent("20230001", "김영훈");
		
		System.out.println(koreaStudent.toString());
		
		String str = koreaStudent.toString();
//		String str2 = koreaStudent;
	}
}
package ch08_생성자;

public class StudentMain {

	public static void main(String[] args) {
		Student s1 = new Student("김영훈", 26, "부산진구");
		System.out.println(s1);
//		s1.name = "김영훈";
//		s1.age = 30;
//		s1.address = "부산진구";
		
		System.out.println(s1.name);
	}
}
package ch14_싱글톤;

public class SamsungFactory {
	
	public Galaxy produce(String model) {
		System.out.println(Samsung.getInsatnce().getCompanyName() + "에서 스마트폰을 생산합니다.");
		int newSerialNumber = Samsung.getInsatnce().getAutoIncrementSerialNumber() + 1;
		Samsung.getInsatnce().setAutoIncrementSerialNumber(newSerialNumber);
		return new Galaxy(newSerialNumber, model);
	}
	
	public void showCompanyName() {
		System.out.println("회사명: " + Samsung.getInsatnce().getCompanyName());
	}
}
/*
 * 싱글톤 만들기
 * 1. static으로 private 변수 만들기
 * 2. 생성자는 private로 막기
 * 3. getInstance라는 static 메소드 만들기 -> 1에서 만든 변수가 null이면 생성하고 반환. null이 아니면 그냥 반환.
*/
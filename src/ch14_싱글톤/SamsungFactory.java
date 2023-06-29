package ch14_싱글톤;

public class SamsungFactory {
	
	private Samsung samsung;
	
	public SamsungFactory(Samsung samsung) {
		this.samsung = samsung;
	}
	
	public Galaxy produce(String model) {
		System.out.println(samsung.getCompanyName() + "에서 스마트폰을 생산합니다.");
		return new Galaxy(0, model);
	}
	
	public void showCompanyName() {
		System.out.println("회사명: " + samsung.getCompanyName());
	}
}
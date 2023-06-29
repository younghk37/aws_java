package ch14_싱글톤;

public class Galaxy {
	private int serialNumber;
	private String model;
	
	public Galaxy(int serialNumber, String model) {
		this.serialNumber = serialNumber;
		this.model = model;
	}
	
	public void showInfo() {
		System.out.println("시리얼번호: " + serialNumber);
		System.out.println("모델: " + model);
	}
}

package ch17_추상;

import lombok.ToString;

//@AllArgsConstructor -> 에러 발생 -> 직접 만들자
@ToString(callSuper = true)
public class SmartPhone extends SmartDevice implements Button {
	
	private String phoneNumber;
	
	public SmartPhone(String deviceName, double displaySize, String phoneNumber) {
		super(deviceName, displaySize);
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public void connectedWiFi() {
		System.out.println("스마트폰의 와이파이를 연결합니다.");
	}

	@Override
	public void powerOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void powerOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		
	}
}
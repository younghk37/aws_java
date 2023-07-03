package ch17_추상;

import lombok.ToString;

//@AllArgsConstructor -> 에러 발생 -> 직접 만들자
@ToString(callSuper = true)
public class Pad extends SmartDevice{
	
	private boolean availabilityPen;
	
	public Pad(String deviceName, double displaySize, boolean availabilityPen) {
		super(deviceName, displaySize);
		this.availabilityPen = availabilityPen;
	}
	
	@Override
	public void connectedWiFi() {
		System.out.println("패드의 와이파이를 연결합니다.");
	}
}
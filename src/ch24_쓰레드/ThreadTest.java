package ch24_쓰레드;

public class ThreadTest implements Runnable{
	@Override
	public void run() {
		System.out.println("스레드 이름: " + Thread.currentThread().getName());
		System.out.println("3번 스레드 실행");
	}
}
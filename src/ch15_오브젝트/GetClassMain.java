package ch15_오브젝트;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GetClassMain {

	public static void main(String[] args) {
		KoreaStudent koreaStudent = new KoreaStudent("20230001", "김채원");
		
		Method[] methods = koreaStudent.getClass().getDeclaredMethods();
		for(int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getName());
			System.out.println(methods[i].getReturnType());
		}
		
		System.out.println();
		
		Field[] fields = koreaStudent.getClass().getDeclaredFields();
		for(int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getName());
		}
		
		System.out.println();
		
		System.out.println(KoreaStudent.class == koreaStudent.getClass());// 자기 클래스만 비교 가능 -> 정확
		
		System.out.println(koreaStudent instanceof Object); // 상위 클래스도 비교 가능 -> 모호 -> 자바 상위 버전에서 제거됨
		
		System.out.println(koreaStudent.getClass().getSimpleName());
		System.out.println(koreaStudent.getClass().getName());
	}
}
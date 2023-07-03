package ch20_컬렉션;

import java.util.ArrayList;
import java.util.LinkedList;

public class StringArrayListMain {
	public static void main(String[] args) {
		CustomArrayList list = new CustomArrayList();
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add(1, "f");
		
		System.out.println(list);
		System.out.println(list.remove());
		System.out.println(list);
		System.out.println(list.remove(3));
		System.out.println(list);
		
		System.out.println("========================================");
		
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("a");
		arrayList.add("b");
		arrayList.add("c");
		arrayList.add("d");
		arrayList.add("e");
		arrayList.add(1, "f");
		
		System.out.println(arrayList);
		System.out.println(arrayList.remove(3));
		System.out.println(arrayList);
		arrayList.set(0, "h");
		System.out.println(arrayList);
		
		LinkedList<String> linkedList = new LinkedList<>();// 좌항의 변수가 제네릭 타입이 명확하면 우항의 제네릭 타입은 생략이 가능하다 and 들어가는 값을 통해서도 생략이 가능.
		LinkedList<Integer> linkedList2 = new LinkedList<>();
	}
}

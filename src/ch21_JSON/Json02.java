package ch21_JSON;

import com.google.gson.Gson;

public class Json02 {
	public static void main(String[] args) {
		Gson gson = new Gson();
		
		JsonUser jsonUser = JsonUser.builder()
				.username("aaa")
				.password("bbb")
				.build();
		
		System.out.println(jsonUser);// 객체
		
		String jsonData = gson.toJson(jsonUser);// 객체 -> JSON(String)
		System.out.println(jsonData);
		
		JsonUser jsonUser2 = gson.fromJson(jsonData, JsonUser.class);// JSON(String) -> 객체
		System.out.println(jsonUser2);
	}
}

package ch26_socket.simpleGUI.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ch26_socket.simpleGUI.client.SimpleGUIClient;
import ch26_socket.simpleGUI.server.dto.RequestBodyDto;
import ch26_socket.simpleGUI.server.dto.SendMessage;
import ch26_socket.simpleGUI.server.entity.Room;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConnectedSocket extends Thread{
	
	private final Socket socket;
	private Gson gson;
	
	private String username;
	
	@Override
	public void run() {
		gson = new Gson();
		
		while(true) {
			try {
				BufferedReader bufferedReader =
						new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String requestBody = bufferedReader.readLine();
				
				requestController(requestBody);
				
//				SimpleGUIServer.connectedSocketList.forEach(connectedSocket -> {
//					try {
//						PrintWriter printWritter =
//								new PrintWriter(connectedSocket.socket.getOutputStream(), true);
//						printWritter.println(requestBody);
//						
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				});
//				
////				for(ConnectedSocket connectedSocket : SimpleGUIServer.connectedSocketList) {
////					
////				}
////				
////				for(int i = 0; i < SimpleGUIServer.connectedSocketList.size(); i++) {
////					
////				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void requestController(String requestBody) {
		String resource = gson.fromJson(requestBody, RequestBodyDto.class).getResource();
		
		switch(resource) {
			case "connection" :
				connection(requestBody);
				break;
				
			case "createRoom" :
				createRoom(requestBody);
				break;
				
			case "join" :
				join(requestBody);
				break;
				
			case "sendMessage" :
				sendMessage(requestBody);
				break;
		}
	}
	
	/**
	 * username 필드를 초기화하고
	 * 이 소켓에게 최신 방 정보를 보내줍니다 (READ -> roomList)
	 */
	private void connection(String requestBody) {
		username = (String) gson.fromJson(requestBody, RequestBodyDto.class).getBody();// 필드 초기화
		
		List<String> roomNameList = new ArrayList<>();
		
		SimpleGUIServer.roomList.forEach(room -> {
			roomNameList.add(room.getRoomName());
		});
		
		RequestBodyDto<List<String>> updateRoomListRequestBodyDto =
				new RequestBodyDto<List<String>>("updateRoomList", roomNameList);

		ServerSender.getInstance().send(socket, updateRoomListRequestBodyDto);
	}
	
	/**
	 * 1. 방 목록에 새로운 방을 하나 추가합니다 (CREATE -> roomList)
	 * 2. 모든 소켓에게 최신 방 정보를 보내줍니다 (READ -> roomList)
	 */
	private void createRoom(String requestBody) {
		String roomName = (String) gson.fromJson(requestBody, RequestBodyDto.class).getBody();//T타입에 해당하는 부분에 문자열 값이 들어있으면 리터럴이라 그냥 String으로 다운캐스팅 가능.
		
		Room newRoom = Room.builder()
				.roomName(roomName)
				.owner(username)
				.userList(new ArrayList<ConnectedSocket>())
				.build();
		
		SimpleGUIServer.roomList.add(newRoom);
		
		List<String> roomNameList = new ArrayList<>();
		
		SimpleGUIServer.roomList.forEach(room -> {
			roomNameList.add(room.getRoomName());
		});
		
		RequestBodyDto<List<String>> updateRoomListRequestBodyDto =
				new RequestBodyDto<List<String>>("updateRoomList", roomNameList);
		
		SimpleGUIServer.connectedSocketList.forEach(con -> {
			
			ServerSender.getInstance().send(con.socket, updateRoomListRequestBodyDto);
			
		});
		
	}
	
	/**
	 * roomName을 입력받아 -> 일치하는 방이 있으면 해당 방의 유저목록에 이 ConnectedSocket을 기록합니다 (ADD -> roomList.room.userList)
	 * 이 방에 접속한 유저들에게
	 * -> 이 방에 접속한 유저들의 이름을 전달하고 (READ -> roomList.room)
	 * -> 새로 들어온 유저의 환영 메시지를 보냅니다
	 */
	private void join(String requestBody) {
		String roomName = (String) gson.fromJson(requestBody, RequestBodyDto.class).getBody();
		
		SimpleGUIServer.roomList.forEach(room -> {
			if(room.getRoomName().equals(roomName)) {
				room.getUserList().add(this);
				
				List<String> usernameList = new ArrayList<>();
				
				room.getUserList().forEach(con -> {
					usernameList.add(con.username);
				});
					
				room.getUserList().forEach(connectedSocket -> {
					RequestBodyDto<List<String>> updateUserListDto = new RequestBodyDto<List<String>>("updateUserList", usernameList);
					RequestBodyDto<String> joinMessageDto = new RequestBodyDto<String>("showMessage", username + "님이 들어왔습니다.");
					
					ServerSender.getInstance().send(connectedSocket.socket, updateUserListDto);
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ServerSender.getInstance().send(connectedSocket.socket, joinMessageDto);
				});
			}
		});
	}
	
	// 요청한 소켓이 속한 방에 메시지를 날립니다
	private void sendMessage(String requestBody) {
		TypeToken<RequestBodyDto<SendMessage>> typeToken = new TypeToken<>() {};
		
		RequestBodyDto<SendMessage> requestBodyDto = gson.fromJson(requestBody, typeToken.getType());
		SendMessage sendMessage = requestBodyDto.getBody();
		
		SimpleGUIServer.roomList.forEach(room -> {
			if(room.getUserList().contains(this)) {
				room.getUserList().forEach(con -> {
					RequestBodyDto<String> dto = new RequestBodyDto<String>("showMessage", sendMessage.getFromUsername() + ": " + sendMessage.getMessageBody());
					ServerSender.getInstance().send(con.socket, dto);
				});
			}
		});
		
	}
}


// 전체 전송 기능입니다.
//		SimpleGUIServer.connectedSocketList.forEach(connectedSocket -> {
//			RequestBodyDto<String> dto = new RequestBodyDto<String>("showMessage", sendMessage.getFromUsername() + ": " + sendMessage.getMessageBody());
//			ServerSender.getInstance().send(connectedSocket.socket, dto);
//		});



// String의 경우 제대로 변환 됨. 그러나 T타입을 가진 부분은 어떤 map 타입이 Object 타입으로 업캐스팅 됨.
//		RequestBodyDto<?> requestBodyDto = gson.fromJson(requestBody, RequestBodyDto.class); 
//
//		TypeToken<RequestBodyDto<SendMessage>> token = new TypeToken<RequestBodyDto<SendMessage>>() {};	
//		RequestBodyDto<SendMessage> requestBodyDto = gson.fromJson(requestBody, token.getType());





//SendMessage sendMessage = (SendMessage) requestBodyDto.getBody();
//
//if(Objects.isNull(sendMessage.getToUsername())) {
//	SimpleGUIServer.connectedSocketList.forEach(con -> {
//		RequestBodyDto<String> showMessageDto =
//				new RequestBodyDto<String>("showMessage", sendMessage.getFromUsername() + ": " + sendMessage.getMessageBody());
//		ServerSender.getInstance().send(con.socket, showMessageDto);
//	});
//}
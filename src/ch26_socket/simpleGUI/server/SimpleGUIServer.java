 package ch26_socket.simpleGUI.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import ch26_socket.simpleGUI.server.entity.Room;

/**
 * [ 채팅 방 서버 프로그램 ]
 * 
 * static 속성
 * - connectedSocketList : 접속 중인 클라이언트 소켓들
 * - roomList : 생성 된 채팅 방들
 * 
 */
public class SimpleGUIServer {
	
	public static List<ConnectedSocket> connectedSocketList = new ArrayList<>();
	public static List<Room> roomList = new ArrayList<>();
	
	public static void main(String[] args) {
		
		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("[ 서버실행 ]");
			
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("접속");
				ConnectedSocket connectedSocket = new ConnectedSocket(socket);
				connectedSocketList.add(connectedSocket);
				connectedSocket.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
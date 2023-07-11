package ch26_socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConnectedSocket extends Thread {

	private final Socket socket;
	
	@Override
	public void run() {
		BufferedReader bufferedReader = null;
		
		try {
			while(true) {
				bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String requestBody = bufferedReader.readLine();
				System.out.println("입력데이터: " +  requestBody);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*InputStreamReader는 입력스트림에서 읽을 수 있다
 * BufferedReader는 입력을 하나하나 처리하지않고 버퍼로 한번에 가져온다 -> 엔터를 기준으로
 */
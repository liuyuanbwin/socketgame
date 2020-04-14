package SocketGame;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("192.168.1.104", 10068);
			System.out.println("input user name：");
			Scanner sc = new Scanner(System.in);

			// getInputStream
			InputStream is = socket.getInputStream();
			// BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				// read server data
				byte[] b = new byte[1024];
				int n = is.read(b);
				String message = new String(b, 0, n);
				String[] res = message.split("@");

				if(res[0].equals(MessageType.RESULT_OK)){//0 类型  2猜测的数  3用户名称
					System.out.println("congratulation" + res[3] + ", num is " + res[2]);
				}
				if(res[0].equals(MessageType.RESULT_BIG)){
					System.out.println(res[3] + "guessed number" + res[2] + "> result");
				}
				if(res[0].equals(MessageType.RESULT_SMALL)){
					System.out.println(res[3] + "guessed number" + res[2] + "> result");
				}

				if(res[0].equals(MessageType.GAME_END)){//游戏结束
					System.out.println(res[3] + "guessed number" + res[2] + "> result");
					System.out.println("game complete" + res[1]);
					System.out.println("Please enter p: play again; q: quit");
				}
				if (res[0].equals(MessageType.GAME_START)) {//游戏开始
					// start game
					System.out.println("game start!gamer=" + res[2]);
				}
				if (res[0].equals(MessageType.GAME_QUIT)) {//游戏退出
					// start game
					System.out.println("game quit!gamer=" + res[2]);
				}
				if (res[0].equals(MessageType.GAME_WAIT)) {//游戏等待
					System.out.println("waiting! current gamer :" + res[1]);
				}
			}
			// 关闭资源
			//      bufferedReader.close();
			//      inputStreamReader.close();
			//      inputStream.close();
			//      writer.close();
			//      outputStream.close();
			//      socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
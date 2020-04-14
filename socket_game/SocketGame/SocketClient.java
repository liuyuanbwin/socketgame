package SocketGame;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
  public static void main(String[] args) {
    try {
      Socket socket = new Socket("192.168.199.153", 10068);
      OutputStream outputStream = socket.getOutputStream();// 得到一个输出流，用于向服务器发送数据
      OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");// 将写入的字符编码成字节后写入一个字节流
      System.out.println("enter user name：");
      Scanner sc = new Scanner(System.in);
      String data = sc.nextLine();
      writer.write(data);
      writer.flush();// 刷新缓冲
      socket.shutdownOutput();// 只关闭输出流而不关闭连接
      // 获取服务器端的响应数据

      InputStream inputStream = socket.getInputStream();// 得到一个输入流，用于接收服务器响应的数据
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");// 将一个字节流中的字节解码成字符
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);// 为输入流添加缓冲
      String info = null;
      System.out.println("客户端IP地址:" + socket.getInetAddress().getHostAddress());

      // 输出服务器端响应数据
      while (true) {

				// read server data
				byte[] b = new byte[1024];
				int n = is.read(b);
        String message = new String(b, 0, n);
        System.out.println("message:" +message);
				String[] res = message.split("@");

				// max game number
				if (res[0].equals("6")) {
					logger.info("already guessed" + res[1] + "times!answer:" + res[2]);
					System.out.println("already guessed" + res[1] + "times!answer:" + res[2]);
				}

				// identify
				if (res[0].equals("0")) {
					logger.info("congratulation" + res[3] + ", num is " + res[2]);

					// win the guess
					System.out.println("congratulation" + res[3] + ", num is " + res[2]);

				} else if (res[0].equals("1")) {
					logger.info(res[3] + "guessed number" + res[2] + "> result");

					// guessed number > result
					System.out.println(res[3] + "guessed number" + res[2] + "> result");
					System.out.println("enter number");

				} else if (res[0].equals("2")) {
					logger.info(res[3] + "guessed number" + res[2] + "< result");

					// guessed number < result
					System.out.println(res[3] + "guessed number" + res[2] + "< result");
					System.out.println("enter number");

				} else if (res[0].equals("7")) {
					logger.info("game conplete" + res[1]);

					// quit game
					System.out.println("game complete" + res[1]);
					System.out.println("Please enter p: play again; q: quit");

				} else if (res[0].equals("4")) {
					logger.info("waiting! current gamer :" + res[1]);

					// wait
					System.out.println("waiting! current gamer :" + res[1]);
				} else if (res[0].equals("8")) {
					logger.info("game start!gamer =" + res[2]);

					// start game
					System.out.println("game start!gamer=" + res[2]);
					System.out.println("enter number");
				}
			}
      // // 关闭资源
      // bufferedReader.close();
      // inputStreamReader.close();
      // inputStream.close();
      // writer.close();
      // outputStream.close();
      // socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
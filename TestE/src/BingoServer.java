import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.net.ServerSocket;  
import java.net.Socket;
import java.util.Arrays;  

/**
 * 游戏的服务端
 * @author Lawliet
 *
 */
public class BingoServer {  
	private static int guessNum = 0;
	private static int guessMaxMum = 5;
    public static void main(String[] args) throws IOException {  
        //生成随机数
        int res = (int) (Math.random() * 5);

        //建立tcp的服务端  
        ServerSocket serverSocket = new ServerSocket(9090);  
        //接受客户端的连接，产生一个SOcket  
        Socket socket = serverSocket.accept();  

        //获取到Socket的输入流对象  
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));  

        //获取到Socket输出流对象  
        OutputStreamWriter socketOut =  new OutputStreamWriter(socket.getOutputStream());  

        //获取键盘的输入流对象  
        BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));  

        //读取客户端的数据  
        String line = null;  
        while((line = socketReader.readLine())!=null){  
            if(Integer.parseInt(line.split("@")[0]) == MessageType.GAME_REGISTER){
            	 socketOut.write(Integer.parseInt(line.split("@")[0])+"注册成功，开始游戏，请按下q退出，s开始"+"\r\n");  
                 socketOut.flush();
            }
            String bingo = Integer.toString(res);
            if(line.equals(bingo)){
                line = "恭喜你猜对了！";  
                socketOut.write(line+"\r\n");  
                socketOut.flush();
            }
            else{
                 line = "很遗憾，没有猜对，再试一次！";  
                 socketOut.write(line+"\r\n");  
                 socketOut.flush();  
                 guessNum++;
                 if(guessMaxMum == guessNum){
                	 line = "猜的次数过多，真正的数字是："+res;  
                     socketOut.write(line+"\r\n");  
                 }
            }
        }  

        //关闭资源  
        serverSocket.close();  
    }  

}  
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.net.ServerSocket;  
import java.net.Socket;
import java.util.Arrays;  

/**
 * ��Ϸ�ķ����
 * @author Lawliet
 *
 */
public class BingoServer {  
	private static int guessNum = 0;
	private static int guessMaxMum = 5;
    public static void main(String[] args) throws IOException {  
        //���������
        int res = (int) (Math.random() * 5);

        //����tcp�ķ����  
        ServerSocket serverSocket = new ServerSocket(9090);  
        //���ܿͻ��˵����ӣ�����һ��SOcket  
        Socket socket = serverSocket.accept();  

        //��ȡ��Socket������������  
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));  

        //��ȡ��Socket���������  
        OutputStreamWriter socketOut =  new OutputStreamWriter(socket.getOutputStream());  

        //��ȡ���̵�����������  
        BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));  

        //��ȡ�ͻ��˵�����  
        String line = null;  
        while((line = socketReader.readLine())!=null){  
            if(Integer.parseInt(line.split("@")[0]) == MessageType.GAME_REGISTER){
            	 socketOut.write(Integer.parseInt(line.split("@")[0])+"ע��ɹ�����ʼ��Ϸ���밴��q�˳���s��ʼ"+"\r\n");  
                 socketOut.flush();
            }
            String bingo = Integer.toString(res);
            if(line.equals(bingo)){
                line = "��ϲ��¶��ˣ�";  
                socketOut.write(line+"\r\n");  
                socketOut.flush();
            }
            else{
                 line = "���ź���û�в¶ԣ�����һ�Σ�";  
                 socketOut.write(line+"\r\n");  
                 socketOut.flush();  
                 guessNum++;
                 if(guessMaxMum == guessNum){
                	 line = "�µĴ������࣬�����������ǣ�"+res;  
                     socketOut.write(line+"\r\n");  
                 }
            }
        }  

        //�ر���Դ  
        serverSocket.close();  
    }  

}  
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.net.InetAddress;  
import java.net.Socket;  
import java.util.Scanner;

/**
 * ��Ϸ�Ŀͻ���
 * @author Lawliet
 *
 */
public class BingoClient {  
    public static void main(String[] args) throws IOException {  

        //����tcp�Ŀͻ��˷���  
        Socket socket = new Socket(InetAddress.getLocalHost(),9090);  
        //��ȡsocket�����������  
        OutputStreamWriter socketOut =  new OutputStreamWriter(socket.getOutputStream());  
        //��ȡsocket������������  
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));  

        //��ȡ���̵����������󣬶�ȡ����  
        BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));  

        String line = null;  

        System.out.println("input user name��");
		Scanner sc = new Scanner(System.in);
		
        //���ϵĶ�ȡ����¼������ݣ�Ȼ�������д��  
        while((line = keyReader.readLine())!=null){  
        	String str = "";
        	str += MessageType.GAME_REGISTER+"@"+line;
            socketOut.write(str+"\r\n");  
            //ˢ��  
            socketOut.flush();  
            //��ȡ����˻��͵�����  
            line = socketReader.readLine();  
            System.out.println("����˻��͵������ǣ�"+line);
            if(line.equals("��ϲ��¶��ˣ�")){
                System.out.println("��Ϸ������");
                break;
            }
        }  
        //�ر���Դ  
        socket.close();  
    }         
}  
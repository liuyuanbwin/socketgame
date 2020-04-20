package GuessNumber;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import GuessNumber.MessageType;

public class Client {
  public static void main(String[] args) {
    Socket socket = null;
    OutputStream os = null;
    InputStream is = null;
    BufferedReader br = null;
    byte[] data = new byte[2];

    try {
      // create socket
      socket = new Socket("127.0.0.1", 10001);
      // send byte
      os = socket.getOutputStream();
      // read byte
      is = socket.getInputStream();

      // keyboard input
      br = new BufferedReader(new InputStreamReader(System.in));

      while (true) {
        System.out.println("Please input number:");
        String s = br.readLine();

        // quit game
        if (s.equals("quit")) {
          os.write("quit".getBytes());
          break;
        }

        // if number
        boolean isNumber = true;

        try {
          Integer.parseInt(s);
        } catch (Exception e) {
          isNumber = false;
        }

        if (isNumber) {
          // send number
          os.write(s.getBytes());

          is.read(data);

          int res = data[0];

          // result parse
          switch (res) {
            case MessageType.CONGRATULATION:
              System.out.println("Congratulation !");
              break;
            case MessageType.NUMBER_LARGE:
              System.out.println("Too large !");
              break;
            case MessageType.NUMBER_SMALL:
              System.out.println("Too small !");
              break;

            default:
              System.out.println("Error !");
              break;
          }

          // guess count
          int guessCount = data[1];
          System.out.println("You have guessed " + guessCount + " times!");

          if (guessCount >= 4) {
            System.out.println("You failed.");

            //
            os.write("quit".getBytes());

            break;
          }
        } else {
          System.out.println("Input Error");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        br.close();
        is.close();
        os.close();
        socket.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
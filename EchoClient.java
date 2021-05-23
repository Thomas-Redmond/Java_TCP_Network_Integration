package TCP;

import java.net.*;
import java.io.*;

public class EchoClient{
  private Socket clientSocket;
  private PrintWriter out;
  private BufferedReader in;

  public void startConnection(String ip, int port) throws IOException{
    clientSocket = new Socket(ip, port);
    out = new PrintWriter(clientSocket.getOutputStream(), true);
    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  }

  public String sendMessage(String msg) throws IOException{
    out.println(msg);
    String resp = in.readLine();
    return resp;
  }

  public void stopConnection() throws IOException{
    in.close();
    out.close();
    clientSocket.close();
  }

  public static void main(String[] args) throws IOException{
    EchoClient EC = new EchoClient();
    EC.startConnection("127.0.0.1", 6666);
    EC.sendMessage("Bananas");
    Ec.stopConnection();

    System.out.println("Connection terminated");
  }

}

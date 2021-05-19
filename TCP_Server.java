package TCP_Network;

import java.net.*;
import java.io.*;

public class TCP_Server{
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private PrintWriter out;
  private BufferedReader in;


  public void start(int port) throws IOException{
    serverSocket = new ServerSocket(port);
    clientSocket = serverSocket.accept();
    out = new PrintWriter(clientSocket.getOutputStream(), true);
    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    String greeting = in.readLine();
    if ("hello server".equals(greeting)){
      out.println("Hello Client");
    }
    else{
        out.println("Unrecognised Greeting");
      }
  }

  public void stop() throws IOException{
    in.close();
    out.close();
    clientSocket.close();
    serverSocket.close();
  }

  public static void main(String[] args) throws IOException{
    TCP_Server server = new TCP_Server();
    server.start(6666);
  }

}

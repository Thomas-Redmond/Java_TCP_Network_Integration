// Creates class which can handle requests from many different clients
// Client requests are moved to a dedicated thread handler upon start

package TCP;

import java.net.*;
import java.io.*;

public class EchoMultiServer{
  // main server class
  // Listens for clients attempting connection, and when made spins up a class to "serve" them.
  // Continues listening for more requests
  private ServerSocket serverSocket;

  public static void main(String[] args){
    EchoMultiServer newServer = new EchoMultiServer();
    newServer.start(6666);
  }


  public void start(int port){
    try{
      serverSocket = new ServerSocket(port);
      while(true){
        new EchoClientHandler(serverSocket.accept()).start();
      }
    } catch(IOException e){
      System.out.println("IOException");
    }
  }

  private class EchoClientHandler extends Thread{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public EchoClientHandler(Socket socket){
      this.clientSocket = socket;
    }

    public void run(){
      try{
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        String inputLine;
        while((inputLine = in.readLine()) != null){
          if(".".equals(inputLine)){
            out.println("bye");
            break;
          }
          System.out.println(inputLine);
        }
        in.close();
        out.close();
        clientSocket.close();
      }
      catch(IOException e){
        System.out.println("IOException");
      }
    }
  }

}

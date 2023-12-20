//EchoThread.java
import java.io.*; 
import java.net.*; 
import java.util.*;
public class EchoThread extends Thread {
    private Socket connectionSocket;
    public EchoThread(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }
     public void run() {
        String number_1st;
        String number_2nd;
        String sum_value;  
        Scanner inFromClient = null;
	    DataOutputStream outToClient = null;
        try {
 			inFromClient = new Scanner(connectionSocket.getInputStream());
 			outToClient = 
            new DataOutputStream(connectionSocket.getOutputStream()); 
            number_1st = inFromClient.nextLine(); 
            if (number_1st.isEmpty()){
                inFromClient.close();
                outToClient.close();
                connectionSocket.close();
            }
            number_2nd = inFromClient.nextLine(); 
            if (number_2nd.isEmpty()){
                inFromClient.close();
                outToClient.close();
                connectionSocket.close();
            }
            sum_value = String.valueOf(Integer.valueOf(number_1st) + Integer.valueOf(number_2nd));
       	    outToClient.writeBytes(sum_value);        
 		}
        catch (IOException e) {
            System.err.println("Closing Socket connection");
        }
        finally {
            try {
                if (inFromClient != null)
                    inFromClient.close();
                if (outToClient != null)
                    outToClient.close();
                if (connectionSocket != null)
                    connectionSocket.close();
                }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
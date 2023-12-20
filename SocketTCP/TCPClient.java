import java.io.*; 
import java.net.*;
import java.util.*; 
class TCPClient { 
public static void main(String argv[]) throws Exception { 
    String number_1st;
    String number_2nd;  
    String modifiedSentence;
    Scanner inFromUser = null;
    Socket clientSocket = null;
    DataOutputStream outToServer = null;
    Scanner inFromServer = null;
    try { 
 		inFromUser = new Scanner(System.in);
        clientSocket = new Socket("localhost", 1667); 
        outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
 		inFromServer = new Scanner(clientSocket.getInputStream());
 		System.out.print("Please enter number (to end just press enter) : ");
 		number_1st = inFromUser.nextLine(); 
 		outToServer.writeBytes(number_1st + '\n');
        System.out.print("Please enter number (to end just press enter) : ");
        number_2nd = inFromUser.nextLine(); 
 		outToServer.writeBytes(number_2nd + '\n');
 		modifiedSentence = inFromServer.nextLine(); 
        System.out.println("Result : " + modifiedSentence);
    }
    catch (IOException e) {
        System.out.println("Error occurred: Closing the connection");
    }
    finally {
        try {
            if (inFromServer != null)
                inFromServer.close();
            if (outToServer != null)
                outToServer.close();
            if (clientSocket != null)
                clientSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        } 
    } 
}
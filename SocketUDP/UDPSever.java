import java.io.*; 
import java.net.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;     
   
class UDPServer { 
    public static void main(String args[]) throws Exception 
    { 
        DatagramSocket serverSocket = new DatagramSocket(9876); 
        DateTimeFormatter dtf ;  
        LocalDateTime now ;  
        byte[] receiveData = new byte[1024]; 
        byte[] sendData  = new byte[1024];

 		while(true) { 
            System.out.println("The server is waiting ");
            DatagramPacket receivePacket = 
                new DatagramPacket(receiveData, receiveData.length); 
            serverSocket.receive(receivePacket);

            dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            now = LocalDateTime.now();  

            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress(); 
            int port = receivePacket.getPort();           

            String capitalizedSentence =  dtf.format(now);
            sendData = capitalizedSentence.getBytes(); 
 			DatagramPacket sendPacket = 
                new DatagramPacket(sendData, sendData.length, IPAddress, port); 
            serverSocket.send(sendPacket); 
         } 
     } 
}
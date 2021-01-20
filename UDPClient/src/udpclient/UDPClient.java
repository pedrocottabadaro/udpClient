/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpclient;

/**
 *
 * @author pedro
 */
import java.io.*; 
import java.net.*; 
  
class UDPClient { 
    
    public static void main(String args[]) throws Exception 
    { 
    
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 

        DatagramSocket clientSocket = new DatagramSocket(); 

        InetAddress IPAddress = InetAddress.getByName("192.168.0.131"); 
        while(true){
        byte[] sendData = new byte[1024]; 
        byte[] receiveData = new byte[1024]; 

        System.out.println("Enviar Msg:\n");
        String sentence = inFromUser.readLine(); 
        
        sendData = sentence.getBytes();         
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876); 

        clientSocket.send(sendPacket); 
        
        if(sentence.contains("#")){
            clientSocket.close(); 
            System.exit(1);
        }

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 

        clientSocket.receive(receivePacket); 

        String modifiedSentence = new String(receivePacket.getData()); 
        
        if(modifiedSentence.contains("#")){
            clientSocket.close(); 
            System.exit(1);
        }

        System.out.println("FROM SERVER:" + modifiedSentence); 
        }
    
      
      } 
    
} 
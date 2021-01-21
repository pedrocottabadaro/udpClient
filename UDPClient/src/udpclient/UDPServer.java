package udpclient;
//@author Pedro Cotta Badaro - 201776014
//@author Davi Rezende Domingues - 201765505ab
import java.io.*;
import java.net.*;

class UDPServer {
    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData;
        byte[] sendData = new byte[1024];
        System.out.println("SERVER PORT 9876");
        
        while (true) {
            receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String sentence = new String(receivePacket.getData());
            
            //VERIFICA SE EH PRA ENCERRAR
            if (sentence.contains("#")) {
                serverSocket.close();
                System.exit(2);
            }

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            
            String capitalizedSentence = sentence;
            System.out.println("FROM CLIENT: " + capitalizedSentence);

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("INSERIR MENSAGEM: ");
            String dados = inFromServer.readLine();
            sendData = dados.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
            
            //VERIFICA SE EH PRA ENCERRAR
            if (dados.contains("#")) {
                serverSocket.close();
                System.exit(2);
            }
        }
    }
}
package udpclient;
//@author Pedro Cotta Badaro - 201776014
//@author Davi Rezende Domingues - 201765505ab
import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String args[]) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("192.168.0.131");
        
        while (true) {
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            System.out.println("INSERIR MENSAGEM: ");
            String sentence = inFromUser.readLine();

            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);

            //VERIFICA SE EH PRA ENCERRAR
            if (sentence.contains("#")) {
                clientSocket.close();
                System.exit(1);
            }

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());

            //VERIFICA SE EH PRA ENCERRAR
            if (modifiedSentence.contains("#")) {
                clientSocket.close();
                System.exit(1);
            }
            //IMPRIME MENSAGEM QUE VEIO DO SERVIDOR
            System.out.println("FROM SERVER: " + modifiedSentence);
        }
    }
}
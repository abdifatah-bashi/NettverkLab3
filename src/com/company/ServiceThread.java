package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

public class ServiceThread extends Thread {

    Socket connectSocket ;
    int portNumber;
    public ServiceThread(Socket theSocket){
        this.connectSocket = theSocket;

    }


    @Override
    public void run() {

        try {
            //Stream writer to the connection socket
            PrintWriter out = new PrintWriter(connectSocket.getOutputStream(), true);
            // Stream reader from the connection socket
            BufferedReader in = new BufferedReader(new InputStreamReader(connectSocket.getInputStream()));
            InetAddress clientAddr = connectSocket.getInetAddress();
            int clientPort = connectSocket.getPort();
            String receivedText;

            // read from the connection socket
            while ((receivedText = in.readLine()) != null) {
                System.out.println("Client [" + clientAddr.getHostAddress() + ":" + clientPort + "] > " + receivedText);
                System.out.println("recieved text: " + receivedText);
                String[] outText = receivedText.split(",");

                // Calculate and convert
                double amount = Double.parseDouble(outText[0]);
                String source = outText[1];
                String target = outText[2];
                double result = convert(amount, source, target);

                out.println("New amount = " + result);
                System.out.println("I (Server) [" + connectSocket.getLocalAddress().getHostAddress() + ":" + portNumber + "] > " + outText);
            }

            System.out.println("I am done, Bye!");
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }


    // Metode som konverterer valuta

    public static double convert(double amount, String source, String target) throws IOException {


        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Windows User\\NettverkLab3\\src\\com\\company\\ExchangeRate.CSV"));
        try {
            while (br!= null){
                String line = br.readLine();
                String [] currentLine = line.split(",");
                if(source.equals(currentLine[0]) && target.equals(currentLine[2])) return Double.parseDouble(currentLine[1] )* amount;
            }


        } catch (IOException e) {
            e.printStackTrace();

        }
        return -1;
    }




}

















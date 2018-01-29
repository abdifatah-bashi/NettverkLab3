package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException
    {

        String hostName = "127.0.0.1"; // Default host, localhost
        int portNumber = 8899; // Default port to use

        if (!(args.length > 2)) {
            if (args.length > 0) {
                hostName = args[0];
                if (args.length > 1) {
                    portNumber = Integer.parseInt(args[1]);

                }
            }
        }


        System.out.println("Hi, I am client!");

        try
                (// create TCP socket for the given hostName, remote port PortNumber
                 Socket clientSocket = new Socket(hostName, portNumber); )
        {
            // Stream writer to the socket
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            // Stream reader from the socket
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Keyboard input reader
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the amount to convert, source currency and target currency seperated by comma f.example (500,USD,NOK\n");
            String userInput;
            // Loop until null input string
            // System.out.println("I (Client) [" + InetAddress.getLocalHost()  + ":" + clientSocket.getLocalPort() + "] > ");
            while ((userInput = stdIn.readLine()) != null && !userInput.isEmpty())
            {
                // write keyboard input to the socket
                out.println(userInput);

                // read from the socket and display
                String receivedText = in.readLine();

                System.out.println("Server [" + hostName +  ":" + portNumber + "] > " + receivedText);
                System.out.print("I (Client) [" + clientSocket.getLocalAddress().getHostAddress() + ":" + clientSocket.getLocalPort() + "] > ");
            }
        } catch (UnknownHostException e)
        {
            System.err.println("Unknown host " + hostName);
            System.exit(1);
        } catch (IOException e)
        {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }

}

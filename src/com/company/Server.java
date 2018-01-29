package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        int portNumber = 8899; // Default port to use
        System.out.println("CURRENCY CONVERTER ONLINE!");

        while (true) {

            try ( // Create server socket with the given port number
                  ServerSocket serverSocket = new ServerSocket(portNumber);
                  // create connection socket, server begins listening
            ) {
                // for incoming TCP requests
                Socket connectSocket = serverSocket.accept();

                //Create new thread that serves for client
                new ServiceThread(connectSocket).start();


            }

            {

            }
        }
    }
}

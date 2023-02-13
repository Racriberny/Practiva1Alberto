package com.cristobalbernal.Practica1Alberto.Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    private static final int PUERTO = 2600;
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado por el puerteo " + PUERTO );

            while(true){

                Socket socket = serverSocket.accept();
                ServidorHilo servidorHilo = new ServidorHilo(socket);
                servidorHilo.start();
            }

        } catch (IOException ex) {
            ex.getMessage();
        }

    }

}

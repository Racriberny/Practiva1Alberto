package com.cristobalbernal.Practica1Alberto.Servidor;

import com.cristobalbernal.Practica1Alberto.Lib.Lib;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorHilo extends Thread {
    private static final int MIN = 1;
    private static final int MAX = 100;
    private final Socket socket;

    public ServidorHilo(Socket socket) {
        this.socket = socket;
    }

    public void run(){

        System.out.println("Cliente esta conectado!!!!");
        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;
        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            int numAleatorio = Lib.numeroAleatorio(MIN,MAX);
            int numUsuario;

            System.out.println("Num generado: " + numAleatorio);

            do {

                outputStream.writeUTF("Escribe un numero entre 1 y 100");
                numUsuario = inputStream.readInt();
                //Esto es para hacer pruebas con los numero!! Se deberia de borrar!!!
                System.out.println("Numero recibido: " + numUsuario);

                if(numUsuario == numAleatorio){
                    outputStream.writeUTF("¡Has ganado!");
                }else if (numUsuario > numAleatorio){
                    outputStream.writeUTF("El numero buscado es menor");

                }else{
                    outputStream.writeUTF("El numero buscado es mayor");
                }
                outputStream.writeBoolean(numUsuario == numAleatorio);
            } while(numUsuario != numAleatorio);

            socket.close();
            System.out.println("Cliente desconectado!!!");

        } catch (IOException ioe) {
            ioe.getMessage();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException ioE) {
                ioE.getMessage();
            }
        }

    }
}
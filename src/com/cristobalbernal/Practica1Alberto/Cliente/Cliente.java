package com.cristobalbernal.Practica1Alberto.Cliente;

import com.cristobalbernal.Practica1Alberto.Lib.Lib;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    private static final String CONEXION = "localhost";
    private static final int PUERTO = 2600;
    public static void main(String[] args) {

        try {
            Socket socket = new Socket(CONEXION,PUERTO);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            boolean salir;

            do{
                String mensajeRecibido = in.readUTF();

                System.out.println(mensajeRecibido);

                int numEscrito = Lib.leerInt();
                out.writeInt(numEscrito);

                mensajeRecibido = in.readUTF();
                System.out.println(mensajeRecibido);

                salir = in.readBoolean();

            }while(!salir);

            socket.close();

        } catch (IOException ex) {
            ex.getMessage();
        }

    }
}

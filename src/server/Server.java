/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author mrabraham
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(300);
            System.out.println("Beklenen Port " + serverSocket.getLocalPort() + "...");
        } catch (IOException ex) {
            System.out.println("bu Port no ile server kurulamaz");
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            socket = serverSocket.accept();
            System.out.println(" Client Bağlandı");
        } catch (IOException ex) {
            System.out.println("Client bağlanamadı ");
            //Can't accept client connection.
        }

        try {
            in = socket.getInputStream();
            System.out.println("Dosya aktarımı başarılı");
        } catch (IOException ex) {
            System.out.println("Dosya aktarılamadı");
            //Can't get socket input stream.
        }

        try {
            out = new FileOutputStream("/Users/mrabraham/Desktop/isim-soyisim.txt");
            System.out.println("Dosya oluşturma başarılı");
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı ");
        }

        byte[] bytes = new byte[16*1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}
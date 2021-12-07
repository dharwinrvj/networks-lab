/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filetransfer;

/**
 *
 * @author dharwin
 */
import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getByName("localhost"), 5000);
        byte[] contents = new byte[10000];
        FileOutputStream fout = new FileOutputStream("C:\\Users\\dharw\\Documents\\NetBeansProjects\\FileTransfer\\src\\filetransfer\\client.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fout);
        InputStream is = socket.getInputStream();
        int bytesRead = 0;
        while ((bytesRead = is.read(contents)) != -1) {
            bos.write(contents, 0, bytesRead);
        }
        bos.flush();
        socket.close();
        System.out.println("file saved successfully");
    }
}

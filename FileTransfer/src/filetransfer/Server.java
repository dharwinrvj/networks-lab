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

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket ssock = new ServerSocket(5000);
        Socket socket = ssock.accept();
        InetAddress ia = InetAddress.getByName("localhost");
        File f = new File("C:\\Users\\dharw\\Documents\\NetBeansProjects\\FileTransfer\\src\\filetransfer\\server.txt");
        FileInputStream fin = new FileInputStream(f);
        BufferedInputStream bis = new BufferedInputStream(fin);
        OutputStream os = socket.getOutputStream();
        byte[] contents;
        long fileLength = f.length();
        long current = 0;
        long start = System.nanoTime();
        while (current != fileLength) {
            int size = 10000;
            if (fileLength - current >= size) {
                current += size;
            } else {
                size = (int) (fileLength - current);
                current = fileLength;
            }
            contents = new byte[size];
            bis.read(contents, 0, size);
            os.write(contents);
            System.out.println("Sending file....." + (current * 100) / fileLength + "% complete");
        }
        os.flush();
        socket.close();
        ssock.close();
        System.out.println("File sent successfully");
    }
}

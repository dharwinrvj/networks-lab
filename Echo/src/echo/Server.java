/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echo;

/**
 *
 * @author dharwin
 */
import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();//establishes connection   
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            String str = (String) dis.readUTF();
            System.out.println("client = " + str);
            dout.writeUTF("Hello World");
            dout.flush();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

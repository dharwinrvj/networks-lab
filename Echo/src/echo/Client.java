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

public class Client {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 6666);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            dout.writeUTF("Hello World");
            dout.flush();
            String str = (String) dis.readUTF();
            System.out.println("server = " + str);
            dout.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

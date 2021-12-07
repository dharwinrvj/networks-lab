/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arp;

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
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            String ip = (String) din.readUTF();
            Runtime r = Runtime.getRuntime();
            System.out.println("C:>arp -a "+ip);
            Process p = r.exec("arp -a " + ip);
            BufferedInputStream bin = new BufferedInputStream(p.getInputStream());
            String output = "";
            int temp = bin.read();
            while (temp != -1) {
                output += (char) temp;
                temp = bin.read();
            }
            dout.writeUTF(output);
            dout.flush();
            dout.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

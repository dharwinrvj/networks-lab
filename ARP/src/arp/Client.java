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

public class Client {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 6666);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            String ip="239.255.255.250";
            System.out.println("sending internet address - "+ip+" to server");
            System.out.println("physical address of "+ip+":");
            dout.writeUTF(ip);
            dout.flush();
            System.out.println(din.readUTF());
            dout.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

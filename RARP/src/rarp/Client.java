/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rarp;

/**
 *
 * @author dharwin
 */
import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket(1310);
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            byte[] sendbyte = new byte[1024];
            byte[] receivebyte = new byte[1024];
            String mac = "01-00-5e-7f-66-17";
            sendbyte = mac.getBytes();
            System.out.println("sending mac address - " + mac + " to server");
            DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, 1309);
            client.send(sender);
            DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
            client.receive(receiver);
            String str = new String(receiver.getData(), 0, receiver.getLength());
            String ip = str.trim();
            System.out.println("internet address of " + mac + ":");
            System.out.println(str);
            client.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

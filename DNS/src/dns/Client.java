/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dns;

import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author 19cse101
 */
import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket(1310);
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            byte[] sendbyte = new byte[1024];
            byte[] receivebyte = new byte[1024];
            String host = "google.com";
            sendbyte = host.getBytes();
            System.out.println("sending host name - " + host + " to dns");
            System.out.println("host name: " + host);
            DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, 1309);
            client.send(sender);
            DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
            client.receive(receiver);
            String str = new String(receiver.getData(), 0, receiver.getLength());
            String ip = str.trim();
            System.out.println("internet address: " + ip);
            client.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
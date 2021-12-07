/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dns;

/**
 *
 * @author 19cse101
 */
import java.net.*;
import java.io.*;

public class Server {
    
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(1309);
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            byte[] sendbyte = new byte[1024];
            byte[] receivebyte = new byte[1024];
            String[] hosts = {"zoho.com", "gmail.com", "google.com", "facebook.com"};
            String[] ips = {"172.28.251.59", "172.217.11.5", "172.217.11.14", "31.13.71.36"};
            DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
            server.receive(receiver);
            String str = new String(receiver.getData(), 0, receiver.getLength());
            String host = str.trim();
            System.out.println(host);
            String ip = "not found";
            for (int i = 0; i < 4; i++) {
                if (hosts[i].equals(host)) {
                    ip = ips[i];
                    break;
                }
            }
            sendbyte = ip.getBytes();
            DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, 1310);
            server.send(sender);
            server.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

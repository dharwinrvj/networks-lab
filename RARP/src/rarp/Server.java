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

public class Server {

    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(1309);
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            byte[] sendbyte = new byte[1024];
            byte[] receivebyte = new byte[1024];
            DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
            server.receive(receiver);
            String str = new String(receiver.getData(), 0, receiver.getLength());
            String mac = str.trim();
            Runtime r = Runtime.getRuntime();
            System.out.println("C:>arp -a");
            Process p = r.exec("arp -a");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            String ip = "Not Found";
            while ((line = br.readLine()) != null) {
                if (line.contains(mac)) {
                    ip = "  Internet Address      Physical Address      Type\n" + line;
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

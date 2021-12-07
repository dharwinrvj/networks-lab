/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadwebpage;

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
            DataInputStream din = new DataInputStream(s.getInputStream());
            FileWriter fw = new FileWriter("C:\\Users\\dharw\\Documents\\NetBeansProjects\\DownloadWebPage\\src\\downloadwebpage\\web.html");
            dout.writeUTF("https://madurai.nic.in/");
            dout.flush();
            String str;
            do {
                str = (String) din.readUTF();
                fw.write(str);
                fw.write("\n");
            } while (!str.equals("</html>"));

            System.out.println("web page written successfully");
            fw.close();
            dout.close();
            din.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

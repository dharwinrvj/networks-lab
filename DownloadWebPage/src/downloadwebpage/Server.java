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

public class Server {

    public static void main(String[] args) throws SocketException {
        try {
            String line;
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();//establishes connection   
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            String str = (String) din.readUTF();
            URL url = new URL(str);
            System.out.println("url received");
            InputStream is = url.openStream();  // throws an IOException
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            System.out.println("sending web page contents to client");
            while ((line = br.readLine()) != null) {
                dout.writeUTF(line);
            }
            dout.close();
            din.close();
            is.close();
            br.close();
            s.close();
            ss.close();
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

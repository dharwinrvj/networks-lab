/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crc;

/**
 *
 * @author dharwin
 */
import java.io.*;

public class CRC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Generator:");
        String gen = br.readLine();
        System.out.println("Enter Data:");
        String data = br.readLine();
        String code = data;
        while (code.length() < (data.length() + gen.length() - 1)) {
            code = code + "0";
        }
        code = data + div(code, gen);
        System.out.println("The transmitted Code Word is: " + code);
        System.out.println("Please enter the received Code Word: ");
        String rec = br.readLine();
        if (Integer.parseInt(div(rec, gen)) == 0) {
            System.out.println("The received code word contains no errors.");
        } else {
            System.out.println("The received code word contains errors.");
        }
    }

    static String div(String code, String gen) {
        int pointer = gen.length();
        String result = code.substring(0, pointer);
        String rem = "";
        for (int i = 0; i < gen.length(); i++) {
            if (result.charAt(i) == gen.charAt(i)) {
                rem += "0";
            } else {
                rem += "1";
            }
        }
        while (pointer < code.length()) {
            if (rem.charAt(0) == '0') {
                rem = rem.substring(1, rem.length());
                rem = rem + String.valueOf(code.charAt(pointer));
                pointer++;
            }
            result = rem;
            rem = "";
            if (result.charAt(0) == '0') {
                for (int i = 0; i < gen.length(); i++) {
                    if (result.charAt(i) == '1') {
                        rem += 1;
                    } else {
                        rem += 0;
                    }
                }
            } else {
                for (int i = 0; i < gen.length(); i++) {
                    if (result.charAt(i) == gen.charAt(i)) {
                        rem += "0";
                    } else {
                        rem += "1";
                    }
                }
            }
        }
        return rem.substring(1, rem.length());
    }
}

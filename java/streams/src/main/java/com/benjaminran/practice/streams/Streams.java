package com.benjaminran.practice.streams;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.IOException;


public class Streams {

    static final double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
    static final int[] units = { 12, 8, 13, 29, 50 };
    static final String[] descs = {
        "Java T-shirt",
        "Java Mug",
        "Duke Juggling Dolls",
        "Java Pin",
        "Java Key Chain"
    };
    
    public static void main(String[] args) {
        if(args.length!=2) usage();
        
        if(args[0].equals("write")) {
            DataOutputStream out = null;
            try {
                OutputStream outStream = args[1].equals("-") ? System.out : new FileOutputStream(args[1]);
                out = new DataOutputStream(new BufferedOutputStream(outStream));
                for(int i = 0; i<prices.length; i++) {
                    out.writeDouble(prices[i]);
                    out.writeInt(units[i]);
                    out.writeUTF(descs[i]);
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("fnfe");
            }
            catch (IOException e) {
                System.out.println("ioe");
            }
            finally {
                System.out.println("Bye.");
                if(out!=null) {
                    try {
                        out.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(args[0].equals("read")) {
            DataInputStream in = null;
            try {
                InputStream inStream = args[1].equals("-") ? System.in : new FileInputStream(args[1]);
                in = new DataInputStream(new BufferedInputStream(inStream));
                double price;
                int unit;
                String desc;
                double total = 0f;

                while(true) {
                    price = in.readDouble();
                    unit = in.readInt();
                    desc = in.readUTF();
                    System.out.format("%d units of %s at $&.2f%n", unit, desc, price);
                    total += unit * price;
                }
            }
            catch(EOFException e) {
                System.out.println("eofe");
            }
            catch(IOException e) {
                System.out.println("ioe");
            }
            finally {
                System.out.println("Bye.");
                if(in!=null) {
                    try {
                        in.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else {
            usage();
        }
    }

    private static void usage() {
        System.out.format("Usage: java -jar streams.jar command filename%n"
                          +"\tcommand: read|write%n\tfilename: file to read/write or -%n");
        System.exit(0);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package share;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vignesh
 */
public class Share {
    
    public String option;
    public String ipAddress;
    public String filePath;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;
    private FileReader fileReader = null;
    private FileOutputStream fileOutputStream = null;
    
    public Share(String option, String ipAddress, String file) {
        this.option = option;
        this.ipAddress = ipAddress;
        this.filePath = file;
    }
    
    public Share(String option) {
        this.option = option;
    }
    
    public void receive() {
        
    }
    
    public void send() {
        
        
    }
    
    public static void printErrorStatement() {
        System.err.println("Error: unrecognized or incomplete command line.");
        System.err.println("USAGE:");
        System.err.println("\tjava Share [OPTION] [IPADDRESS] [FILENAME]");
        System.err.println("\tShare file from one computer to another");
        System.err.println();
        System.err.println("\t-r receive a file from sender");
        System.err.println("\t-s send a file to a specfied [IPADDRESS]");
        exit(0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Share share = null;
        
        if(args.length == 0) {
            printErrorStatement();
            exit(1);
        }
        
        else if(args.length == 1) {
            share = new Share(args[0]);
        }
        
        else if(args.length == 3) {
            share = new Share(args[0], args[1], args[2]);
        }
        else {
            printErrorStatement();
        }
        
        switch (share.option) {
            case "-r":
                share.receive();
                break;
            case "-s":
                share.send();
                break;
            default:
                printErrorStatement();
        }
        
    }
    
}

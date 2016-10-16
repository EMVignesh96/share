/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package share;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import static java.lang.System.exit;
import java.net.ServerSocket;
import java.net.Socket;
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
    //private FileOutputStream fileOutputStream = null;

    public Share(String ipAddress, String file) {
        this.ipAddress = ipAddress;
        this.filePath = file;
    }

    public Share() {
    }

    public void receive() {
        try {
            serverSocket = new ServerSocket(6059);
            socket = serverSocket.accept();
            inputStream = new DataInputStream(socket.getInputStream());
            filePath = inputStream.readUTF();
            //fileReader = new FileReader(filePath);
            int i;
            FileWriter fileWriter = new FileWriter(filePath);
            while((i = inputStream.read()) != -1) {
                fileWriter.write(i);
            }
            inputStream.close();
            System.out.println("\nFile received : " + filePath);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void send() {
        try {
            socket = new Socket(ipAddress, 6059);
            outputStream = new DataOutputStream(socket.getOutputStream());
            //data = new String();
            outputStream.writeUTF(filePath);
            File f = new File(filePath);
            long fileLength = f.length();
            fileReader = new FileReader(filePath);
            int i, count = 0;
            while((i = fileReader.read()) != -1) {
                outputStream.write(i);
                count++;
                System.out.print("\rTransfer percentage: " + count / fileLength * 100 + "%");
            }
            outputStream.flush();
            outputStream.close();
            System.out.println("\nFile shared to peer " + ipAddress);
        } catch (Exception e) {
            System.out.println(e);
        }
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

        Share share;

        switch (args[0]) {
            case "-s":
                share = new Share(args[1], args[2]);
                share.send();
                break;
            case "-r":
                share = new Share();
                share.receive();
                break;
            case "-version":
                System.out.println("Share version \"1.0\"");
            case "-help":
            default:
                printErrorStatement();
                break;
        }

    }

}

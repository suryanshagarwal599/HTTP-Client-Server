import java.io.*;
import java.net.*;

public class server {

    public final static int PORT_NO = 13259;  //this port number can be changed
    public static String File_Address = "";  //stores the address of the file sent by the client

    public static void main (String [] args ) throws IOException {
        String cfile;

        FileInputStream finput = null;
        BufferedInputStream binput = null;
        OutputStream soutput = null;
        ServerSocket serversocket = null;
        Socket socket = null;
        try {
            serversocket = new ServerSocket(PORT_NO); //to create socket
            while (true) {
                System.out.print("Server started.");
                System.out.print("Waiting for the client to respond...");
                try {
                    socket = serversocket.accept();//accepts connection from file


                    BufferedReader clientRead = new BufferedReader(new InputStreamReader(socket.getInputStream( )));//read data from client


                    DataOutputStream clientWrite = new DataOutputStream(socket.getOutputStream( ));//writing address to the server received from the client


                    cfile = clientRead.readLine(); // to read contents of html file
                    cfile= (String) cfile.subSequence(0,cfile.length());// to calculate file size

                    File_Address="\\home\\sa\\Desktop\\"+cfile; // its the path to the file
                    System.out.print("Client responded....Connection Successful");
                    System.out.print(File_Address); // to print file address requested by client
                    File newFile = new File (File_Address); // for referencing to address of specified file
                    byte [] arr  = new byte [(int)newFile.length()]; // calculate length of file in form of array
                    finput = new FileInputStream(newFile); // generate to inputstream of newfile
                    binput = new BufferedInputStream(finput); // generating buffered stream for finput
                    binput.read(arr,0,arr.length);//reads the array form of file data and display in the client
                    soutput = socket.getOutputStream();
                    //System.out.println("Open the file on web page");
System.out.println("--------------------------------------------------------------------------");
                System.out.print("Status code: ");
               System.out.print("OK 200 \n Language used: en");
               System.out.print("--------------------------------------------------------------------------");
                    soutput.write(arr,0,arr.length);
                    soutput.flush();
                   System.out.print("Socket Closed");
                    break;
                }
                finally {
                    if (binput != null) binput.close(); // closing binput
                    if (soutput != null) soutput.close(); // closing soutput
                    if (socket!=null) socket.close(); // closing socket
                }//connection closed
            }
        }
        finally {
            if (serversocket != null) serversocket.close();
        }
    }
}

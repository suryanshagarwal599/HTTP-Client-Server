import java.awt.*;
import java.io.*;
import java.net.*;

public class client {

    public final static int PORT_NO = 13259;      //this can be changed
    public final static String SERVER = "127.0.0.1";  // localhost
    public final static String File_Address = "\\home\\sa\\Desktop\\index.html";  //address of the location of html file
    public final static int SIZE_file = 6011896; //size of the html file

    public static void main (String [] args ) throws IOException {
        int readByte; //reads the bytes file is storing
        String file = "";
        int count = 0;//count the number of bytes, program stops when count reaches maximum bytes
        FileOutputStream foutput = null;
        BufferedOutputStream boutput = null;
        Socket socket2 = null;
        try {
            socket2 = new Socket(SERVER, PORT_NO);
            System.out.print("Connecting...");
            System.out.print("Connection Successful");
            System.out.print("Enter File name:");
            BufferedReader readUserFile = new BufferedReader(new InputStreamReader(System.in));//reads file name

            DataOutputStream serverOutput = new DataOutputStream(socket2.getOutputStream()); //receiving data output stream from server
            file = readUserFile.readLine();//reads data from file
            serverOutput.writeBytes(file + '\n'); // writing server output to file
            File newFile = new File (File_Address); // path to the file
            byte [] arr  = new byte [(int)newFile.length()]; // finding file length in form of array
            InputStream inp = socket2.getInputStream(); // generate input stream of socket
            foutput = new FileOutputStream(File_Address); // generate output stream of address of file
            boutput = new BufferedOutputStream(foutput); // generating bufferred stream of output
            readByte = inp.read(arr,0,arr.length); // read from the file sent by the server
            count = readByte;
            boutput.write(arr, 0 , count);//display the array
            boutput.flush();

            BufferedReader br = new BufferedReader(new FileReader(File_Address)); // read file

            String str;
            while ((str = br.readLine()) != null)
                System.out.print(str);

            File filebrowse = new File("/home/sa/Desktop/index.html");//opens the file in the browser

                System.out.print("--------------------------------------------------------------------------");
                System.out.print("Status code: ");
               System.out.print("OK 200 \n Language used: en");
               System.out.print("--------------------------------------------------------------------------");
            System.out.print("Opening the file in browser.");
            Desktop.getDesktop().browse(filebrowse.toURI()); // displaying the file sent by server in default browser
            System.out.print("Client closed \n connection closed");
        }
        finally {
            if (foutput != null) foutput.close();
            if (boutput != null) boutput.close();
            if (socket2 != null) socket2.close();//close the connections
        }
    }

}

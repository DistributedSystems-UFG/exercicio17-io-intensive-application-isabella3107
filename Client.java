import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws Exception {

        Socket socket =
                new Socket("localhost",5000);

        PrintWriter out =
                new PrintWriter(
                        socket.getOutputStream(),true);

        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));

        out.println("READ");

        System.out.println(in.readLine());

        socket.close();
    }
}
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.concurrent.*;

public class PoolServer {

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(5000);

        ExecutorService pool =
                Executors.newFixedThreadPool(10);

        while(true) {

            Socket client = server.accept();

            pool.execute(() -> {

                try {

                    BufferedReader in =
                            new BufferedReader(
                                    new InputStreamReader(
                                            client.getInputStream()));

                    PrintWriter out =
                            new PrintWriter(
                                    client.getOutputStream(), true);

                    String request = in.readLine();

                    if("READ".equals(request)) {

                        String content =
                                Files.readString(
                                        Path.of("dados.txt"));

                        out.println(content);
                    }

                    client.close();

                } catch(Exception e) {
                    e.printStackTrace();
                }

            });
        }
    }
}
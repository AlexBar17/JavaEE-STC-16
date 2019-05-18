package lesson9.task01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable {

    ServerSocket ss = null;
    Socket s = null;

    InputStream fromClient;
    OutputStream toClient;
    BufferedWriter clientWriter;
    BufferedReader clientReader;

    public ServerThread(ServerSocket sSocket) {
        ss = sSocket;
    }

    @Override
    public void run() {

        try {
            s = ss.accept();
        } catch (Exception ex) {
            Thread.interrupted();
        }


        try {
            fromClient = s.getInputStream();
            toClient = s.getOutputStream();
            clientReader = new BufferedReader(new InputStreamReader(fromClient));
            clientWriter = new BufferedWriter(new OutputStreamWriter(toClient));

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                String messageIn = clientReader.readLine();
                clientWriter.write(messageIn + "!!\n");
                clientWriter.newLine();
                clientWriter.flush();
                System.out.println(messageIn);

                if(messageIn.equals("quit")) {
                    break;
                }

            } catch (IOException e) {
                break;
            }
        }
        try {
            fromClient.close();
            toClient.close();
        } catch (IOException e) {
            Thread.interrupted();
        }

    }
}

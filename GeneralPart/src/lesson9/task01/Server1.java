package lesson9.task01;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server1 {

    public static final Integer SERVER_PORT = 4999; // Прослушиваемый порт
    public static ServerSocket serverSocket = null; //TCP socket

    public static void main(String[] args) throws IOException {

        ArrayList<Socket> socketList = new ArrayList<>();

        serverSocket = new ServerSocket(SERVER_PORT,0, InetAddress.getByName("127.0.0.1"));
        Socket socket = serverSocket.accept(); // Слушать!
        Socket socket1 = serverSocket.accept(); // Слушать!


        InputStream fromClient = socket.getInputStream();
        OutputStream toClient = socket.getOutputStream();
        BufferedReader clientReader = new BufferedReader(new InputStreamReader(fromClient));

        InputStream fromClient1 = socket1.getInputStream();
        OutputStream toClient1 = socket1.getOutputStream();
        BufferedReader clientReader1 = new BufferedReader(new InputStreamReader(fromClient1));
        BufferedWriter clientWriter = new BufferedWriter(new OutputStreamWriter(toClient1));

        String message, message1;
//        while ((message = clientReader.readLine()) != null) {
        while (true) {
            message = clientReader.readLine();
            message1 = clientReader1.readLine();
            if (message != null) {
                System.out.println("Server read: " + message);

            } else {
                socketList.add(socket);
                socket = serverSocket.accept();
                fromClient = socket.getInputStream();
                clientReader = new BufferedReader(new InputStreamReader(fromClient));
                for (Socket s :
                        socketList) {
                    System.out.println(s);
                }
            }
            if (message1 != null) {
                System.out.println("Не ждали!: " + message1);

            }
//            clientWriter.write("\"" + message + "\" received \n");
//            clientWriter.flush();
        }
    }

}

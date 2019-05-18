package lesson9.task01;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        while (!(message.equals("q"))) {
//            System.out.println(message);

            Socket socket = new Socket("127.0.0.1", Server1.SERVER_PORT);

            BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));


            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();

//            System.out.println("server echo " + bufferedReader.readLine());

            System.out.println(socket);
            socket.close();
            System.out.println(socket);
            message = scanner.nextLine();
            socket = null;
        }
    }
}

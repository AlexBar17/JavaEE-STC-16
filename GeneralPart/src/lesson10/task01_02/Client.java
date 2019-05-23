package lesson10.task01_02;

/*
Задание 1. Разработать приложение - многопользовательский чат, в котором участвует произвольное количество клиентов.
Каждый клиент после запуска отправляет свое имя серверу. После чего начинает отправлять ему сообщения.
Каждое сообщение сервер подписывает именем клиента и рассылает всем клиентам (broadcast).

Задание 2.  Усовершенствовать задание 1:

a.      добавить возможность отправки личных сообщений (unicast).

b.      добавить возможность выхода из чата с помощью написанной в чате команды «quit»
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    static BufferedReader in = null;
    static PrintWriter out = null;
    static Socket socket = null;

    public static void main(String[] args) {



        Scanner scan = new Scanner(System.in);

        try {
            socket = new Socket("127.0.0.1", Server.SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Введите ваше имя:");
            out.println(scan.nextLine());

            Resender resender = new Resender();
            Thread resendThread = new Thread(resender);
            resendThread.start();

            String str = "";
            while (!str.equals("exit")) {
                str = scan.nextLine();
                out.println(str);
            }
            resender.setStop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (Exception e) {
            }
        }

    }


    private static class Resender implements Runnable {

        private boolean stoped;


        public void setStop() {
            stoped = true;
        }


        @Override
        public void run() {
            try {
                while (!stoped) {
                    String str = in.readLine();
                    System.out.println(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}









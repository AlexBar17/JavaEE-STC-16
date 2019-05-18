package lesson9.task01;

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

            System.out.println("������ ᢮� ���:");
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
                System.err.println("��⮪� �� �뫨 �������!");
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
                System.err.println("�訡�� �� ����祭�� ᮮ�饭��.");
                e.printStackTrace();
            }
        }
    }
}









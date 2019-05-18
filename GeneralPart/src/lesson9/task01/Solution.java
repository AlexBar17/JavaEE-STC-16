package lesson9.task01;

import java.net.ServerSocket;

public class Solution {

    public static void main(String[] args) {

        ServerSocket ss = null;
        try
        {
            ss = new ServerSocket(9999);
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            System.exit(0);
        }

        ServerThread sThread = null;

        sThread = new ServerThread(ss);
        Thread thread = new Thread(sThread);
        thread.start();

        System.out.println(
                "Waiting connection...");

        try
        {
            thread.join();
        }
        catch(InterruptedException ex)
        {
            System.out.println(ex.toString());
        }

        try
        {
            ss.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }

    }
}

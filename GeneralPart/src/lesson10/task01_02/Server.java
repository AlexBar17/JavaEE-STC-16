package lesson10.task01_02;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    public static final Integer SERVER_PORT = 4999; // Прослушиваемый порт
    public static ServerSocket serverSocket = null; //TCP socket
    private static List<Connection> connections =
            Collections.synchronizedList(new ArrayList<Connection>());
    private static Map<String, Connection> contactBook = new HashMap<>();

    public static void main(String[] args) {


        try {
            serverSocket = new ServerSocket(SERVER_PORT,0, InetAddress.getByName("127.0.0.1"));

            while (true) {
                Socket socket = serverSocket.accept();

                Connection con = new Connection(socket);
                connections.add(con);


                con.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }


    private static void closeAll() {
        try {
            serverSocket.close();

            synchronized(connections) {
                Iterator<Connection> iter = connections.iterator();
                while(iter.hasNext()) {
                    iter.next().close();
                }
            }
        } catch (Exception e) {
        }
    }

    private static class Connection extends Thread {
        private BufferedReader in;
        private PrintWriter out;
        private Socket socket;

        private String name = "";

        public Connection(Socket socket) {
            this.socket = socket;

            try {
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

            } catch (IOException e) {
                close();
            }
        }


        @Override
        public void run() {
            try {
                name = in.readLine();
                contactBook.put(name, this);
                synchronized(connections) {
                    Iterator<Connection> iter = connections.iterator();
                    while(iter.hasNext()) {
                        ((Connection) iter.next()).out.println(name + " cames now");
                    }
                }

                String str = "";
                while (true) {
                    str = in.readLine();
                    if(str.equals("quite")) break;
                    if(str.equals("~")) {
                        String nameForPrivate = in.readLine();
                        Connection privateConnection = contactBook.get(nameForPrivate);
                            str = in.readLine();
                        System.out.println("Считал " + str);
                        privateConnection.out.println(name+" told you: " + str);

                        continue;
                    }

                    synchronized(connections) {
                        Iterator<Connection> iter = connections.iterator();
                        while(iter.hasNext()) {
                            ((Connection) iter.next()).out.println(name + ": " + str);
                        }
                    }
                }

                synchronized(connections) {
                    Iterator<Connection> iter = connections.iterator();
                    while(iter.hasNext()) {
                        ((Connection) iter.next()).out.println(name + " has left");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

        public void close() {
            try {
                in.close();
                out.close();
                socket.close();


                connections.remove(this);
                if (connections.size() == 0) {
                    Server.closeAll();
                    System.exit(0);
                }
            } catch (Exception e) {
            }
        }
    }

}



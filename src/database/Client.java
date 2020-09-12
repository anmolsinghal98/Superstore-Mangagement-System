package database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static SuperStore myObj;

    static String serverName = "192.168.51.7";
    static int port = 2112;

    static Socket server;
    static ObjectOutputStream outStream;
    static ObjectInputStream inStream;

    {
        try {
            server = new Socket(serverName, port);
            System.out.println("Connected to: " + server.getRemoteSocketAddress());
//            inStream = new ObjectInputStream(server.getInputStream());
            inStream = null;
            outStream = null;
            myObj = null;
        } catch (Exception e) {}
    }

    private static void callServer() throws IOException {
        myObj = null;
        try {
            Object o1 = inStream.readObject();
            myObj = (SuperStore) o1;
        } catch (ClassNotFoundException e) {
            System.err.println("Error inStream Server-Client");
        }
        inStream.close();
        server.close();
    }

    public static void fetchData() {
        try {
            server = new Socket(serverName, port);
            System.out.println("Connected to: " + server.getRemoteSocketAddress());
            inStream = null;
            outStream = null;
            myObj = null;
        } catch (Exception e) {}
        try {
            outStream = new ObjectOutputStream(server.getOutputStream());
            outStream.writeObject(new Boolean(false));
            outStream.flush(); // Clearing the contents of the stream
        } catch (IOException e) {
            System.err.println("Flag not transmitted");
            e.printStackTrace();
        } finally {
            if(outStream!=null) {
                try {
                    outStream.close();
                    outStream = null;
                } catch (Exception e){}
            }
        }

        try {
            inStream = new ObjectInputStream(server.getInputStream());
            myObj = (SuperStore) inStream.readObject();
            Main.serialize(myObj);
            Main.deserialize();
        } catch (Exception e) {
            System.err.println("Error in getting Data");
            e.printStackTrace();
        } finally {
            if(inStream!=null) {
                try {
                    inStream.close();
                    inStream = null;
                } catch (Exception e) {}
            }
        }
        try {
            server.close();
        } catch (Exception e) {}
    }

    public static void sendData() {
        try {
            server = new Socket(serverName, port);
            System.out.println("Connected to: " + server.getRemoteSocketAddress());
            inStream = null;
            outStream = null;
            myObj = null;
        } catch (Exception e) {}
        try {
            outStream = new ObjectOutputStream(server.getOutputStream());
            outStream.writeObject(new Boolean(true));
            outStream.flush(); // Clearing the contents of the stream
        } catch (IOException e) {
            System.err.println("Flag not transmitted");
            e.printStackTrace();
        } finally {
            if(outStream!=null) {
                try {
                    outStream.close();
                    outStream = null;
                } catch (Exception e){}
            }
        }

        try {
            myObj = Main.getMySuperStore();
            outStream = new ObjectOutputStream(server.getOutputStream());
            outStream.writeObject(myObj);
            Main.serialize(myObj);
//            myObj = (SuperStore) inStream.readObject();
        } catch (Exception e) {
            System.err.println("Error in getting Data");
            e.printStackTrace();
        } finally {
            if(outStream!=null) {
                try {
                    outStream.close();
                    outStream = null;
                } catch (Exception e) {}
            }
        }
        try {
            server.close();
        } catch (Exception e) {}
    }
}

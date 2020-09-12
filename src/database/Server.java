package database;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket me = new ServerSocket(2112);
        ExecutorService exec = Executors.newFixedThreadPool(2);
        while(true) {
            Socket connection = me.accept();
            System.out.println("connected");
            ConnectionHandler task = new ConnectionHandler(connection);
            new Thread(task).start();
        }
    }
}

class ConnectionHandler implements Runnable {
    Socket connection;
    ConnectionHandler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        ObjectOutputStream outStream = null;
        ObjectInputStream inStream = null;
        Boolean streamDir = false;
        try {
            inStream = new ObjectInputStream(connection.getInputStream());
            streamDir = (Boolean) inStream.readObject();
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        } catch (ClassNotFoundException e) {
            System.err.println("Invalid Stream Object");
        }
        switch (streamDir?1:0) {
            case 1:
                try {
                    SuperStore tempStore = (SuperStore) inStream.readObject();
                    try {
                        Main.serialize(tempStore);
                        Main.deserialize();
                    } catch (Exception e) {
                        System.err.println("Serialization Error: " + e.getStackTrace());
                    }
                } catch (Exception e) {
                    System.err.println(e.getStackTrace());
                } finally {
                    try {
                        if(inStream!=null) inStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 0:
                try {
                    outStream = new ObjectOutputStream(connection.getOutputStream());
                    outStream.writeObject(Main.getMySuperStore());
                    Main.serialize(Main.getMySuperStore());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(outStream!=null) {
                        try {
                            outStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            default:
                System.err.println("What the f*ck is happening");
                break;
        }
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package database;

import java.io.*;

public class Main {

    private static SuperStore mySuperStore;

    public static SuperStore getMySuperStore() {
        return mySuperStore;
    }

    public static void serialize(SuperStore s1) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("LeMarche.txt"));
            out.writeObject(s1);
        } finally {
            out.close();
        }
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("LeMarche.txt"));
            mySuperStore = (SuperStore) in.readObject();
        } finally {
            in.close();
        }
    }
}

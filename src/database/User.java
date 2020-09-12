package database;

import java.io.Serializable;

public class User implements Serializable {

    private Credentials credentials;

    public Credentials getCredentials() {
        return credentials;
    }

    public User(String name, long mobile) {
        this.credentials = new Credentials(name, mobile, "check", "check","check");
    }

    public User(String name, long mobile, String id, String pass,String passHint) {
        this.credentials = new Credentials(name, mobile, id, pass, passHint);
    }

    public void setName(String name) {
        credentials.setName(name);
    }

    public void setMobileNumber(long mobileNumber) {
        credentials.setMobileNumber(mobileNumber);
    }


}

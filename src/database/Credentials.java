package database;

import java.io.Serializable;

public class Credentials implements Serializable {

    private String name;
    private final String id;
    private final String pass;
    private long mobileNumber;
    private String passHint;

    public Credentials(String name, long mobileNumber, String id, String pass,String passHint) {
        this.name = name;
        this.id = id;
        this.pass = pass;
        this.mobileNumber = mobileNumber;
        this.passHint=passHint;
    }

    public Credentials(String id,String pass) {
        this.id=id;
        this.pass=pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj!=null && getClass().equals(obj.getClass())) {
            Credentials c = (Credentials) obj;
            if(c.getId().equals(getId()) && c.getPass().equals(getPass())) {
                return true;
            }
            else{
                return false;
            }
        }
        return false;

    }

    public String getPassHint() {
        return passHint;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public long getMobileNumber() {
        return mobileNumber;
    }

    public String getPass() {
        return pass;
    }
}

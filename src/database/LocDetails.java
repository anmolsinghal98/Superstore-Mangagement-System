package database;

import java.io.Serializable;

public class LocDetails implements Serializable {
    private final long id;
    private String name;
    private String loc;
    private static long totId;

    public LocDetails(String name,String loc) {
        totId++;
        this.id = totId;
        this.name=name;
        this.loc=loc;
    }

    public LocDetails() {
        totId++;
        id = totId;
        this.name="";
        this.loc="";
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLoc() {
        return loc;
    }
}

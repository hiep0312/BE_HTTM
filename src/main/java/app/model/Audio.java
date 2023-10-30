package app.model;

import java.sql.Timestamp;

public class Audio {
    private int id;
    private String name;
    private String path;
    private Timestamp date;
    private Timestamp update;

    public Audio() { 
    }

    public Audio(int id, String name, String path, Timestamp date, Timestamp update) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.date = date;
        this.update = update;
    }

    public Audio(int id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public Audio(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getUpdate() {
        return update;
    }

    public void setUpdate(Timestamp update) {
        this.update = update;
    }

}

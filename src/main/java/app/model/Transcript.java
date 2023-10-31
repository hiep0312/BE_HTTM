package app.model;

import java.sql.Timestamp;

public class Transcript {
    private int id;
    private String name;
    private String content;
    private Timestamp date;
    private Timestamp lastupdate;

    public Transcript() {
    }

    public Transcript(int id, String name, String content, Timestamp date, Timestamp lastUpdate) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.date = date;
        this.lastupdate = lastUpdate;
    }

    public Transcript(String name, String content, Timestamp date, Timestamp lastUpdate) {
        this.name = name;
        this.content = content;
        this.date = date;
        this.lastupdate = lastUpdate;
    }

    public Transcript(String name, String content) {
        this.name = name;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Timestamp lastUpdate) {
        this.lastupdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Transcript [id=" + id + ", name=" + name + ", content=" + content + ", date=" + date + ", lastUpdate="
                + lastupdate + "]";
    }

    

}

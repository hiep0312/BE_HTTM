package app.model;

import java.sql.Timestamp;

public class Sample {
    private int id;
    private String name;
    private int audioId;
    private int transcriptId;
    private Timestamp date;
    private Timestamp lastupdate;

    public Sample(int id, String name, int audioId, int transcriptId, Timestamp date, Timestamp update) {
        this.id = id;
        this.name = name;
        this.audioId = audioId;
        this.transcriptId = transcriptId;
        this.date = date;
        this.lastupdate = update;
    }

    public Sample() {
    }

    public Sample(int id, int audioId, int transcriptId, Timestamp date, Timestamp update) {
        this.id = id;
        this.audioId = audioId;
        this.transcriptId = transcriptId;
        this.date = date;
        this.lastupdate = update;
    }

    public Sample(int audioId, int transcriptId) {
        this.audioId = audioId;
        this.transcriptId = transcriptId;
    }

    public Sample(String name, int audioId, int transcriptId) {
        this.name = name;
        this.audioId = audioId;
        this.transcriptId = transcriptId;
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

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public int getTranscriptId() {
        return transcriptId;
    }

    public void setTranscriptId(int transcriptId) {
        this.transcriptId = transcriptId;
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

    public void setUpdate(Timestamp lastupdate) {
        this.lastupdate = lastupdate;
    }

}

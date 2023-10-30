package app.model;

import java.sql.Timestamp;

public class Sample {
    private int id;
    private int audioId;
    private int transcriptId;
    private Timestamp date;
    private Timestamp update;

    public Sample() {
    }

    public Sample(int id, int audioId, int transcriptId, Timestamp date, Timestamp update) {
        this.id = id;
        this.audioId = audioId;
        this.transcriptId = transcriptId;
        this.date = date;
        this.update = update;
    }

    public Sample(int audioId, int transcriptId) {
        this.audioId = audioId;
        this.transcriptId = transcriptId;
    }

    public Sample(int id, int audioId, int transcriptId) {
        this.id = id;
        this.audioId = audioId;
        this.transcriptId = transcriptId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getUpdate() {
        return update;
    }

    public void setUpdate(Timestamp update) {
        this.update = update;
    }

}

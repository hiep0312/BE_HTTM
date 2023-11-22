package app.model;

import java.sql.Timestamp;

public class Model {
    private int id;
    private String name;
    private String path;
    private Timestamp date;
    private float mos; // mean opinion score
    private int datasetId;
    private int algorithmId;
    private String task;

    public Model() {
    }

    public Model(int id) {
        this.id = id;
    }

    // Constructor for create new model
    public Model(String name, String path, float mos, int datasetId, int algorithmId, String task) {
        this.name = name;
        this.path = path;
        this.mos = mos;
        this.datasetId = datasetId;
        this.algorithmId = algorithmId;
        this.task = task;
    }

    public Model(int id, String name, String path, Timestamp date, float mos, int datasetId, int algorithmId,
            String task) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.date = date;
        this.mos = mos;
        this.datasetId = datasetId;
        this.algorithmId = algorithmId;
        this.task = task;
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

    public float getMos() {
        return mos;
    }

    public void setMos(float mos) {
        this.mos = mos;
    }

    public int getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(int datasetId) {
        this.datasetId = datasetId;
    }

    public int getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(int algorithmId) {
        this.algorithmId = algorithmId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

}

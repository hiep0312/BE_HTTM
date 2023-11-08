package app.model;

public class Algorithm {
    private int id;
    private String name;
    private String description;
    private String task;

    public Algorithm() {
    }

    public Algorithm(int id, String name, String description, String task) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.task = task;
    }

    public Algorithm(String name, String description, String task) {
        this.name = name;
        this.description = description;
        this.task = task;
    }

    public Algorithm(String name, String task) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    
}

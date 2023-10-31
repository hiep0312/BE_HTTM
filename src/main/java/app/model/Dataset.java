package app.model;

public class Dataset {
    private int id;
    private String name;

    public Dataset() {
    }

    public Dataset(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dataset(String name) {
        this.name = name;
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

}

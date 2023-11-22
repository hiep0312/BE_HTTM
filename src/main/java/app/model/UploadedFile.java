package app.model;

import org.springframework.web.multipart.MultipartFile;

public class UploadedFile {
    private int id;
    private String name;
    private MultipartFile file;

    public UploadedFile() {
    }

    public UploadedFile(int id) {
        this.id = id;
    }

    public UploadedFile(String name, MultipartFile file) {
        this.name = name;
        this.file = file;
    }
    

    public UploadedFile(int id, String name, MultipartFile file) {
        this.id = id;
        this.name = name;
        this.file = file;
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}

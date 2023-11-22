package app.model;

public class ObjectIndex {
    public int id;
    public int start_idx;
    public int count;
    public String sortBy;
    public boolean ascend;

    public ObjectIndex() {
        
    }

    public ObjectIndex(int start_idx, int count, String sortBy, boolean ascend) {
        this.start_idx = start_idx;
        this.count = count;
        this.sortBy = sortBy;
        this.ascend = ascend;
    }


    public ObjectIndex(int id, int start_idx, int count, String sortBy, boolean ascend) {
        this.id = id;
        this.start_idx = start_idx;
        this.count = count;
        this.sortBy = sortBy;
        this.ascend = ascend;
    }

    public ObjectIndex(int start_idx, int count) {
        this.start_idx = start_idx;
        this.count = count;
        this.sortBy = "lastupdate";
        this.ascend = false;
    }


    public ObjectIndex(int id, int start_idx, int count) {
        this.id = id;
        this.start_idx = start_idx;
        this.count = count;
    }


    public int getStart_idx() {
        return start_idx;
    }

    public void setStart_idx(int start_idx) {
        this.start_idx = start_idx;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public boolean isAscend() {
        return ascend;
    }

    public void setAscend(boolean ascend) {
        this.ascend = ascend;
    }

    
}
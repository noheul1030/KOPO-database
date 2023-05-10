package exam.case1.dto;

public class Table1Dto {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Table1Dto [id=" + id + ", name=" + name + "]";
    }

}

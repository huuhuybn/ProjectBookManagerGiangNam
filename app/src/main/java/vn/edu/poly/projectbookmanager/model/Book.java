package vn.edu.poly.projectbookmanager.model;

public class Book {
    private String Img;
    private String Name;
    private String Type;

    public Book(String img, String name, String type) {
        Img = img;
        Name = name;
        Type = type;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}

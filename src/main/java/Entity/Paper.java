package Entity;

public class Paper {
    String id;
    String name;
    String author;
    String major;
    String a_class;
    int year;


    public Paper(String id, String name, String author, String major, String a_class, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.major = major;
        this.a_class = a_class;
        this.year = year;
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getA_class() {
        return a_class;
    }

    public void setA_class(String a_class) {
        this.a_class = a_class;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return id+name;
    }
}

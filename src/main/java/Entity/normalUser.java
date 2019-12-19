package Entity;

public class normalUser {
    String id;
    String name;
    String gender;
    int age;
    String password;

    public normalUser(String id, String name, String gender, int age, String password) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.password = password;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

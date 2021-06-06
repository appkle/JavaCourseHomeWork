public class User {
    private int id;
    private String name;
    private int sex;

    public User(int id, String name, int sex){
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }
}

package method2;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;

    public Student(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void print(){
        System.out.println(id + ":" + name);
    }
}

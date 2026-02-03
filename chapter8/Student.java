package chapter8;

public class Student extends Person {

    public Student(String name, String id, String addr, String email) {
        super(name, id, addr, email);
    }
    @Override
    public String getType() {
        return "Student";
    }
}

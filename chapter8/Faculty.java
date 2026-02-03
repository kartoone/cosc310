package chapter8;

public class Faculty extends Employee {
    public Faculty(String name, String id, String addr, String email) {
        super(name, id, addr, email);
    }
    @Override
    public String getType() {
        return "Faculty";
    }
}

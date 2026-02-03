package chapter8;

public class Staff extends Employee {
    public Staff(String name, String id, String addr, String email) {
        super(name, id, addr, email);
    }

    @Override
    public String getType() {
        return "Staff";
    }
}

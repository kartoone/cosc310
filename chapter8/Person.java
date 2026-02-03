package chapter8;

// abstract forces other parts of program
// to create a specific kind of Person
// whenever they are constructing a person
abstract public class Person {

    String name;
    protected String id;
    protected String addr;
    protected String email;

    protected Person(String name, String id, String addr, String email) {
        this.name = name;
        this.id = id;
        this.addr = addr;
        this.email = email;
    }

    abstract public String getType();

    public String getName() {
        return name;
    }       

    public String getId() {
        return id;
    }

    public String getAddr() {
        return addr;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
        // execute query to update the database
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

}

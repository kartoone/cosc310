import chapter8.Person;

public class BannerAdmin extends Person {

    protected BannerAdmin(String name, String id, String addr, String email) {
        super(name, id, addr, email);
    }

    @Override
    public String getType() {
        return "BannerAdmin"; // + name; (error because of default access instead of protected access)
    }
    
}

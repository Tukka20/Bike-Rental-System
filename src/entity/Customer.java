package entity;

public class Customer {

    private int id;

    private  String name;

    private String userName;

    private  String pass;
    public Customer()
    {

    }

    public Customer(int id, String userName, String pass) {
        this.id = id;
        this.userName = userName;
        this.pass = pass;
    }

    public Customer(int id, String name, String userName, String pass) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = this.name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "[ Customer Id : "+id+" | Customer Name : "+name+" | Username : "+userName+" | Password : "+pass+" ]";
    }
}

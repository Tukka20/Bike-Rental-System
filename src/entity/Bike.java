package entity;

public class Bike {

    private int id;

    private String bike;

    private String model;

    private double price;

    private boolean availability;

    public Bike(){

    }


    public Bike(int id, String bike, String model, double price,boolean availability) {
        this.id = id;
        this.bike = bike;
        this.model = model;
        this.price = price;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBike() {
        return bike;
    }

    public void setBike(String bike) {
        this.bike = bike;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "[ Bike id : "+id+" | Bike Name : "+bike+" | Model : "+model+" | Price : "+price+" | Availability : "+(availability? "Yes": "No")+" ]";
    }
}

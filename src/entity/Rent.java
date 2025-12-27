package entity;

import java.sql.Timestamp;

public class Rent {

    private int id;
    private int customerId;
    private int bikeId;
    private Timestamp rentDate;
    private Timestamp returnDate;
    private double totalCost;

    public Rent(int id, int customer_id, int bike_id, Timestamp rent_date, Timestamp return_date, double total_cost) {
        this.id = id;
        this.customerId = customer_id;
        this.bikeId = bike_id;
        this.rentDate = rent_date;
        this.returnDate = return_date;
        this.totalCost = total_cost;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customerId;
    }

    public void setCustomer_id(int customer_id) {
        this.customerId = customer_id;
    }

    public int getBike_id() {
        return bikeId;
    }

    public void setBike_id(int bike_id) {
        this.bikeId = bike_id;
    }

    public Timestamp getRent_date() {
        return rentDate;
    }

    public void setRent_date(Timestamp rent_date) {
        this.rentDate = rent_date;
    }

    public Timestamp getReturn_date() {
        return returnDate;
    }

    public void setReturn_date(Timestamp return_date) {
        this.returnDate = return_date;
    }

    public double getTotal_cost() {
        return totalCost;
    }

    public void setTotal_cost(double total_cost) {
        this.totalCost = total_cost;
    }

    @Override
    public String toString() {
        return "[Rental ID: " + id +
                " | Customer ID: " + customerId +
                " | Bike ID: " + bikeId +
                " | Rent Date: " + rentDate +
                " | Return Date: " + (returnDate != null ? returnDate : "Not Returned") +
                " | Total Cost: ₹" + totalCost + "]";
    }
}





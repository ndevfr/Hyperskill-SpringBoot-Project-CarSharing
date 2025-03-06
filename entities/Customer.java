package carsharing.entities;

public class Customer {
    private Integer id;
    private String name;
    private Integer rentedCarId;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
        this.rentedCarId = null;
    }

    public Customer(int id, String name, int rentedCarId) {
        this.id = id;
        this.name = name;
        this.rentedCarId = rentedCarId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRentedCarId() {
        if(rentedCarId == null){
            return 0;
        }
        return rentedCarId;
    }

    public void setRentedCarId(Integer rentedCarId) {
        this.rentedCarId = rentedCarId;
    }

}
package carsharing.dao;

import carsharing.entities.Company;
import carsharing.entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private static final CustomerDao INSTANCE = new CustomerDao();
    private final Connection connection = ConnectionManager.getConnection();

    private CustomerDao() {

    }

    public static CustomerDao get() {
        return INSTANCE;
    }

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM customer;";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Customer customer = getCustomer(resultSet);
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            System.out.println("Failed to get customer list.");
            e.printStackTrace();
        }
        return List.of();
    }

    private static Customer getCustomer(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int rentedCarId = resultSet.getInt("rented_car_id");
        Customer customer = new Customer(id, name, rentedCarId);
        return customer;
    }

    public boolean addCustomer(String customerName) {
        String sql = "INSERT INTO customer (name) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, customerName);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.printf("Failed to add [%s] customer.%n", customerName);
            e.printStackTrace();
            return false;
        }
    }

    public Customer getById(Integer customerId) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerId); // Set the parameter
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getCustomer(resultSet);
            } else {
                System.out.printf("No customer found with id [%d].%n", customerId);
                return null;
            }
        } catch (SQLException e) {
            System.out.printf("Failed to get customer with id [%d].%n", customerId);
            e.printStackTrace();
            return null;
        }
    }

    public void setRentedCarId(Integer customerId, Integer carId) {
        String sql = "UPDATE customer SET rented_car_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (carId == null) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, carId);
            }
            ps.setInt(2, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Failed to set rented car id for customer with id [%d].%n", customerId);
            e.printStackTrace();
        }
    }

    public boolean createTable() {
        String sql = """
                 CREATE TABLE IF NOT EXISTS customer (
                   id INTEGER PRIMARY KEY AUTO_INCREMENT,
                   name VARCHAR UNIQUE NOT NULL,
                   rented_car_id INTEGER,
                   FOREIGN KEY (rented_car_id) REFERENCES car(id)
               )""";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
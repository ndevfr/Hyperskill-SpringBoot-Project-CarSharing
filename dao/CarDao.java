package carsharing.dao;

import carsharing.entities.Car;
import carsharing.entities.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    private static final CarDao INSTANCE = new CarDao();
    private final Connection connection = ConnectionManager.getConnection();

    private CarDao() {

    }

    public static CarDao get() {
        return INSTANCE;
    }

    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM car;";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Car car = getCar(resultSet);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            System.out.println("Failed to get car list.");
            e.printStackTrace();
        }
        return List.of();
    }

    private static Car getCar(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int companyId = resultSet.getInt("company_id");
        Car car = new Car(id, name, companyId);
        return car;
    }

    public Car getById(Integer carId) {
        String sql = "SELECT * FROM car WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, carId); // Set the parameter
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getCar(resultSet);
            } else {
                System.out.printf("No car found with id [%d].%n", carId);
                return null;
            }
        } catch (SQLException e) {
            System.out.printf("Failed to get car with id [%d].%n", carId);
            e.printStackTrace();
            return null;
        }
    }

    public List<Car> getByCompanyId(Integer companyId) {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM car WHERE company_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, companyId); // Set the parameter
            ResultSet resultSet = preparedStatement.executeQuery();
            //try (Statement statement = connection.createStatement()) {
            //    String sql = "SELECT * FROM car WHERE company_id = " + companyId;
            //ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Car car = getCar(resultSet);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            System.out.println("Failed to get car list.");
            e.printStackTrace();
        }
        return List.of();
    }

    public List<Car> getNotRantedByCompanyId(Integer companyId) {
        List<Car> cars = new ArrayList<>();
        String sql = """
                SELECT * FROM car 
                WHERE company_id = ?
                AND id NOT IN (SELECT rented_car_id FROM customer WHERE rented_car_id IS NOT NULL)""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, companyId); // Set the parameter
            ResultSet resultSet = preparedStatement.executeQuery();
            //try (Statement statement = connection.createStatement()) {
            //    String sql = "SELECT * FROM car WHERE company_id = " + companyId;
            //ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Car car = getCar(resultSet);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            System.out.println("Failed to get car list.");
            e.printStackTrace();
        }
        return List.of();
    }

    public boolean addCar(int companyId, String carName) {
        String sql = "INSERT INTO car (name, company_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, carName);
            ps.setInt(2, companyId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.printf("Failed to add [%s] car.%n", carName);
            e.printStackTrace();
            return false;
        }
    }

    public boolean createTable() {
        String sql = """
                 CREATE TABLE IF NOT EXISTS car (
                   id INTEGER PRIMARY KEY AUTO_INCREMENT,
                   name VARCHAR UNIQUE NOT NULL,
                   company_id INTEGER NOT NULL,
                   FOREIGN KEY (company_id) REFERENCES company(id)
               )""";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
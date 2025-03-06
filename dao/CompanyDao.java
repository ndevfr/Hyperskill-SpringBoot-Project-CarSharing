package carsharing.dao;

import carsharing.entities.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao {
    private static final CompanyDao INSTANCE = new CompanyDao();
    private final Connection connection = ConnectionManager.getConnection();

    private CompanyDao() {

    }

    public static CompanyDao get() {
        return INSTANCE;
    }

    public List<Company> getAll() {
        List<Company> companies = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM company;";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Company company = getCompany(resultSet);
                companies.add(company);
            }
            return companies;
        } catch (SQLException e) {
            System.out.println("Failed to get company list.");
            e.printStackTrace();
        }
        return List.of();
    }

    private static Company getCompany(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Company company = new Company(id, name);
        return company;
    }

    public boolean addCompany(String companyName) {
        String sql = "INSERT INTO company (name) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, companyName);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.printf("Failed to add [%s] company.%n", companyName);
            e.printStackTrace();
            return false;
        }
    }

    public Company getById(Integer companyId) {
        String sql = "SELECT * FROM company WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, companyId); // Set the parameter
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getCompany(resultSet);
            } else {
                System.out.printf("No company found with id [%d].%n", companyId);
                return null;
            }
        } catch (SQLException e) {
            System.out.printf("Failed to get company with id [%d].%n", companyId);
            e.printStackTrace();
            return null;
        }
    }

    public boolean createTable() {
        String sql = """
                  CREATE TABLE IF NOT EXISTS company (
                  id INTEGER PRIMARY KEY AUTO_INCREMENT,
                  name VARCHAR UNIQUE NOT NULL)""";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
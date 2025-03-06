package carsharing;

import java.sql.*;

import carsharing.commands.Menu;
import carsharing.dao.ConnectionManager;
import carsharing.services.CarService;
import carsharing.services.CompanyService;
import carsharing.services.CustomerService;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String databaseFileName = getArgsParam(args, "databaseFileName", "carsharing");

        ConnectionManager.initConnection("jdbc:h2:./src/carsharing/db/%s".formatted(databaseFileName));

        CompanyService companyService = new CompanyService();
        companyService.initTable();

        CarService carService = new CarService();
        carService.initTable();

        CustomerService customerService = new CustomerService();
        customerService.initTable();

        Menu menu = new Menu(companyService, carService, customerService);
        menu.start();
    }

    private static String getArgsParam(String[] args, String param, String defaultValue) {
        for (int i = 0; i < args.length; i++) {
            if (param.equals(args[i]) && i + 1 < args.length) {
                return args[i + 1];
            }
        }
        return defaultValue;
    }
}
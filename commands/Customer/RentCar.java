package carsharing.commands.Customer;

import carsharing.Utils;
import carsharing.commands.MainMenu;
import carsharing.commands.Manager.CompanyMenu;
import carsharing.commands.Menu;
import carsharing.commands.MenuAction;
import carsharing.entities.Car;
import carsharing.entities.Company;
import carsharing.entities.Customer;

import java.util.List;

public class RentCar implements MenuAction {
    private final Menu menu;
    private boolean emptyList = true;

    public RentCar(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        Company selectedCompany = menu.getSelectedCompany();
        List<Car> cars = menu.getCarService().getNotRantedCarsByCompanyId(selectedCompany.getId());
        if (cars.isEmpty()) {
            emptyList = true;
            System.out.println("The car list is empty!");
        } else {
            emptyList = false;
            System.out.println("Choose a car:");
            for (int i = 1; i <= cars.size(); i++) {
                Car car = cars.get(i - 1);
                System.out.println(i + ". " + car.getName());
            }
        }
    }

    @Override
    public void execute() {
        int input = 0;
        if(!emptyList) {
            input = Utils.getIntInput();
        }
        if (input == 0) {
            menu.setSelectedCompany(null);
            menu.setCurrentMenu(new ChooseCompany(menu));
        } else {
            Company selectedCompany = menu.getSelectedCompany();
            List<Car> cars = menu.getCarService().getNotRantedCarsByCompanyId(selectedCompany.getId());
            System.out.println(input - 1);
            System.out.println(cars.size());
            Car selectedCar = cars.get(input - 1);
            Customer customer = menu.getSelectedCustomer();
            customer.setRentedCarId(selectedCar.getId());
            menu.getCustomerService().setRentedCarId(customer.getId(), selectedCar.getId());
            System.out.println("You rented '" + selectedCar.getName() +"'");
            menu.setCurrentMenu(new CustomerMenu(menu));
        }
    }
}
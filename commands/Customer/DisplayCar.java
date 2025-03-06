package carsharing.commands.Customer;

import carsharing.Utils;
import carsharing.commands.MainMenu;
import carsharing.commands.Menu;
import carsharing.commands.MenuAction;
import carsharing.entities.Customer;
import carsharing.entities.Company;
import carsharing.entities.Car;


public class DisplayCar implements MenuAction {
    private final Menu menu;

    public DisplayCar(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        Customer customer = menu.getSelectedCustomer();
        if(customer.getRentedCarId() > 0){
            int rentedCarId = customer.getRentedCarId();
            Car rentedCar = menu.getCarService().getCarById(rentedCarId);
            int companyId = rentedCar.getCompanyId();
            Company companyCar = menu.getCompanyService().getCompanyById(companyId);
            System.out.println("Your rented car:");
            System.out.println(rentedCar.getName());
            System.out.println("Company:");
            System.out.println(companyCar.getName());
        } else {
            System.out.println("You didn't rent a car!");
        }
    }

    @Override
    public void execute() {
        menu.setCurrentMenu(new CustomerMenu(menu));
    }
}
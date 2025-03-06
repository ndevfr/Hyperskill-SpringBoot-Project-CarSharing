package carsharing.commands.Customer;

import carsharing.Utils;
import carsharing.commands.MainMenu;
import carsharing.commands.Menu;
import carsharing.commands.MenuAction;
import carsharing.entities.Customer;

public class ReturnCar implements MenuAction {
    private final Menu menu;

    public ReturnCar(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        Customer customer = menu.getSelectedCustomer();
        if(customer.getRentedCarId() > 0){
            customer.setRentedCarId(null);
            menu.getCustomerService().setRentedCarId(customer.getId(), null);
            System.out.println("You've returned a rented car!");
        } else {
            System.out.println("You didn't rent a car!");
        }
    }

    @Override
    public void execute() {
        menu.setCurrentMenu(new CustomerMenu(menu));
    }
}
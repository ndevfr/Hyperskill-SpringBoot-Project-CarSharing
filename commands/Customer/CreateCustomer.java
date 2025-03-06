package carsharing.commands.Customer;

import carsharing.Utils;
import carsharing.commands.MainMenu;
import carsharing.commands.Menu;
import carsharing.commands.MenuAction;

public class CreateCustomer implements MenuAction {
    private final Menu menu;

    public CreateCustomer(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        System.out.println("Enter the customer name:");
    }

    @Override
    public void execute() {
        String customerName = Utils.getStringInput();
        menu.getCustomerService().addCustomer(customerName);
        System.out.println("The customer was added!");
        menu.setCurrentMenu(new MainMenu(menu));
    }
}
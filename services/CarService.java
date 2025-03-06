package carsharing.services;

import carsharing.dao.CarDao;
import carsharing.entities.Car;

import java.util.List;

public class CarService {
    public void initTable() {
        CarDao.get().createTable();
    }

    public List<Car> getAllCars() {
        return CarDao.get().getAll();
    }

    public Car getCarById(Integer carId) {
        return CarDao.get().getById(carId);
    }

    public List<Car> getCarsByCompanyId(Integer companyId) {
        return CarDao.get().getByCompanyId(companyId);
    }

    public List<Car> getNotRantedCarsByCompanyId(Integer companyId) {
        return CarDao.get().getNotRantedByCompanyId(companyId);
    }

    public void addCar(int companyId, String name) {
        CarDao.get().addCar(companyId, name);
    }

}
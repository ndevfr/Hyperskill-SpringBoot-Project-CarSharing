package carsharing.services;
import java.util.List;
import carsharing.dao.CompanyDao;
import carsharing.entities.Company;

public class CompanyService {
    public void initTable() {
        CompanyDao.get().createTable();
    }

    public List<Company> getAllCompanies() {
        return CompanyDao.get().getAll();
    }

    public Company getCompanyById(int id) {
        return CompanyDao.get().getById(id);
    }

    public void addCompany(String companyName) {
        CompanyDao.get().addCompany(companyName);
    }
}
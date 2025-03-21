package ra.business.designImpl;

import ra.business.dao.EmployeeDao;
import ra.business.design.EmployeeDesign;
import ra.business.entity.Employee;
import java.util.List;

/**
 * Triển khai giao diện xử lý logic nghiệp vụ cho đối tượng Employee.
 */
public class EmployeeDesignImpl implements EmployeeDesign {

    private final EmployeeDao employeeDao;

    public EmployeeDesignImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        employeeDao.deleteById(employeeId);
    }

    @Override
    public List<Employee> searchEmployeesByName(String name) {
        return employeeDao.findByName(name);
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        return employeeDao.findByDepartmentId(departmentId);
    }

    @Override
    public List<Employee> getTop5HighestPaidEmployees() {
        return employeeDao.findTop5HighestSalaries();
    }
}

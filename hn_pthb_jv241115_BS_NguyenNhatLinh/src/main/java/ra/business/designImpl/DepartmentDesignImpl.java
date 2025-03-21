package ra.business.designImpl;

import ra.business.dao.DepartmentDao;
import ra.business.design.DepartmentDesign;
import ra.business.entity.Department;

import java.util.List;

/**
 * Triển khai logic nghiệp vụ cho giao diện DepartmentDesign.
 */
public class DepartmentDesignImpl implements DepartmentDesign {

    private final DepartmentDao departmentDao;

    public DepartmentDesignImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.findAll();
    }

    @Override
    public void addDepartment(Department department) {
        departmentDao.save(department);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentDao.save(department);
    }

    @Override
    public void deleteDepartmentById(int departmentId) {
        departmentDao.deleteById(departmentId);
    }

    @Override
    public List<Department> searchDepartmentsByName(String name) {
        return departmentDao.findByName(name);
    }

    @Override
    public Department getDepartmentWithMostEmployees() {
        return departmentDao.findDepartmentWithMostEmployees();
    }
}



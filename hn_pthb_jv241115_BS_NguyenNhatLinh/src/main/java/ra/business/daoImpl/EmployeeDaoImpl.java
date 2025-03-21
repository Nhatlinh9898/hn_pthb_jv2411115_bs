package ra.business.daoImpl;




import ra.business.dao.EmployeeDao;
import ra.business.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Triển khai cụ thể cho giao diện EmployeeDao.
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private Connection connection;

    public EmployeeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Employee";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date").toLocalDate(),
                        rs.getInt("department_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee findById(Integer id) {
        try {
            String sql = "SELECT * FROM Employee WHERE employee_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date").toLocalDate(),
                        rs.getInt("department_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Employee employee) {
        try {
            String sql = employee.getEmployeeId() > 0
                    ? "UPDATE Employee SET employee_name = ?, position = ?, salary = ?, hire_date = ?, department_id = ? WHERE employee_id = ?"
                    : "INSERT INTO Employee (employee_name, position, salary, hire_date, department_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getEmployeeName());
            ps.setString(2, employee.getPosition());
            ps.setDouble(3, employee.getSalary());
            ps.setDate(4, Date.valueOf(employee.getHireDate()));
            ps.setInt(5, employee.getDepartmentId());
            if (employee.getEmployeeId() > 0) {
                ps.setInt(6, employee.getEmployeeId());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            String sql = "DELETE FROM Employee WHERE employee_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> findByName(String name) {
        List<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Employee WHERE employee_name LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date").toLocalDate(),
                        rs.getInt("department_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId) {
        List<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Employee WHERE department_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, departmentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date").toLocalDate(),
                        rs.getInt("department_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<Employee> findTop5HighestSalaries() {
        List<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Employee ORDER BY salary DESC LIMIT 5";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date").toLocalDate(),
                        rs.getInt("department_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}





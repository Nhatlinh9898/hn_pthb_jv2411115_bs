package ra.business.daoImpl;

import ra.business.dao.DepartmentDao;
import ra.business.entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Triển khai cụ thể cho giao diện DepartmentDao.
 */
public class DepartmentDaoImpl implements DepartmentDao {
    private Connection connection;

    // Constructor with Connection parameter
    public DepartmentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Departments";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                departments.add(new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getBoolean("department_status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public Department findById(Integer id) {
        try {
            String sql = "SELECT * FROM Departments WHERE department_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getBoolean("department_status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Department department) {
        try {
            String sql = department.getDepartmentId() > 0
                    ? "UPDATE Departments SET department_name = ?, department_status = ? WHERE department_id = ?"
                    : "INSERT INTO Departments (department_name, department_status) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, department.getDepartmentName());
            ps.setBoolean(2, department.isDepartmentStatus());
            if (department.getDepartmentId() > 0) {
                ps.setInt(3, department.getDepartmentId());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            String sql = "DELETE FROM Departments WHERE department_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Department> findByName(String name) {
        List<Department> departments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Departments WHERE department_name LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                departments.add(new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getBoolean("department_status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public Department findDepartmentWithMostEmployees() {
        try {
            String sql = "SELECT d.department_id, d.department_name, d.department_status, COUNT(e.employee_id) AS total_employees " +
                    "FROM Departments d " +
                    "JOIN Employee e ON d.department_id = e.department_id " +
                    "GROUP BY d.department_id, d.department_name, d.department_status " +
                    "ORDER BY total_employees DESC LIMIT 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getBoolean("department_status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Department mapResultSetToDepartment(ResultSet rs) throws SQLException {
        return new Department(
                rs.getInt("department_id"),
                rs.getString("department_name"),
                "Active".equalsIgnoreCase(rs.getString("department_status")) // Map "Active" to true
        );
    }

}



package ra.business.design;

import ra.business.entity.Employee;

import java.util.List;

/**
 * Giao diện xử lý logic nghiệp vụ cho đối tượng Employee.
 */
public interface EmployeeDesign {

    /**
     * Lấy danh sách tất cả nhân viên.
     *
     * @return danh sách nhân viên
     */
    List<Employee> getAllEmployees();

    /**
     * Thêm một nhân viên mới.
     *
     * @param employee thông tin nhân viên mới
     */
    void addEmployee(Employee employee);

    /**
     * Cập nhật thông tin nhân viên.
     *
     * @param employee thông tin nhân viên cần cập nhật
     */
    void updateEmployee(Employee employee);

    /**
     * Xóa một nhân viên theo ID.
     *
     * @param employeeId ID nhân viên cần xóa
     */
    void deleteEmployeeById(int employeeId);

    /**
     * Tìm nhân viên theo tên.
     *
     * @param name tên nhân viên cần tìm
     * @return danh sách nhân viên phù hợp
     */
    List<Employee> searchEmployeesByName(String name);

    /**
     * Lấy danh sách nhân viên theo ID phòng ban.
     *
     * @param departmentId ID phòng ban
     * @return danh sách nhân viên thuộc phòng ban
     */
    List<Employee> getEmployeesByDepartmentId(int departmentId);

    /**
     * Lấy top 5 nhân viên có lương cao nhất.
     *
     * @return danh sách top 5 nhân viên
     */
    List<Employee> getTop5HighestPaidEmployees();
}

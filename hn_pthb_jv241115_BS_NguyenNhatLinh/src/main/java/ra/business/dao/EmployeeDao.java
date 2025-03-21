package ra.business.dao;

import ra.business.entity.Employee;
import java.util.List;

/**
 * Giao diện DAO cụ thể cho bảng Employee.
 * Kế thừa từ giao diện tổng quát IGenericDao.
 */
public interface EmployeeDao extends IGenericDao<Employee, Integer> {

    /**
     * Tìm kiếm nhân viên theo tên.
     *
     * @param name tên nhân viên
     * @return danh sách nhân viên phù hợp
     */
    List<Employee> findByName(String name);

    /**
     * Hiển thị danh sách nhân viên theo phòng ban.
     *
     * @param departmentId mã phòng ban
     * @return danh sách nhân viên thuộc phòng ban
     */
    List<Employee> findByDepartmentId(Integer departmentId);

    /**
     * Lấy danh sách top 5 nhân viên có lương cao nhất.
     *
     * @return danh sách top 5 nhân viên có lương cao nhất
     */
    List<Employee> findTop5HighestSalaries();
}

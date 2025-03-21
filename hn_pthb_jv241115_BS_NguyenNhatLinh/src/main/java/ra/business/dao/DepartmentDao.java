package ra.business.dao;



import ra.business.entity.Department;

import java.util.List;

/**
 * DAO cụ thể cho bảng Departments.
 * Kế thừa từ giao diện tổng quát IGenericDao.
 */
public interface DepartmentDao extends IGenericDao<Department, Integer> {

    /**
     * Tìm kiếm phòng ban theo tên.
     *
     * @param name tên của phòng ban
     * @return danh sách phòng ban phù hợp
     */
    List<Department> findByName(String name);

    /**
     * Tìm phòng ban có nhiều nhân viên nhất.
     *
     * @return phòng ban có nhiều nhân viên nhất
     */
    Department findDepartmentWithMostEmployees();
}

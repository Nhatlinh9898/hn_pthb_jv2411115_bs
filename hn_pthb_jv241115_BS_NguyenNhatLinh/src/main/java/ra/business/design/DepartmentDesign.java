package ra.business.design;
import ra.business.dao.*;
import ra.business.entity.Department;

import java.util.List;



    /**
     * Giao diện xử lý logic nghiệp vụ cho đối tượng Department.
     */
    public interface DepartmentDesign {

        /**
         * Lấy danh sách tất cả phòng ban.
         *
         * @return danh sách phòng ban
         */
        List<Department> getAllDepartments();

        /**
         * Thêm một phòng ban mới.
         *
         * @param department thông tin phòng ban mới
         */
        void addDepartment(Department department);

        /**
         * Cập nhật thông tin phòng ban.
         *
         * @param department thông tin phòng ban cần cập nhật
         */
        void updateDepartment(Department department);

        /**
         * Xóa một phòng ban theo ID.
         *
         * @param departmentId ID phòng ban cần xóa
         */
        void deleteDepartmentById(int departmentId);

        /**
         * Tìm kiếm phòng ban theo tên.
         *
         * @param name tên phòng ban cần tìm
         * @return danh sách phòng ban phù hợp
         */
        List<Department> searchDepartmentsByName(String name);

        /**
         * Lấy phòng ban có nhiều nhân viên nhất.
         *
         * @return phòng ban có nhiều nhân viên nhất
         */
        Department getDepartmentWithMostEmployees();
    }



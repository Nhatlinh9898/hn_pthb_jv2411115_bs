package ra.presentation;

import ra.business.config.ConnectDB;
import ra.business.dao.DepartmentDao;
import ra.business.dao.EmployeeDao;
import ra.business.daoImpl.DepartmentDaoImpl;
import ra.business.daoImpl.EmployeeDaoImpl;
import ra.business.design.DepartmentDesign;
import ra.business.design.EmployeeDesign;
import ra.business.designImpl.DepartmentDesignImpl;
import ra.business.designImpl.EmployeeDesignImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = ConnectDB.openConnection();
            if (connection == null) {
                System.err.println("Không thể kết nối đến cơ sở dữ liệu. Vui lòng kiểm tra cài đặt!");
                return;
            }

            // Khởi tạo các DAO và tầng nghiệp vụ (Service)
            DepartmentDao departmentDao = new DepartmentDaoImpl(connection); // Use concrete implementation
            EmployeeDao employeeDao = new EmployeeDaoImpl(connection);       // Use concrete implementation

            DepartmentDesign departmentDesign = new DepartmentDesignImpl(departmentDao); // Use concrete implementation
            EmployeeDesign employeeDesign = new EmployeeDesignImpl(employeeDao);         // Use concrete implementation

            // Chạy menu chính của ứng dụng
            runMainMenu(departmentDesign, employeeDesign);
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            ConnectDB.closeConnection(connection);
        }
    }

    private static void runMainMenu(DepartmentDesign departmentService, EmployeeDesign employeeService) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n******************HR-MANAGEMENT******************");
            System.out.println("1. Quản lý phòng ban");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    try {
                        DepartmentMenu.display(scanner, departmentService);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    try {
                        EmployeeMenu.display(scanner, employeeService);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    System.out.println("Thoát chương trình. Hẹn gặp lại!");
                    return; // Exit the program
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}





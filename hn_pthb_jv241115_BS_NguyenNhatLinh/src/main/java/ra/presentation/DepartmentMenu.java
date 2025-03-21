
 

package ra.presentation;

import ra.business.design.DepartmentDesign;
import ra.business.entity.Department;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentMenu {

    public static void display(Scanner scanner, DepartmentDesign departmentService) throws SQLException {
        while (true) {
            System.out.println("\n**********************DEPARTMENT-MENU********************");
            System.out.println("1. Danh sách phòng ban");
            System.out.println("2. Thêm mới phòng ban");
            System.out.println("3. Cập nhật thông tin phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("5. Hiển thị phòng ban có nhiều nhân viên nhất");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllDepartments(departmentService);
                    break;
                case 2:
                    addNewDepartment(scanner, departmentService);
                    break;
                case 3:
                    updateDepartment(scanner, departmentService);
                    break;
                case 4:
                    deleteDepartment(scanner, departmentService);
                    break;
                case 5:
                    displayTopDepartment(departmentService);
                    break;
                case 6:
                    System.out.println("Thoát khỏi menu phòng ban.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void listAllDepartments(DepartmentDesign departmentService) {
        try {
            List<Department> departments = departmentService.getAllDepartments();
            if (departments.isEmpty()) {
                System.out.println("Không có phòng ban nào trong hệ thống.");
            } else {
                // Prepare table headers
                List<String> headers = List.of("Department ID", "Department Name", "Status");
                List<List<String>> data = new ArrayList<>();

                // Map departments to table rows
                for (Department department : departments) {
                    data.add(List.of(
                            String.valueOf(department.getDepartmentId()),
                            department.getDepartmentName(),
                            department.isDepartmentStatus() ? "Hoạt động" : "Ngừng hoạt động"
                    ));
                }

                // Display the table
                displayTable(headers, data, 20);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách phòng ban: " + e.getMessage());
        }
    }

    private static void addNewDepartment(Scanner scanner, DepartmentDesign departmentService) {
        try {
            System.out.print("Nhập tên phòng ban: ");
            scanner.nextLine(); // Clear newline
            String name = scanner.nextLine();
            System.out.print("Nhập trạng thái phòng ban (true/false): ");
            boolean status = scanner.nextBoolean();
            Department department = new Department(0, name, status);
            departmentService.addDepartment(department);
            System.out.println("Thêm mới phòng ban thành công!");
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm mới phòng ban: " + e.getMessage());
        }
    }

    private static void updateDepartment(Scanner scanner, DepartmentDesign departmentService) {
        try {
            System.out.print("Nhập ID phòng ban cần cập nhật: ");
            int departmentId = scanner.nextInt();
            scanner.nextLine(); // Clear newline
            System.out.print("Nhập tên mới của phòng ban: ");
            String name = scanner.nextLine();
            System.out.print("Nhập trạng thái mới (true/false): ");
            boolean status = scanner.nextBoolean();
            Department department = new Department(departmentId, name, status);
            departmentService.updateDepartment(department);
            System.out.println("Cập nhật thông tin phòng ban thành công!");
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật thông tin phòng ban: " + e.getMessage());
        }
    }

    private static void deleteDepartment(Scanner scanner, DepartmentDesign departmentService) {
        try {
            System.out.print("Nhập ID phòng ban cần xóa: ");
            int departmentId = scanner.nextInt();
            departmentService.deleteDepartmentById(departmentId);
            System.out.println("Xóa phòng ban thành công!");
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa phòng ban: " + e.getMessage());
        }
    }

    private static void displayTopDepartment(DepartmentDesign departmentService) {
        try {
            Department topDepartment = departmentService.getDepartmentWithMostEmployees();
            if (topDepartment != null) {
                // Prepare table headers
                List<String> headers = List.of("Department ID", "Department Name", "Status");
                List<List<String>> data = List.of(List.of(
                        String.valueOf(topDepartment.getDepartmentId()),
                        topDepartment.getDepartmentName(),
                        topDepartment.isDepartmentStatus() ? "Hoạt động" : "Ngừng hoạt động"
                ));

                // Display the table
                displayTable(headers, data, 20);
            } else {
                System.out.println("Không tìm thấy phòng ban nào có nhân viên.");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị phòng ban có nhiều nhân viên nhất: " + e.getMessage());
        }
    }

    private static void displayTable(List<String> headers, List<List<String>> data, int maxColumnLength) {
        System.out.println("=".repeat(80));
        for (String header : headers) {
            System.out.printf("%-" + maxColumnLength + "s", header);
        }
        System.out.println();
        System.out.println("=".repeat(80));
        for (List<String> row : data) {
            for (String value : row) {
                System.out.printf("%-" + maxColumnLength + "s", value);
            }
            System.out.println();
        }
        System.out.println("=".repeat(80));
    }
}




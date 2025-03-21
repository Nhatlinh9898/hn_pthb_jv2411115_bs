

package ra.presentation;

import ra.business.design.EmployeeDesign;
import ra.business.entity.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeMenu {
    public static void display(Scanner scanner, EmployeeDesign employeeService) throws SQLException, IOException {
        while (true) {
            System.out.println("\n************************EMPLOYEE-MENU********************");
            System.out.println("1. Danh sách nhân viên");
            System.out.println("2. Thêm mới nhân viên");
            System.out.println("3. Cập nhật thông tin nhân viên");
            System.out.println("4. Xóa nhân viên");
            System.out.println("5. Hiển thị danh sách nhân viên theo phòng ban");
            System.out.println("6. Tìm kiếm nhân viên theo tên");
            System.out.println("7. Hiển thị top 5 nhân viên có lương cao nhất");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllEmployees(employeeService);
                    break;
                case 2:
                    addNewEmployee(scanner, employeeService);
                    break;
                case 3:
                    updateEmployee(scanner, employeeService);
                    break;
                case 4:
                    deleteEmployee(scanner, employeeService);
                    break;
                case 5:
                    listEmployeesByDepartment(scanner, employeeService);
                    break;
                case 6:
                    searchEmployeesByName(scanner, employeeService);
                    break;
                case 7:
                    displayTop5HighestPaidEmployees(employeeService);
                    break;
                case 8:
                    System.out.println("Thoát khỏi menu nhân viên.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void listAllEmployees(EmployeeDesign employeeService) {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            if (employees.isEmpty()) {
                System.out.println("Không có nhân viên nào trong hệ thống.");
            } else {
                // Display employees in table format
                List<String> headers = List.of("Employee ID", "Name", "Position", "Salary", "Department ID");
                List<List<String>> data = new ArrayList<>();
                for (Employee employee : employees) {
                    data.add(List.of(
                            String.valueOf(employee.getEmployeeId()),
                            employee.getEmployeeName(),
                            employee.getPosition(),
                            String.format("%.2f", employee.getSalary()),
                            String.valueOf(employee.getDepartmentId())
                    ));
                }
                displayTable(headers, data, 25); // Max column length is 25
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách nhân viên: " + e.getMessage());
        }
    }

    private static void addNewEmployee(Scanner scanner, EmployeeDesign employeeService) {
        try {
            System.out.print("Nhập tên nhân viên: ");
            scanner.nextLine(); // Clear newline
            String name = scanner.nextLine();
            System.out.print("Nhập vị trí công việc: ");
            String position = scanner.nextLine();
            System.out.print("Nhập mức lương: ");
            double salary = scanner.nextDouble();
            System.out.print("Nhập mã phòng ban: ");
            int departmentId = scanner.nextInt();

            Employee employee = new Employee(0, name, position, salary, LocalDate.now(), departmentId);
            employeeService.addEmployee(employee);
            System.out.println("Thêm mới nhân viên thành công!");
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm mới nhân viên: " + e.getMessage());
        }
    }

    private static void updateEmployee(Scanner scanner, EmployeeDesign employeeService) {
        try {
            System.out.print("Nhập ID nhân viên cần cập nhật: ");
            int employeeId = scanner.nextInt();
            scanner.nextLine(); // Clear newline
            System.out.print("Nhập tên mới của nhân viên: ");
            String newName = scanner.nextLine();
            System.out.print("Nhập vị trí mới của nhân viên: ");
            String newPosition = scanner.nextLine();
            System.out.print("Nhập mức lương mới: ");
            double newSalary = scanner.nextDouble();
            System.out.print("Nhập mã phòng ban mới: ");
            int newDepartmentId = scanner.nextInt();

            Employee updatedEmployee = new Employee(employeeId, newName, newPosition, newSalary, LocalDate.now(), newDepartmentId);
            employeeService.updateEmployee(updatedEmployee);
            System.out.println("Cập nhật thông tin nhân viên thành công!");
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật thông tin nhân viên: " + e.getMessage());
        }
    }

    private static void deleteEmployee(Scanner scanner, EmployeeDesign employeeService) {
        try {
            System.out.print("Nhập ID nhân viên cần xóa: ");
            int employeeId = scanner.nextInt();
            employeeService.deleteEmployeeById(employeeId);
            System.out.println("Xóa nhân viên thành công!");
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa nhân viên: " + e.getMessage());
        }
    }

    private static void listEmployeesByDepartment(Scanner scanner, EmployeeDesign employeeService) {
        try {
            System.out.print("Nhập mã phòng ban: ");
            int departmentId = scanner.nextInt();
            List<Employee> employees = employeeService.getEmployeesByDepartmentId(departmentId);
            if (employees.isEmpty()) {
                System.out.println("Không có nhân viên nào thuộc phòng ban này.");
            } else {
                // Display employees in table format
                List<String> headers = List.of("Employee ID", "Name", "Position", "Salary", "Department ID");
                List<List<String>> data = new ArrayList<>();
                for (Employee employee : employees) {
                    data.add(List.of(
                            String.valueOf(employee.getEmployeeId()),
                            employee.getEmployeeName(),
                            employee.getPosition(),
                            String.format("%.2f", employee.getSalary()),
                            String.valueOf(employee.getDepartmentId())
                    ));
                }
                displayTable(headers, data, 25); // Max column length is 25
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị danh sách nhân viên theo phòng ban: " + e.getMessage());
        }
    }

    private static void searchEmployeesByName(Scanner scanner, EmployeeDesign employeeService) {
        try {
            System.out.print("Nhập tên nhân viên cần tìm: ");
            scanner.nextLine(); // Clear newline
            String name = scanner.nextLine();
            List<Employee> employees = employeeService.searchEmployeesByName(name);
            if (employees.isEmpty()) {
                System.out.println("Không tìm thấy nhân viên nào với tên: " + name);
            } else {
                // Display employees in table format
                List<String> headers = List.of("Employee ID", "Name", "Position", "Salary", "Department ID");
                List<List<String>> data = new ArrayList<>();
                for (Employee employee : employees) {
                    data.add(List.of(
                            String.valueOf(employee.getEmployeeId()),
                            employee.getEmployeeName(),
                            employee.getPosition(),
                            String.format("%.2f", employee.getSalary()),
                            String.valueOf(employee.getDepartmentId())
                    ));
                }
                displayTable(headers, data, 25); // Max column length is 25
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm kiếm nhân viên theo tên: " + e.getMessage());
        }
    }

    private static void displayTop5HighestPaidEmployees(EmployeeDesign employeeService) {
        try {
            List<Employee> topEmployees = employeeService.getTop5HighestPaidEmployees();
            if (topEmployees.isEmpty()) {
                System.out.println("Không có nhân viên nào trong hệ thống.");
            } else {
                // Display employees in table format
                List<String> headers = List.of("Employee ID", "Name", "Position", "Salary", "Department ID");
                List<List<String>> data = new ArrayList<>();
                for (Employee employee : topEmployees) {
                    data.add(List.of(
                            String.valueOf(employee.getEmployeeId()),
                            employee.getEmployeeName(),
                            employee.getPosition(),
                            String.format("%.2f", employee.getSalary()),
                            String.valueOf(employee.getDepartmentId())
                    ));
                }
                displayTable(headers, data, 25); // Max column length is 25
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị top 5 nhân viên có lương cao nhất: " + e.getMessage());
        }
    }

    // Helper method to display tables
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


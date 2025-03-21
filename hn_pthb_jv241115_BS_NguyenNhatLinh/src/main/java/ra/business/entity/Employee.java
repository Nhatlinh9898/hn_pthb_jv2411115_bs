package ra.business.entity;



import java.time.LocalDate;

public class Employee {
    private int employeeId;         // Mã nhân viên
    private String employeeName;    // Tên nhân viên
    private String position;        // Vị trí công việc
    private double salary;          // Mức lương
    private LocalDate hireDate;     // Ngày tuyển dụng
    private int departmentId;       // Mã phòng ban (khóa ngoại)

    // Constructor
    public Employee(int employeeId, String employeeName, String position, double salary, LocalDate hireDate, int departmentId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
        this.departmentId = departmentId;
    }

    // Default Constructor
    public Employee() {}

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary must be greater than 0.");
        }
        this.salary = salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", departmentId=" + departmentId +
                '}';
    }
}


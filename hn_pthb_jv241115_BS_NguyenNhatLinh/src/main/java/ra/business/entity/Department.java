package ra.business.entity;

//package ra.business.entity;
//
//
//
//public class Department {
//    private int departmentId;           // Mã phòng ban
//    private String departmentName;      // Tên phòng ban
//    private boolean departmentStatus;   // Trạng thái phòng ban (1: hoạt động, 0: ngừng hoạt động)
//
//    // Constructor
//    public Department(int departmentId, String departmentName, boolean departmentStatus) {
//        this.departmentId = departmentId;
//        this.departmentName = departmentName;
//        this.departmentStatus = departmentStatus;
//    }
//
//    // Default Constructor
//    public Department() {}
//
//    // Getters and Setters
//    public int getDepartmentId() {
//        return departmentId;
//    }
//
//    public void setDepartmentId(int departmentId) {
//        this.departmentId = departmentId;
//    }
//
//    public String getDepartmentName() {
//        return departmentName;
//    }
//
//    public void setDepartmentName(String departmentName) {
//        this.departmentName = departmentName;
//    }
//
//    public boolean isDepartmentStatus() {
//        return departmentStatus;
//    }
//
//    public void setDepartmentStatus(boolean departmentStatus) {
//        this.departmentStatus = departmentStatus;
//    }
//
//    @Override
//    public String toString() {
//        return "Department{" +
//                "departmentId=" + departmentId +
//                ", departmentName='" + departmentName + '\'' +
//                ", departmentStatus=" + departmentStatus +
//                '}';
//    }
//}
public class Department {
    private int departmentId;
    private String departmentName;
    private boolean departmentStatus; // Internally stored as a boolean

    public Department(int departmentId, String departmentName, boolean departmentStatus) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentStatus = departmentStatus;
    }

    // Getters and setters
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public boolean isDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(boolean departmentStatus) {
        this.departmentStatus = departmentStatus;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentStatus=" + departmentStatus +
                '}';
    }
}

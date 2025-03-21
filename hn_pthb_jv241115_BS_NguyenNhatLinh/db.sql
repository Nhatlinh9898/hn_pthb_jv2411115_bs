CREATE DATABASE  CSDLquanlynhansu;
USE CSDLquanlynhansu;






-- Tạo bảng Departments
CREATE TABLE Departments (
                             department_id INT AUTO_INCREMENT PRIMARY KEY, -- Mã phòng ban, khóa chính
                             department_name VARCHAR(50) NOT NULL UNIQUE,  -- Tên phòng ban, không null và duy nhất
                             department_status BIT DEFAULT 1              -- Trạng thái phòng ban, mặc định là 1 (hoạt động)
);

-- Tạo bảng Employee
CREATE TABLE Employee (
                          employee_id INT AUTO_INCREMENT PRIMARY KEY,   -- Mã nhân viên, khóa chính
                          employee_name VARCHAR(50) NOT NULL UNIQUE,    -- Tên nhân viên, không null và duy nhất
                          position VARCHAR(50) NOT NULL,                -- Vị trí công việc, không null
                          salary DOUBLE NOT NULL CHECK (salary > 0),    -- Mức lương, không null và phải lớn hơn 0
                          hire_date DATE NOT NULL,                      -- Ngày tuyển dụng, không null
                          department_id INT NOT NULL,                   -- Mã phòng ban, không null, khóa ngoại
                          CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES Departments(department_id)
                              ON DELETE CASCADE                             -- Khi xóa phòng ban, nhân viên thuộc phòng ban đó cũng sẽ bị xóa
);

ALTER TABLE Departments MODIFY COLUMN department_status VARCHAR(10);

INSERT INTO Departments (department_id, department_name, department_status) VALUES
                                                                                (1, 'Human Resources', 'Active'),
                                                                                (2, 'IT', 'Active'),
                                                                                (3, 'Finance', 'Inactive'),
                                                                                (4, 'Marketing', 'Active'),
                                                                                (5, 'Operations', 'Active');
SELECT * FROM Departments WHERE department_id IN (1, 2, 3, 4, 5);


INSERT INTO Employee (employee_id, employee_name, position, salary, hire_date, department_id) VALUES
                                                                                                   (101, 'John Smith', 'Manager', 5000.00, '2022-01-15', 1),
                                                                                                   (102, 'Alice Johnson', 'Developer', 4500.50, '2021-05-22', 2),
                                                                                                   (103, 'Michael Brown', 'Financial Analyst', 4000.00, '2020-11-10', 3),
                                                                                                   (104, 'Sarah Davis', 'Marketing Lead', 4700.75, '2023-03-01', 4),
                                                                                                   (105, 'Daniel Lee', 'Operations Specialist', 4200.00, '2022-08-19', 5);

DESCRIBE Departments;
ALTER TABLE Departments MODIFY COLUMN department_status VARCHAR(10);

SELECT DISTINCT department_status FROM Departments;
UPDATE Departments SET department_status = 'Active' WHERE department_status = 1;
UPDATE Departments SET department_status = 'Inactive' WHERE department_status = 0;

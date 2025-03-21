package ra.presentation.test;

import ra.presentation.DepartmentMenu;
import ra.business.design.DepartmentDesign;
import ra.business.designImpl.DepartmentDesignImpl;
import ra.business.daoImpl.DepartmentDaoImpl;
import ra.business.config.ConnectDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConnectDB.openConnection();
            DepartmentDesign departmentService = new DepartmentDesignImpl(new DepartmentDaoImpl(connection));
            Scanner scanner = new Scanner(System.in);
            DepartmentMenu.display(scanner, departmentService);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
    }
}


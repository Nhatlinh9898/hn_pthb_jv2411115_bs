package ra.business.config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlexibleTableManager {

    private final String jdbcUrl;
    private final String username;
    private final String password;

    public FlexibleTableManager(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    /**
     * Connect to the database using JDBC.
     *
     * @return Connection object
     * @throws SQLException in case of connection issues
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    /**
     * Fetch data from the database based on a query.
     *
     * @param query SQL query to execute
     * @return List of rows, where each row is a list of column values
     */
    public List<List<String>> fetchData(String query) {
        List<List<String>> data = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i) != null ? rs.getString(i) : "NULL");
                }
                data.add(row);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }
        return data;
    }

    /**
     * Print a table with customizable column length limits.
     *
     * @param headers Column headers
     * @param data    Table data (rows)
     * @param maxColumnLength Maximum length of any column
     */
    public void displayTable(List<String> headers, List<List<String>> data, int maxColumnLength) {
        if (headers == null || headers.isEmpty() || data == null || data.isEmpty()) {
            System.out.println("No data or headers available to display.");
            return;
        }

        // Determine column width constraints
        int[] maxColumnWidths = new int[headers.size()];
        for (int i = 0; i < headers.size(); i++) {
            maxColumnWidths[i] = Math.min(headers.get(i).length(), maxColumnLength);
        }
        for (List<String> row : data) {
            for (int i = 0; i < row.size(); i++) {
                maxColumnWidths[i] = Math.min(Math.max(maxColumnWidths[i], row.get(i).length()), maxColumnLength);
            }
        }

        // Build the format string for rows
        StringBuilder formatBuilder = new StringBuilder();
        for (int width : maxColumnWidths) {
            formatBuilder.append("%-").append(width + 2).append("s");
        }
        formatBuilder.append("\n");
        String rowFormat = formatBuilder.toString();

        // Print the headers
        System.out.printf(rowFormat, headers.stream().map(h -> truncate(h, maxColumnLength)).toArray());
        System.out.println("-".repeat(rowFormat.length()));

        // Print the data rows
        for (List<String> row : data) {
            System.out.printf(rowFormat, row.stream().map(v -> truncate(v, maxColumnLength)).toArray());
        }
    }

    /**
     * Truncate a string to a maximum length and add "..." if truncated.
     *
     * @param value Input string
     * @param maxLength Maximum allowed length
     * @return Truncated string
     */
    private String truncate(String value, int maxLength) {
        if (value == null) {
            return "NULL";
        }
        return value.length() > maxLength ? value.substring(0, maxLength - 3) + "..." : value;
    }

    public static void main(String[] args) {
        // Example usage for "Departments" table
        FlexibleTableManager tableManager = new FlexibleTableManager(
                "jdbc:mysql://localhost:3306/CSDLquanlynhansu", "root", "Nhat68linh68#");

        String query = "SELECT department_id, department_name, department_status FROM Departments";
        List<List<String>> data = tableManager.fetchData(query);
        List<String> headers = List.of("Department ID", "Department Name", "Status");

        // Display the data in a formatted table
        tableManager.displayTable(headers, data, 20);
    }
}


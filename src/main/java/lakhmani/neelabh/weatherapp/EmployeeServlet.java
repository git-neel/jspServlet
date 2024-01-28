package lakhmani.neelabh.weatherapp;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet(name="employeeServlet", value="/employee")
public class EmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // JDBC connection parameters
        String url = "jdbc:mysql://localhost:3306/employee";
        String userName = "root";
        String password = "Mankii@24";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            try (Connection conn = DriverManager.getConnection(url, userName, password)) {
                // Construct the SQL query using a parameterized PreparedStatement
                String query = "INSERT INTO employee (first_name, last_name, age, salary, designation, address) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = conn.prepareStatement(query)) {
                    // Set the parameters for the query using user input
                    statement.setString(1, request.getParameter("firstName"));
                    statement.setString(2, request.getParameter("lastName"));
                    statement.setInt(3, Integer.parseInt(request.getParameter("age")));
                    statement.setDouble(4, Double.parseDouble(request.getParameter("salary")));
                    statement.setString(5, request.getParameter("designation"));
                    statement.setString(6, request.getParameter("address"));

                    // Execute the query
                    statement.executeUpdate();
                    String readQuery = "select * from employee";
                    ResultSet rs = statement.executeQuery(readQuery);
                    while(rs.next()){
                        System.out.println("firstName: "+rs.getString(1));
                        System.out.println("lastName: "+rs.getString(2));
                        System.out.println("age: "+rs.getInt(3));
                        System.out.println("salary: "+rs.getDouble(4));
                        System.out.println("designation: "+rs.getString(5));
                        System.out.println("address: "+rs.getString(6));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // JDBC connection parameters
        String url = "jdbc:mysql://localhost:3306/employee";
        String userName = "root";
        String password = "Mankii@24";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            try (Connection conn = DriverManager.getConnection(url, userName, password)) {
                // Construct the SQL query
                String query = "INSERT INTO employee (first_name, last_name, age, salary, designation, address) " +
                        "VALUES ('" + request.getParameter("firstName") + "', '" +
                        request.getParameter("lastName") + "', " +
                        Integer.parseInt(request.getParameter("age")) + ", " +
                        Double.parseDouble(request.getParameter("salary")) + ", '" +
                        request.getParameter("designation") + "', '" +
                        request.getParameter("address") + "')";

                // Create a statement
                try (Statement statement = conn.createStatement()) {
                    // Execute the query
                    statement.executeUpdate(query);


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}

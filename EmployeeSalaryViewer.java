package empsalary;

import java.sql.*;
import java.util.Scanner;

public class EmployeeSalaryViewer {

    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/company_db","root", "lalli@2007" );

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();
        PreparedStatement ps = con.prepareStatement(
                "SELECT emp_id, name, salary FROM employees WHERE emp_id = ?");
        ps.setInt(1, empId);  // set parameter

        ResultSet rs = ps.executeQuery();

        int count = 0; 
        while (rs.next()) {
            count++;
            System.out.println("================================");
            System.out.println("        EMPLOYEE DETAILS");
            System.out.println("================================");
            System.out.println("Employee ID : " + rs.getInt("emp_id"));
            System.out.println("Name        : " + rs.getString("name"));
            System.out.println("Salary      : $" + rs.getDouble("salary"));
            System.out.println("================================");
        }

        if (count == 0) {
            System.out.println("\n[!] No record found for Employee ID: " + empId);
        }

        con.close();
    }
}

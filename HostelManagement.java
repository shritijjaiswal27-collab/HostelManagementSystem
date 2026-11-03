
import java.sql.*;
import java.util.Scanner;

public class HostelManagement {

    static final String URL = "jdbc:mysql://localhost:3306/hostel_db";
    static final String USER = "root";
    static final String PASS = "Krish27sql";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            System.out.println("Database Connected Successfully!");

            while (true) {

                System.out.println("\n===== KIET HOSTEL MANAGEMENT =====");
                System.out.println("1. Insert Student Records");
                System.out.println("2. Search Students by Hostel");
                System.out.println("3. Update Hostel Fee");
                System.out.println("4. Search and Update Room");
                System.out.println("5. Display Total Fee and Delete Student");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        insertStudents(con);
                        break;

                    case 2:
                        searchStudents(con);
                        break;

                    case 3:
                        updateFee(con);
                        break;

                    case 4:
                        updateRoom(con);
                        break;

                    case 5:
                        totalFeeAndDelete(con);
                        break;

                    case 6:
                        con.close();
                        System.out.println("Thank You!");
                        return;

                    default:
                        System.out.println("Invalid Choice!");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // (a) Insert Student Records
    static void insertStudents(Connection con) throws SQLException {

        String sql = "INSERT INTO HostelStudent VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        for (int i = 1; i <= 3; i++) {

            System.out.println("\nEnter Student " + i + " Details:");

            System.out.print("Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Student Name: ");
            String name = sc.nextLine();

            System.out.print("Roll Number: ");
            String roll = sc.nextLine();

            System.out.print("Hostel Name: ");
            String hostel = sc.nextLine();

            System.out.print("Room Number: ");
            String room = sc.nextLine();

            System.out.print("Hostel Fee: ");
            double fee = sc.nextDouble();

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, roll);
            ps.setString(4, hostel);
            ps.setString(5, room);
            ps.setDouble(6, fee);

            try {
                ps.executeUpdate();
                System.out.println("Student Inserted Successfully!");
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Duplicate Student ID! Record not inserted.");
            }
        }
    }

    // (b) Conditional Retrieval and Sorting
    static void searchStudents(Connection con) throws SQLException {

        System.out.print("Enter Hostel Name: ");
        sc.nextLine();
        String hostel = sc.nextLine();

        String sql = "SELECT * FROM HostelStudent " +
                     "WHERE hostel_name = ? AND fee > 50000 " +
                     "ORDER BY fee DESC";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, hostel);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(
                rs.getInt("student_id") + " | " +
                rs.getString("student_name") + " | " +
                rs.getString("roll_number") + " | " +
                rs.getString("hostel_name") + " | " +
                rs.getString("room_number") + " | " +
                rs.getDouble("fee")
            );
        }
    }

    // (c) Update Hostel Fee
    static void updateFee(Connection con) throws SQLException {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        System.out.print("Enter New Hostel Fee: ");
        double fee = sc.nextDouble();

        String sql = "UPDATE HostelStudent SET fee = ? WHERE student_id = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, fee);
        ps.setInt(2, id);

        int rows = ps.executeUpdate();

        if (rows > 0)
            System.out.println("Hostel Fee Updated Successfully!");
        else
            System.out.println("Student Record Does Not Exist!");
    }

    // (d) Search and Update Room
    static void updateRoom(Connection con) throws SQLException {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        String select = "SELECT * FROM HostelStudent WHERE student_id = ?";

        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            System.out.println("Student Found!");
            System.out.println("Name: " + rs.getString("student_name"));
            System.out.println("Old Room: " + rs.getString("room_number"));

            System.out.print("Enter New Room Number: ");
            String room = sc.nextLine();

            String update = "UPDATE HostelStudent SET room_number = ? WHERE student_id = ?";

            PreparedStatement ps2 = con.prepareStatement(update);
            ps2.setString(1, room);
            ps2.setInt(2, id);

            ps2.executeUpdate();

            System.out.println("Room Updated Successfully!");

            String show = "SELECT * FROM HostelStudent WHERE student_id = ?";
            PreparedStatement ps3 = con.prepareStatement(show);
            ps3.setInt(1, id);

            ResultSet rs2 = ps3.executeQuery();

            if (rs2.next()) {
                System.out.println("Updated Student Details:");
                System.out.println("ID: " + rs2.getInt("student_id"));
                System.out.println("Name: " + rs2.getString("student_name"));
                System.out.println("Roll: " + rs2.getString("roll_number"));
                System.out.println("Hostel: " + rs2.getString("hostel_name"));
                System.out.println("Room: " + rs2.getString("room_number"));
                System.out.println("Fee: " + rs2.getDouble("fee"));
            }

        } else {
            System.out.println("Student Record Not Found!");
        }
    }

    // (e) Aggregate Query and Conditional Deletion
    static void totalFeeAndDelete(Connection con) throws SQLException {

        String sql = "SELECT SUM(fee) AS total FROM HostelStudent";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Total Hostel Fee Collected: " +
                               rs.getDouble("total"));
        }

        System.out.print("Enter Student ID to Delete: ");
        int id = sc.nextInt();

        String delete = "DELETE FROM HostelStudent " +
                        "WHERE student_id = ? AND fee > 0";

        PreparedStatement ps2 = con.prepareStatement(delete);
        ps2.setInt(1, id);

        int rows = ps2.executeUpdate();

        if (rows > 0)
            System.out.println("Student Deleted Successfully!");
        else
            System.out.println("Record Not Found or Fee Not Fully Paid!");
    }
}

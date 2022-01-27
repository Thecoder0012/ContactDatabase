import java.sql.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/IdeaProjects/TestDB/testjava.db");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS contacts " + " (name TEXT,phone INTEGER, email TEXT)");
            boolean runProgram = true;
            while (runProgram) {
                System.out.println("Enter:\n**************************************************************" +
                        "\n\t1 - to add a new person to the database\n\t2 - " +
                        "to update the phonenumber of a person in the database\n\t3 - " +
                        "to delete a person from the database\n\t4 - " +
                        "to view all the persons\n\t5 - to quit the program\n" +
                        "*******************" +
                        "*******************************************\nEnter a number:");
                switch (scanner.nextInt()) {

                    case 1:
                        System.out.println("Add a new person to the database");
                        scanner.nextLine();
                        System.out.println("Add name");
                        String name = scanner.nextLine();
                        int phone = scanner.nextInt();
                        String email = scanner.next();
                        statement.execute("INSERT INTO contacts (name, phone, email)" + "" + "VALUES('" + name + "'," + phone + ",'" + email + "')");
                        break;

                    case 2:
                        System.out.println("Type in a new number and a name after to update the number");
                        scanner.nextLine();
                        statement.execute("UPDATE contacts SET phone='" + scanner.nextInt() + "' WHERE name ='" + scanner.next() + "'");
                        break;

                    case 3:
                        System.out.println("Type in a name to delete that person from the database");
                        statement.execute("DELETE FROM contacts WHERE name='" + scanner.next() + "'");
                        break;

                    case 4:
                        statement.execute("SELECT * FROM contacts ");
                        ResultSet resultSet = statement.getResultSet();
                        while (resultSet.next()) {
                            System.out.println(
                                    resultSet.getString(1) + " " +
                                            resultSet.getInt(2) + " " +
                                            resultSet.getString(3) + "\n");
                        }
                        resultSet.close();
                        break;
                    case 5:
                        runProgram = false;

                    default:
                        if (runProgram) {
                            System.out.println("Enter a valid number");
                        }
                }
            }

        } catch (SQLException e) {
            System.out.println("Wrong " + e.getMessage());
        }
    }
}

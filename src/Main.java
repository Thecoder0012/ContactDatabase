import java.sql.*;
import java.util.Scanner;

public class Main {

    public static final String fileName = "contacts.db";
    public static final String filePath = "jdbc:sqlite:/IdeaProjects/ContactInfo/" + fileName;

    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection(filePath);
            Statement statement = connection.createStatement();

            boolean runProgram = true;
            while (runProgram) {
                menulist();

                switch (scanner.nextInt()) {

                    case 0:
                        createTable(scanner, statement);
                        break;
                    case 1:
                        addToTable(scanner, statement);
                        break;
                    case 2:
                        updateRecord(scanner, statement);
                        break;
                    case 3:
                        deleteRecord(scanner, statement);
                        break;
                    case 4:
                        System.out.println("Type a table name to read all its data");
                        readTables(scanner, statement);

                        break;
                    case 5:
                        runProgram = false;
                        statement.close();
                        connection.close();
                        break;
                    default:
                        if (runProgram) {
                            System.out.println("Enter a valid number");
                            break;
                        }
                }
            }
        } catch (SQLException e) {
            System.out.println("Wrong " + e.getMessage());
        }
    }

    public static void menulist() {
        System.out.println(
                        "Enter:\n**************************************************************" +
                        "\n\t0 - to create a new table\n\t1 - to add a new person\n\t2 - " +
                        "to update a contact's information\n\t3 - " +
                        "to delete a person\n\t4 - " +
                        "to view all the persons\n\t5 - to quit the program\n" +
                        "*******************" +
                        "*******************************************\nEnter a number:");
    }

    public static void createTable(Scanner scanner, Statement statement) {
        try {
            System.out.println("Add a new table ");
            statement.execute("CREATE TABLE IF NOT EXISTS '" + scanner.next() + "'" + " (name TEXT,phone INTEGER, email TEXT)");
        } catch (SQLException e) {
            System.out.println("No such table in the database 🥲🥲.. Try again\n" + e.getMessage() + "\n");
        }
    }

    public static void readTables(Scanner scanner, Statement statement) {
        try {
            statement.execute("SELECT * FROM " + scanner.next());
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println(
                                resultSet.getString(1) + " " +
                                resultSet.getInt(2) + " " +
                                resultSet.getString(3) + "\n");
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("No such table in the database 🥲🥲.. Try again\n" + e.getMessage() + "\n");
        }
    }

    public static void updateRecord(Scanner scanner, Statement statement) {
        try {
            System.out.println("Type in the table name, a new name, phonenumber, gmail and the person you want to change.");
            statement.execute("UPDATE '" + scanner.next() + "' SET name='" + scanner.next() + "' , "
                    + "phone='" + scanner.nextInt() + "' , "
                    + "email ='" + scanner.next() + "' "
                    + "WHERE name ='" + scanner.next() + "'");
        } catch (SQLException e) {
            System.out.println("No such table in the database 🥲🥲.. Try again\n" + e.getMessage() + "\n");
        }
    }

    public static void deleteRecord(Scanner scanner, Statement statement) {
        try {
            System.out.println("To delete a person from the database, type in the table name and then the name of the person.");
            statement.execute("DELETE FROM '" + scanner.next() + "' WHERE name='" + scanner.next() + "'");
        } catch (SQLException e) {
            System.out.println("No such table in the database 🥲🥲.. Try again\n" + e.getMessage() + "\n");
        }
    }

    public static void addToTable(Scanner scanner, Statement statement) {
        try {
            System.out.println("Add a new person - type in table name first.");
            statement.execute("INSERT INTO '" + scanner.next() + "'" + "(name, phone, email)" + "" +
                    "VALUES('" + scanner.next() + "'," + scanner.nextInt() + ",'" + scanner.next() + "')");
        } catch (SQLException e) {
            System.out.println("No such table in the database 🥲🥲.. Try again\n" + e.getMessage() + "\n");
        }
    }
}

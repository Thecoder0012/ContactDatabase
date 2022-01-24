import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {


    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/IdeaProjects/TestDB/testjava.db");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS contacts " + " (name TEXT,phone INTEGER, email TEXT)");
            statement.close(); // always close statement before connection
            connection.close();

        } catch (SQLException e) {
            System.out.println("Wrong " + e.getMessage());
        }
    }
}

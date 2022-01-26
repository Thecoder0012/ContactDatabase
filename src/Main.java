import java.sql.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add a new person to the database");
        String name = scanner.nextLine();
        int phone = scanner.nextInt();
        String email =  scanner.next();

        try {

            Connection connection = DriverManager.getConnection("jdbc:sqlite:/IdeaProjects/TestDB/testjava.db");
//          connection.setAutoCommit(true);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS contacts " + " (name TEXT,phone INTEGER, email TEXT)");
            statement.execute("INSERT INTO contacts (name, phone, email)" +"" + "VALUES('" +  name + "'," + phone + ",'" + email + "')");

            statement.execute("UPDATE contacts SET phone='"+scanner.nextInt()+"' WHERE name ='"+scanner.next()+"'");
           statement.execute("DELETE FROM contacts WHERE name='"+scanner.next()+"'");

            statement.execute("SELECT * FROM contacts ");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
                System.out.println(
                        resultSet.getString("name") + " "+
                        resultSet.getInt("phone") +" " +
                        resultSet.getString("email"));

//                        resultSet.getString(1)+" "+  easier way, gets the index(column) in the database.
//                        resultSet.getInt(2)+" "+
//                        resultSet.getString(3));
            }
            resultSet.close();
            statement.close(); // always close statement before connection
            connection.close();

        } catch (SQLException e) {
            System.out.println("Wrong " + e.getMessage());
        }
    }
}

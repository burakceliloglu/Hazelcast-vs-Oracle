import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Main
{
    static String userName= "SYS as sysdba";
    static String password= "12345";
    static String dbURL = "jdbc:oracle:thin:@localhost:1521/TEST";
    static int MAX_NUMBER=1000;
    static String MESSAGE="DONE.";
    static int NUM_OF_ROWS;

    public static void main(String[] args)
    {
        Connection connection;
        PreparedStatement preparedStatement;
        try
        {
            connection = DriverManager.getConnection(dbURL,userName,password);

            String sql="INSERT INTO DEMO(NUMBER2) VALUES(?)";
            preparedStatement = connection.prepareStatement(sql);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter how many rows will be inserted: ");
            NUM_OF_ROWS=scanner.nextInt();

            Random random = new Random();
            int randomNumber;
            for(int i=0; i<NUM_OF_ROWS ;i++)
            {
                randomNumber=random.nextInt(MAX_NUMBER);
                preparedStatement.setString(1,String.valueOf(randomNumber));
                preparedStatement.executeUpdate();
            }
            System.out.println(MESSAGE);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
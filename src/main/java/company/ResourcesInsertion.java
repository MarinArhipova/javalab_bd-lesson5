package company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResourcesInsertion {
    private final static int N = 1000000;

    private static final String URL = "jdbc:postgresql://localhost:5433/11_03";
    private static final String USER = "postgres";
    private static final String PASSWORD = "555555554ft";


    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO resource(link) VALUES (?);");
        for (int i = 0; i < N; i++) {
            preparedStatement.setString(1, "res" + i);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        conn.commit();
    }
}
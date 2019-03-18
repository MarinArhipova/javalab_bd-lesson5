package company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TagsInsertion {
    private final static int N = 400;

    private static final String URL = "jdbc:postgresql://localhost:5433/11_03";
    private static final String USER = "postgres";
    private static final String PASSWORD = "555555554ft";

    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO tag(title) VALUES (?);");
        for (int i = 0; i < N; i++) {
            preparedStatement.setString(1, "tag" + i);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        conn.commit();
    }
}

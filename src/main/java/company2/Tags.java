package company2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tags {
    private static final int N = 100;
    private static Random r = new Random();

    private static List<Random> tags = new ArrayList<>();

    static {
        for (int i = 1; i < N; i++) {
            r = new Random();
            tags.add(r);
        }
    }

    private static final String URL = "jdbc:postgresql://localhost:5433/11_03";
    private static final String USER = "postgres";
    private static final String PASSWORD = "555555554ft";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO resource1(link, tags) VALUES (?, ?);");
        for (int i = 0; i < N; i++) {
            preparedStatement.setString(1, "res" + i);
            preparedStatement.setInt(2, conn.createArrayOf("integer", tags.get(i)));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        conn.commit();
    }

}

package company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ResourceTagInsertion {
    private static final int N = 1000000;

    private static final String URL = "jdbc:postgresql://localhost:5433/11_03";
    private static final String USER = "postgres";
    private static final String PASSWORD = "555555554ft";

    private static List<Integer> tagIds = new ArrayList<>();

    static {
        for (int i = 1; i < 400; i++) {
            tagIds.add(i);
        }
    }

    private static Random r = new Random();

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO resource_tag(tag_id, resource_id) VALUES (?, ?);");
        for (int i = 1; i < N; i++) {
            int tagsCount = 50 + r.nextInt(100);
            Collections.shuffle(tagIds);
            for (int j = 0; j < tagsCount; j++) {
                preparedStatement.setInt(1, tagIds.get(j));
                preparedStatement.setInt(2, i);
                preparedStatement.addBatch();
                //addBatch - накопливает запросы, и один раз отправляет
            }
            if (i % 10000 == 0) {
                preparedStatement.executeBatch();
                conn.commit();
            }
        }
        preparedStatement.executeBatch();
        conn.commit();
    }
}


package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main(String[] args) {
        // Зчитуємо файл sql/init_db.sql
        String sqlFile = "./src/main/java/sql/init_db.sql";
        String sqlContent = readSqlFile(sqlFile);
        String url="jdbc:postgresql://localhost:32768/modul1";
        String login="postgres";
        String password="1111";
        Database database =new Database(url,login,password);
        // Отримуємо з'єднання з БД
        Connection conn = database.getINSTANCE().getConnection();

        // Виконуємо запити з файлу sql/init_db.sql
        executeSqlQueries(conn, sqlContent);

        // Закриваємо з'єднання з БД
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String readSqlFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private static void executeSqlQueries(Connection conn, String sqlContent) {
        try {
            PreparedStatement statement = conn.prepareStatement(sqlContent);

            String[] queries = sqlContent.split(";");
            for (String query : queries) {
                query = query.trim();
                if (!query.isEmpty()) {
                    statement.execute();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



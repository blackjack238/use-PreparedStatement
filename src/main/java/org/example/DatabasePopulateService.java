package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DatabasePopulateService {
   static Connection conn = new Database("jdbc:postgresql://localhost:32768/modul1","postgres","1111").getINSTANCE().getConnection();
    public static void main(String[] args) {
        // Зчитуємо файл sql/init_db.sql
        String sqlFile = "./src/main/java/sql/populate_db.sql";
        String sqlContent = readSqlFile(sqlFile);

        // Отримуємо з'єднання з БД

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
    public static void insertWorker(String name,String data,String level,int salary){
    String insertWorkersQuery = "INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES (?, ?, ?, ?)";
try (
     PreparedStatement statement = conn.prepareStatement(insertWorkersQuery)) {
        statement.setString(1, name);
        statement.setDate(2, Date.valueOf(data));
        statement.setString(3, level);
        statement.setInt(4, salary);
        statement.executeUpdate();

        // Додаткові вставки працівників з параметрами
        // ...
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public static void insertClient(String name){
        String insertClientsQuery = "INSERT INTO client (NAME) VALUES (?)";
        try (
                PreparedStatement statement =conn.prepareStatement(insertClientsQuery)) {
            statement.setString(1, name);
            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertProjects(int ClientId,String StartData,String FinishData){
        String insertProjectsQuery = "INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES (?, ?, ?)";
        try (
                PreparedStatement statement = conn.prepareStatement(insertProjectsQuery)) {
            statement.setInt(1, ClientId);
            statement.setDate(2, Date.valueOf(StartData));
            statement.setDate(3, Date.valueOf(FinishData));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertProjectWorker(int projectId,int workerId){
        String insertProjectWorkersQuery = "INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES (?, ?)";
        try (
                PreparedStatement statement = conn.prepareStatement(insertProjectWorkersQuery)) {
            statement.setInt(1, projectId);
            statement.setInt(2, workerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package database;

import beans.Assistant;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SQliteDB {
    private Connection c = null;
    private Statement statement = null;
    private Semaphore semaphore;
    private static SQliteDB instance = null;

    private SQliteDB() {
        try {
            semaphore = new Semaphore(1);
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db.sqlite3");
            System.out.println("Connected to DB");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SQliteDB getInstance() {
        if (instance == null)
            instance = new SQliteDB();
        return instance;
    }

    public synchronized List<Assistant> getAllAssistant() {
        List<Assistant> list = new ArrayList<>();
        try {
            semaphore.acquire();
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM assistants");

            while (rs.next()) {
                String name = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int points = rs.getInt("points");
                Assistant assistant = new Assistant(name, lastName, points);
                list.add(assistant);
            }
            semaphore.release();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public synchronized void addAssistant(Assistant assistant) {
        try {
            semaphore.acquire();
            int id = 0;
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM auto_id");

            while (rs.next()) {
                id = rs.getInt("id");
            }

            String query = "INSERT INTO assistants VALUES(";
            query += id + ",'";
            query += assistant.getName() + "','";
            query += assistant.getLastName() + "',";
            query += ((int) assistant.getPoints()) + ")";
            statement = c.createStatement();
            statement.execute(query);

            id++;
            statement = c.createStatement();
            statement.execute("UPDATE auto_id SET id = " + id + " WHERE auto = 'auto'");
            semaphore.release();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            c.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
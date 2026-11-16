package ru.university.rogachev.dao;

import ru.university.rogachev.model.Train;

import java.sql.*;
import java.util.List;

public class SqlTrainDao implements TrainDao {
    private final String url;

    public SqlTrainDao(String url){
        this.url = url;
        createTables();
    }

    private Connection getConnection() throws SQLException{
        Connection conn = DriverManager.getConnection(url);
        try(Statement st = conn.createStatement()){
            st.execute("PRAGMA foreign_keys = ON");
        }
        return conn;
    }

    private void createTables(){
        String trainSql = """
                CREATE TABLE IF NOT EXISTS trains(
                    number TEXT PRIMARY KEY,
                    route_name TEXT NOT NULL
                )""";

        String routeSql = """
                CREATE TABLE IF NOT EXISTS route_point (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    train_number TEXT NOT NULL,
                    point_order INTEGER NOT NULL,
                    station TEXT NOT NULL,
                    arrival_time TEXT,
                    departure_time TEXT,
                    FOREIGN KEY (train_number) REFERENCES trains(number) ON DELETE CASCADE
                )""";

        try(Connection conn = getConnection(); Statement st  = conn.createStatement()){
            st.execute(trainSql);
            st.execute(routeSql);
        }catch (SQLException e){
            throw new RuntimeException("Не удалось подключиться к бд", e);
        }
    }

    @Override
    public void create(Train train) {

    }

    @Override
    public Train findByNumber(String number) {
        return null;
    }

    @Override
    public List<Train> findAll() {
        return List.of();
    }

    @Override
    public void delete(String number) {
        String sql = "DELETE FROM trains WHERE number = ?";

        try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, number);
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Не удалось удалить",e);
        }
    }

}

package exam.case1.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static Connection connection = null;

    public static void createConection() {
        // MySQL Connection 접속정보
        String url = "jdbc:mysql://192.168.23.60:3307/kopo11";
        String user = "root";
        String passwd = "shdmf1030@";
        try {
            // MySQL 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // MySQL Connection 생성
            connection = DriverManager.getConnection(url, user, passwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // 싱글턴 패턴의 Connection 객체 반환 메소드
    public static Connection getConnection() {
        if (connection == null) {
            createConection();
        }
        return connection;
    }
}

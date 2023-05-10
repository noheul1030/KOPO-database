package exam.case1.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static Connection connection = null;

    public static void createConection() {
        // MySQL Connection ��������
        String url = "jdbc:mysql://192.168.23.60:3307/kopo11";
        String user = "root";
        String passwd = "shdmf1030@";
        try {
            // MySQL ����̹� �ε�
            Class.forName("com.mysql.cj.jdbc.Driver");

            // MySQL Connection ����
            connection = DriverManager.getConnection(url, user, passwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // �̱��� ������ Connection ��ü ��ȯ �޼ҵ�
    public static Connection getConnection() {
        if (connection == null) {
            createConection();
        }
        return connection;
    }
}

package exam.case1;

import java.sql.Connection;
import java.sql.SQLException;

import exam.case1.dao.Table1Dao;
import exam.case1.database.MySqlConnection;

public class Table1InsertExecutor {

    public static void main(String[] args) {
        new Table1InsertExecutor().execute();
    }

    public void execute() {
        Connection con = MySqlConnection.getConnection();
        try {
            // �ڵ�Ŀ�� ����
            con.setAutoCommit(false);

            // ������ ���� ����
            Table1Dao dao = new Table1Dao();
            dao.insertData(MySqlConnection.getConnection());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // �ڵ�Ŀ�� �ѱ�
                con.setAutoCommit(true);
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

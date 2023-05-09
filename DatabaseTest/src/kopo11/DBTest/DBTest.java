package kopo11.DBTest;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {

	// Class�� ã�� ���� ��� �����߻� , SQL���� �߻�
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ��� 
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		// ������ ������ ������� �����ϴ� ���� ����(ȣ�� �� �� show databases�� ������ش�.)
		ResultSet kopo11_rset = kopo11_stmt.executeQuery("show databases;");
		
		// ���� ���� ��� ���� �����ִ� �ݺ���(���� ������ ���ð� ��� false�� �Ǹ� �ݺ��� ����)
		while (kopo11_rset.next()) {
			System.out.println("�� : "+kopo11_rset.getString(1)); // kopo11_rset�� 1��° �� print
		}
		kopo11_rset.close(); // ResultSet ��ü ����
		kopo11_stmt.close(); // Statement ��ü ����
		kopo11_conn.close(); // Connection ��ü ����
	}

}

package kopo11.DBTest;

import java.sql.*;
public class DeleteExam {
	
	// Class�� ã�� ���� ��� �����߻� , SQL���� �߻�
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		// JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ��� 
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		
		// sql ���� ���� : anoutherexamtable�� �����ض�
		kopo11_stmt.execute("delete from anoutherexamtable;");
		
		kopo11_stmt.close(); // Statement ��ü ����
		kopo11_conn.close();  // Connection ��ü ����

	}

}

package kopo11.DBTest;

import java.sql.*;
public class SelectExam {

	// Class�� ã�� ���� ��� �����߻� , SQL���� �߻�
	public static void main(String[] args)  throws ClassNotFoundException, SQLException {
		// JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ��� 
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		// ������ ������ ������� �����ϴ� ���� ����(ȣ�� �� �� show databases�� ������ش�.)
		ResultSet kopo11_rset = kopo11_stmt.executeQuery("select * from anoutherexamtable;");
		
		// �̸� �й� ���� ���� ���� ���� print 
		System.out.printf("  �̸�	�й�	����	����	���� \n");
		
		// ���� ���� ��� ���� �����ִ� �ݺ���(���� ������ ���ð� ��� false�� �Ǹ� �ݺ��� ����)
		while(kopo11_rset.next()) {
			// ���ϴ� ������ ���·� kopo11_rset�� ���� 1������ ~ 5������ �̾ ����Ѵ�.
			System.out.printf("%4s	%6d	%3d	%3d	%3d \n",kopo11_rset.getString(1)
					,kopo11_rset.getInt(2),kopo11_rset.getInt(3)
					,kopo11_rset.getInt(4),kopo11_rset.getInt(5));
		}
		
		kopo11_rset.close(); // ResultSet ��ü ����
		kopo11_stmt.close(); // Statement ��ü ����
		kopo11_conn.close(); // Connection ��ü ����
	}

}

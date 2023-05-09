package kopo11.DBTest;

import java.sql.*;

public class Examtable {

	// Class�� ã�� ���� ��� �����߻� , SQL���� �߻�
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ���
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		
		/* sql ���� ���� : anoutherexamtable�� ����� name field(���� �� 20)
		 * 	studentid�� null�� ����� �� �� ���� primary key����
		 *  kor,eng,mat�� int������ field�� �����.
		 *  default charset���δ� UTF-8�� ���� 
		 */
		kopo11_stmt.execute( "Create table anoutherexamtable("+"name varchar(20),"
				+"studentid int not null primary key,"
				+"kor	int,"
				+"eng	int,"
				+"mat	int)"
				+"DEFAULT CHARSET=utf8;");
		
		kopo11_stmt.close(); // Statement ��ü ����
		kopo11_conn.close();  // Connection ��ü ����
	}

}

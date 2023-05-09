package kopo11.basicTraining2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GradeTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ��� 
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		
		// freewifi ���̺� �����ͺ��̽��� ������ �����ϴ� ����
//		kopo11_stmt.execute("drop table GradeList;");
		
		kopo11_stmt.execute(  "create table GradeList(" // sql ���� ���� : GradeList ���̺��� ����� 
				+"studentID int not null,"				// 1.studentID field not null ����
				+"name	varchar(50), " 				    // 2.�̸� (field ũ�� 50 ����)
				+"kor	int, "						    // 3.���� (field int ����)
				+"eng	int, "			 				// 4.���� (field int ����)
				+"mat	int, " 		 					// 5.���� (field int ����)
				+"sum	int, " 		 					// 6.�հ� (field int ����)
				+"avg	int, " 		 					// 7.��� (field int ����)				
				+"primary key (studentID)" 			    // primary key(studentID)���� 
				+") DEFAULT CHARSET=utf8;" 			    // UTF-8�� default
 				);
		
		kopo11_stmt.close(); // Statement ��ü ����
		kopo11_conn.close();  // Connection ��ü ����
	}
}

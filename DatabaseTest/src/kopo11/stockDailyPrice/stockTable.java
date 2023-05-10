package kopo11.stockDailyPrice;

import java.sql.*;
public class stockTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ��� 
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		
		// freewifi ���̺� �����ͺ��̽��� ������ �����ϴ� ����
		kopo11_stmt.execute("drop table stockDailyPrice;");
		
		kopo11_stmt.execute(  "create table stockDailyPrice(" 	// sql ���� ���� : GradeList ���̺��� ����� 
				+"stockCODE 	varchar(50) not null,"			// 1.�����ڵ� field not null ����
				+"Daily		varchar(50), " 				   		// 2.���� (field ũ�� 50 ����)
				+"opening_price		int, "						// 3.�ð� (field int ����)
				+"high_price	int, "			 				// 4.�� (field int ����)
				+"low_price	int, "			 					// 5.���� (field int ����)
				+"closing_price	int, " 		 					// 6.���� (field int ����)
				+"trading_volume	int, " 		 				// 7.�ŷ��� (field int ����)
				+"trading_amount	long, " 		 				// 8.�ŷ���� (field long ����)				
				+"primary key (stockCODE,Daily)" 			    // primary key(studentID,Daily)���� 
				+") DEFAULT CHARSET=utf8;" 			    		// UTF-8�� default
 				);
		
		kopo11_stmt.close(); // Statement ��ü ����
		kopo11_conn.close();  // Connection ��ü ����
	}

}

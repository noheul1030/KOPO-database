package kopo11.DBTest;

import java.sql.*;
public class InsertExam {

	// Class�� ã�� ���� ��� �����߻� , SQL���� �߻�
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ��� 
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		
		// anoutherexamtable �����ͺ��̽� �ʵ忡 �� �Է� ('ȿ��',�й�,����,����,����)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('ȿ��',209901,95,100,95);");
		// anoutherexamtable �����ͺ��̽� �ʵ忡 �� �Է� ('����',�й�,����,����,����)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('����',209902,95,95,95);");
		// anoutherexamtable �����ͺ��̽� �ʵ忡 �� �Է� ('����',�й�,����,����,����)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('����',209903,100,100,100);");
		// anoutherexamtable �����ͺ��̽� �ʵ忡 �� �Է� ('����',�й�,����,����,����)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('����',209904,100,95,90);");
		// anoutherexamtable �����ͺ��̽� �ʵ忡 �� �Է� ('�ҿ�',�й�,����,����,����)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('�ҿ�',209905,80,100,70);");
		// anoutherexamtable �����ͺ��̽� �ʵ忡 �� �Է� ('ť��',�й�,����,����,����)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('ť��',209906,100,100,70);");
		// anoutherexamtable �����ͺ��̽� �ʵ忡 �� �Է� ('ȭ��',�й�,����,����,����)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('ȭ��',209907,70,70,70);");
		
		kopo11_stmt.close(); // Statement ��ü ����
		kopo11_conn.close();  // Connection ��ü ����
	}
}


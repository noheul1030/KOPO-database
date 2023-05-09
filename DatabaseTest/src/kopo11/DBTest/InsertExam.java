package kopo11.DBTest;

import java.sql.*;
public class InsertExam {

	// Class를 찾지 못할 경우 오류발생 , SQL오류 발생
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// JDBC 버전에 따라 com.mysql.cj.jdbc.Driver를 사용 
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql에 연결될 나의 IP:포워딩 포트번호/database이름,DB사용자,DB비밀번호
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt = kopo11_conn.createStatement();
		
		// anoutherexamtable 데이터베이스 필드에 값 입력 ('효민',학번,국어,영어,수학)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('효민',209901,95,100,95);");
		// anoutherexamtable 데이터베이스 필드에 값 입력 ('보람',학번,국어,영어,수학)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('보람',209902,95,95,95);");
		// anoutherexamtable 데이터베이스 필드에 값 입력 ('은정',학번,국어,영어,수학)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('은정',209903,100,100,100);");
		// anoutherexamtable 데이터베이스 필드에 값 입력 ('지연',학번,국어,영어,수학)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('지연',209904,100,95,90);");
		// anoutherexamtable 데이터베이스 필드에 값 입력 ('소연',학번,국어,영어,수학)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('소연',209905,80,100,70);");
		// anoutherexamtable 데이터베이스 필드에 값 입력 ('큐리',학번,국어,영어,수학)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('큐리',209906,100,100,70);");
		// anoutherexamtable 데이터베이스 필드에 값 입력 ('화영',학번,국어,영어,수학)
		kopo11_stmt.execute("insert into anoutherexamtable (name,studentid,kor,eng,mat) values ('화영',209907,70,70,70);");
		
		kopo11_stmt.close(); // Statement 객체 종료
		kopo11_conn.close();  // Connection 객체 종료
	}
}


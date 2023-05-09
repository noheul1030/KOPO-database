package kopo11.DBTest;

import java.sql.*;

public class Examtable {

	// Class를 찾지 못할 경우 오류발생 , SQL오류 발생
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// JDBC 버전에 따라 com.mysql.cj.jdbc.Driver를 사용
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql에 연결될 나의 IP:포워딩 포트번호/database이름,DB사용자,DB비밀번호
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt = kopo11_conn.createStatement();
		
		/* sql 쿼리 실행 : anoutherexamtable을 만들고 name field(내부 값 20)
		 * 	studentid는 null이 만들어 질 수 없고 primary key지정
		 *  kor,eng,mat은 int형으로 field를 만든다.
		 *  default charset으로는 UTF-8로 지정 
		 */
		kopo11_stmt.execute( "Create table anoutherexamtable("+"name varchar(20),"
				+"studentid int not null primary key,"
				+"kor	int,"
				+"eng	int,"
				+"mat	int)"
				+"DEFAULT CHARSET=utf8;");
		
		kopo11_stmt.close(); // Statement 객체 종료
		kopo11_conn.close();  // Connection 객체 종료
	}

}

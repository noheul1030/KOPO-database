package kopo11.basicTraining2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GradeTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// JDBC 버전에 따라 com.mysql.cj.jdbc.Driver를 사용 
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql에 연결될 나의 IP:포워딩 포트번호/database이름,DB사용자,DB비밀번호
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt = kopo11_conn.createStatement();
		
		// freewifi 테이블 데이터베이스를 완전히 삭제하는 쿼리
//		kopo11_stmt.execute("drop table GradeList;");
		
		kopo11_stmt.execute(  "create table GradeList(" // sql 쿼리 실행 : GradeList 테이블을 만들고 
				+"studentID int not null,"				// 1.studentID field not null 지정
				+"name	varchar(50), " 				    // 2.이름 (field 크기 50 지정)
				+"kor	int, "						    // 3.국어 (field int 지정)
				+"eng	int, "			 				// 4.영어 (field int 지정)
				+"mat	int, " 		 					// 5.수학 (field int 지정)
				+"sum	int, " 		 					// 6.합계 (field int 지정)
				+"avg	int, " 		 					// 7.평균 (field int 지정)				
				+"primary key (studentID)" 			    // primary key(studentID)지정 
				+") DEFAULT CHARSET=utf8;" 			    // UTF-8로 default
 				);
		
		kopo11_stmt.close(); // Statement 객체 종료
		kopo11_conn.close();  // Connection 객체 종료
	}
}

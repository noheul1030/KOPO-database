package kopo11.DBTest;

import java.sql.*;
public class SelectExam {

	// Class를 찾지 못할 경우 오류발생 , SQL오류 발생
	public static void main(String[] args)  throws ClassNotFoundException, SQLException {
		// JDBC 버전에 따라 com.mysql.cj.jdbc.Driver를 사용 
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql에 연결될 나의 IP:포워딩 포트번호/database이름,DB사용자,DB비밀번호
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt = kopo11_conn.createStatement();
		// 쿼리를 실행한 결과값을 저장하는 변수 생성(호출 될 때 show databases를 출력해준다.)
		ResultSet kopo11_rset = kopo11_stmt.executeQuery("select * from anoutherexamtable;");
		
		// 이름 학번 국어 영어 수학 개행 print 
		System.out.printf("  이름	학번	국어	영어	수학 \n");
		
		// 쿼리 실행 결과 값을 보여주는 반복문(다음 내용이 나올게 없어서 false가 되면 반복문 종료)
		while(kopo11_rset.next()) {
			// 원하는 포멧팅 형태로 kopo11_rset의 값을 1번부터 ~ 5번까지 이어서 출력한다.
			System.out.printf("%4s	%6d	%3d	%3d	%3d \n",kopo11_rset.getString(1)
					,kopo11_rset.getInt(2),kopo11_rset.getInt(3)
					,kopo11_rset.getInt(4),kopo11_rset.getInt(5));
		}
		
		kopo11_rset.close(); // ResultSet 객체 종료
		kopo11_stmt.close(); // Statement 객체 종료
		kopo11_conn.close(); // Connection 객체 종료
	}

}

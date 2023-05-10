package kopo11.stockDailyPrice;

import java.sql.*;
public class stockTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// JDBC 버전에 따라 com.mysql.cj.jdbc.Driver를 사용 
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql에 연결될 나의 IP:포워딩 포트번호/database이름,DB사용자,DB비밀번호
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt = kopo11_conn.createStatement();
		
		// freewifi 테이블 데이터베이스를 완전히 삭제하는 쿼리
		kopo11_stmt.execute("drop table stockDailyPrice;");
		
		kopo11_stmt.execute(  "create table stockDailyPrice(" 	// sql 쿼리 실행 : GradeList 테이블을 만들고 
				+"stockCODE 	varchar(50) not null,"			// 1.단축코드 field not null 지정
				+"Daily		varchar(50), " 				   		// 2.일자 (field 크기 50 지정)
				+"opening_price		int, "						// 3.시가 (field int 지정)
				+"high_price	int, "			 				// 4.고가 (field int 지정)
				+"low_price	int, "			 					// 5.저가 (field int 지정)
				+"closing_price	int, " 		 					// 6.종가 (field int 지정)
				+"trading_volume	int, " 		 				// 7.거래량 (field int 지정)
				+"trading_amount	long, " 		 				// 8.거래대금 (field long 지정)				
				+"primary key (stockCODE,Daily)" 			    // primary key(studentID,Daily)지정 
				+") DEFAULT CHARSET=utf8;" 			    		// UTF-8로 default
 				);
		
		kopo11_stmt.close(); // Statement 객체 종료
		kopo11_conn.close();  // Connection 객체 종료
	}

}

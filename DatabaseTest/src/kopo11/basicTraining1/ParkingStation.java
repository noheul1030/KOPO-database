package kopo11.basicTraining1;

import java.sql.*;

public class ParkingStation {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// JDBC 버전에 따라 com.mysql.cj.jdbc.Driver를 사용 
				Class.forName("com.mysql.cj.jdbc.Driver");
				// mysql에 연결될 나의 IP:포워딩 포트번호/database이름,DB사용자,DB비밀번호
				Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
						,"root","shdmf1030@");
				// sql 쿼리를 실행하기 위한 객체 변수 생성
				Statement kopo11_stmt = kopo11_conn.createStatement();
				
				// freewifi 테이블 데이터베이스를 완전히 삭제하는 쿼리
				kopo11_stmt.execute("drop table ParkingStation;");
				
				kopo11_stmt.execute(  "create table ParkingStation(" // sql 쿼리 실행 : ParkingStation 테이블을 만들고 
						+"number int not null,"						 // 1.number field not null 지정
						+"manage_office	varchar(50), " 				 // 2.관리기관명 (field 크기 50 지정)
						+"installation_purpose	varchar(50), "		 // 3.설치목적구분 (field 크기 50 지정)
						+"place_addr_road	varchar(200), "			 // 4.소재지도로명주소 (field 크기 200 지정)
						+"place_addr_land	varchar(200), " 		 // 5.소재지지번주소 (field 크기 200 지정)
						+"camera_count int," 						 // 6.카메라대수
						+"camera_pixel int," 						 // 7.카메라화소
						+"shooting_angle varchar(200)," 			 // 8.촬영방면정보
						+"storage_period int," 						 // 9.보관일수
						+"inst_date	date NULL, " 					 // 10.설치년월
						+"manage_office_phone	varchar(50), " 		 // 11.관리기관전화번호 (field 크기 50 지정)
						+"latitude	double, " 						 // 12.위도 (field 값 double 지정)
						+"longitude	double, " 						 // 13.경도 (field 값 double 지정)
						+"write_date date not null, " 				 // 14.데이터기준일자 field not null 지정
						+"primary key (number, write_date)" 		 // primary key(number,write_date)지정 
						+") DEFAULT CHARSET=utf8;" 					 // UTF-8로 default
		 				);
				
				kopo11_stmt.close(); // Statement 객체 종료
				kopo11_conn.close();  // Connection 객체 종료
	}
}

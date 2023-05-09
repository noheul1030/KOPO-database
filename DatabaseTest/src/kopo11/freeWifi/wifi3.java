package kopo11.freeWifi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class wifi3 {

	// Class를 찾지 못할 경우 오류발생처리 , SQL오류 발생처리, IOE 오류 처리
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// JDBC 버전에 따라 com.mysql.cj.jdbc.Driver를 사용
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql에 연결될 나의 IP:포워딩 포트번호/database이름,DB사용자,DB비밀번호
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11", "root",
				"shdmf1030@");
		// sql 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt = kopo11_conn.createStatement();
		
		double kopo11_lat = 37.3860521; // 위도
		double kopo11_lng = 127.1214038; // 경도
		
		String kopo11_QueryTxt;// String 변수 선언
		// 원하는 string형태로 포멧한 값을 kopo11_QueryTxt에 저장한다.
		// 위도,경도를 가지고 최단거리 구하는 쿼리문 
		kopo11_QueryTxt = String.format("select * from freewifi where " 
				+ " SQRT(POWER(latitude-%f,2) + POWER(longitude-%f,2))="
				+ "(select MIN(SQRT(POWER(latitude-%f,2) + POWER(longitude-%f,2)))from freewifi);",
				kopo11_lat,kopo11_lng,kopo11_lat,kopo11_lng);
		
		// freewifi의 모든 정보를 조회하는  쿼리문 
//		kopo11_QueryTxt = "select * from freewifi";
		// freewifi의 service_provider field에서 'SKT'를 조회하는  쿼리문 
//		kopo11_QueryTxt = "select * from freewifi where service_provider = 'SKT'";
		// freewifi의 service_country field에서 '수원시'를 조회하는  쿼리문 
//		kopo11_QueryTxt = "select * from freewifi where service_country = '수원시'";
		
		// 쿼리를 실행한 결과값을 저장하는 변수 생성(호출 될 때 show databases를 출력해준다.)
		ResultSet kopo11_rset = kopo11_stmt.executeQuery(kopo11_QueryTxt);
		int kopo11_iCnt = 0; // int 변수에 0값 대입
		
		// 쿼리 실행 결과 값을 보여주는 반복문(다음 내용이 나올게 없어서 false가 되면 반복문 종료)
		while((kopo11_rset.next())) {
			// 값,***라인 출력 개행
			System.out.printf("*(%s번)*******************************************\n",kopo11_rset.getString(1)); 
			System.out.printf("설치장소명               : %s\n",kopo11_rset.getString(2)); // kopo11_rset 1 번째 값 출력 개행
			System.out.printf("설치장소상세             : %s\n",kopo11_rset.getString(3)); // kopo11_rset 2 번째 값 출력 개행
			System.out.printf("설치시도명               : %s\n",kopo11_rset.getString(4)); // kopo11_rset 3 번째 값 출력 개행
			System.out.printf("설치시군구명             : %s\n",kopo11_rset.getString(5)); // kopo11_rset 4 번째 값 출력 개행
			System.out.printf("설치시설구분             : %s\n",kopo11_rset.getString(6)); // kopo11_rset 5 번째 값 출력 개행
			System.out.printf("서비스제공사명           : %s\n",kopo11_rset.getString(7)); // kopo11_rset 6 번째 값 출력 개행
			System.out.printf("와이파이SSID             : %s\n",kopo11_rset.getString(8)); // kopo11_rset 7 번째 값 출력 개행
			System.out.printf("설치년월                 : %s\n",kopo11_rset.getString(9)); // kopo11_rset 8 번째 값 출력 개행
			System.out.printf("소재지도로명주소         : %s\n",kopo11_rset.getString(10)); // kopo11_rset 9 번째 값 출력 개행
			System.out.printf("소재지지번주소           : %s\n",kopo11_rset.getString(11)); // kopo11_rset 10 번째 값 출력 개행
			System.out.printf("관리기관명               : %s\n",kopo11_rset.getString(12)); // kopo11_rset 11 번째 값 출력 개행
			System.out.printf("관리기관전화번호         : %s\n",kopo11_rset.getString(13)); // kopo11_rset 12 번째 값 출력 개행
			// kopo11_rset 13 번째 값 출력 개행
			System.out.printf("위도                     : %f\n",Double.parseDouble(kopo11_rset.getString(14))); 
			// kopo11_rset 14 번째 값 출력 개행
			System.out.printf("경도                     : %f\n",Double.parseDouble(kopo11_rset.getString(15))); 
			System.out.printf("데이터기준일자           : %s\n",kopo11_rset.getString(16)); // kopo11_rset 15 번째 값 출력 개행
			System.out.printf("****************************************************\n");// ***라인 출력 개행
			
		}
		kopo11_rset.close(); // ResultSet 객체 종료
		kopo11_stmt.close(); // Statement 객체 종료
		kopo11_conn.close();  // Connection 객체 종료
	}
	

}

package kopo11.basicTraining2;

import java.io.*;
import java.sql.*;
import java.util.Random;

public class GradeExcute {
	public static int random() {
		return new Random().nextInt(101);
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// JDBC 버전에 따라 com.mysql.cj.jdbc.Driver를 사용
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql에 연결될 나의 IP:포워딩 포트번호/database이름,DB사용자,DB비밀번호
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11", "root",
				"shdmf1030@");
		// sql 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt = kopo11_conn.createStatement();
		kopo11_stmt.execute("delete from GradeList;");

		/*
		 * // resource/전국무료와이파이표준데이터.txt File 불러 오기 File kopo11_f = new
		 * File("resource/gradeSave.csv"); // 불러온 파일을 BufferedReaderfh 읽는다.
		 * BufferedReader kopo11_br = new BufferedReader(new FileReader(kopo11_f));
		 * 
		 * String kopo11_readtxt; // String 변수 선언
		 * 
		 * // BufferedReader로 한줄씩 읽는 kopo11_readtxt 값이 null이면 if ((kopo11_readtxt =
		 * kopo11_br.readLine()) == null) { System.out.printf("빈 파일입니다.\n"); // 안내 문구 출력
		 * 개행 return; // 리턴 }
		 * 
		 * // kopo11_readtxt의 내용을 \t기준으로 split해서 String 배열에 담는다. String[]
		 * kopo11_field_name = kopo11_readtxt.split(",");
		 * 
		 * int kopo11_LineCnt = 0; // int 변수에 0값 대입
		 */
		
		String kopo11_minimumTxt;// String 변수 선언
//		// 원하는 string형태로 포멧한 값을 kopo11_QueryTxt에 저장한다.
//		// 위도,경도를 가지고 최단거리 구하는 쿼리문 
//		kopo11_minimumTxt = String.format("select * from ParkingStation where " 
//				+ " SQRT(POWER(latitude-%f,2) + POWER(longitude-%f,2))="
//				+ "(select MIN(SQRT(POWER(latitude-%f,2) + POWER(longitude-%f,2)))from ParkingStation);",
//				kopo11_lat,kopo11_lng,kopo11_lat,kopo11_lng);
//		
//		// 쿼리를 실행한 결과값을 저장하는 변수 생성(호출 될 때 show databases를 출력해준다.)
//		ResultSet kopo11_rset = kopo11_stmt.executeQuery(kopo11_minimumTxt);
//		
//		while((kopo11_rset.next())) {
//			// 값,***라인 출력 개행
//			System.out.printf("*(ID Number %s번)*********************************\n",kopo11_rset.getString(1)); 
//			System.out.printf("관리기관명               : %s\n",kopo11_rset.getString(2)); // kopo11_rset 1 번째 값 출력 개행
//			System.out.printf("설치목적구분             : %s\n",kopo11_rset.getString(3)); // kopo11_rset 2 번째 값 출력 개행
//			System.out.printf("소재지도로명주소         : %s\n",kopo11_rset.getString(4)); // kopo11_rset 3 번째 값 출력 개행
//			System.out.printf("소재지지번주소           : %s\n",kopo11_rset.getString(5)); // kopo11_rset 4 번째 값 출력 개행
//			System.out.printf("카메라대수               : %d\n",Integer.parseInt(kopo11_rset.getString(6))); // kopo11_rset 5 번째 값 출력 개행
//			System.out.printf("카메라화소               : %d\n",Integer.parseInt(kopo11_rset.getString(7))); // kopo11_rset 6 번째 값 출력 개행
//			System.out.printf("촬영방면정보             : %s\n",kopo11_rset.getString(8)); // kopo11_rset 7 번째 값 출력 개행
//			System.out.printf("보관일수                 : %d\n",Integer.parseInt(kopo11_rset.getString(9))); // kopo11_rset 8 번째 값 출력 개행
//			System.out.printf("설치년월                 : %s\n",kopo11_rset.getString(10)); // kopo11_rset 9 번째 값 출력 개행
//			System.out.printf("관리기관전화번호         : %s\n",kopo11_rset.getString(11)); // kopo11_rset 10 번째 값 출력 개행
//			// kopo11_rset 12 번째 값 출력 개행
//			System.out.printf("위도                     : %f\n",Double.parseDouble(kopo11_rset.getString(12))); 
//			// kopo11_rset 13 번째 값 출력 개행
//			System.out.printf("경도                     : %f\n",Double.parseDouble(kopo11_rset.getString(13))); 
//			System.out.printf("데이터기준일자           : %s\n",kopo11_rset.getString(14)); // kopo11_rset 14 번째 값 출력 개행
//			System.out.printf("****************************************************\n");// ***라인 출력 개행
//			
//		}
		
		
		//// 데이터DB입력코드///////////////////////////////////////////////////////////////////////////////

		String kopo11_QueryTxt; // String 변수 선언
		String name = "홍길동";
		int count = 0;

		// 원하는 string형태로 포멧한 값을 kopo11_QueryTxt에 저장한다.
		// kopo11_field의 [0]~[15]째 까지 이어 붙인 문자열을 kopo11_QueryTxt에 저장
		while (count < 1000) {
			int kor = random();
			int eng = random();
			int mat = random();

			if (count < 10) {
				kopo11_QueryTxt = String.format(
						"insert into GradeList (studentID,name,kor,eng,mat,sum,avg) values ('%s','%s','%s','%s','%s','%s','%s');",
						count, name + "0" + count, kor, eng, mat, kor + eng + mat, (kor + eng + mat) / 3);
				// 항목의 insert 진행중인 count와 현황 print 개행
				System.out.printf("%d번째 항목 Insert OK [%s]\n", count, kopo11_QueryTxt);
				count++;
			} else {
				kopo11_QueryTxt = String.format(
						"insert into GradeList (studentID,name,kor,eng,mat,sum,avg) values ('%s','%s','%s','%s','%s','%s','%s');",
						count, name + count, kor, eng, mat, kor + eng + mat, (kor + eng + mat) / 3);
				// 항목의 insert 진행중인 count와 현황 print 개행
				System.out.printf("%d번째 항목 Insert OK [%s]\n", count, kopo11_QueryTxt);
				count++;
			}
			// 문자열이 완성된 값으로 쿼리 실행
			kopo11_stmt.execute(kopo11_QueryTxt);
		}
		kopo11_stmt.close(); // Statement 객체 종료
		kopo11_conn.close(); // Connection 객체 종료
	}

}

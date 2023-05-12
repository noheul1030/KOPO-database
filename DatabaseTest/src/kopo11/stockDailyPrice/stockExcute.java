package kopo11.stockDailyPrice;

import java.io.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class stockExcute {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// JDBC 버전에 따라 com.mysql.cj.jdbc.Driver를 사용
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql에 연결될 나의 IP:포워딩 포트번호/database이름,DB사용자,DB비밀번호
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11", "root",
				"shdmf1030@");

//		inputdata(kopo11_conn); // 0. 데이터 입력 메서드 
//		Samsung(kopo11_conn); 	// 1. 삼성전자 종목 추출 메서드
		DateCheck(kopo11_conn); // 2. 특정일자 추출 메서드

		kopo11_conn.close(); // Connection 종료

	}

	// 1. 삼성전자 종목 추출 메서드
	public static void Samsung(Connection kopo11_conn) throws NumberFormatException, SQLException {
		// DecimalFormat 객체 생성
		DecimalFormat kopo11_df = new DecimalFormat("###,###,###,###,###,###,###");

		// sql 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt = kopo11_conn.createStatement();
		// Individual_performance 변수에 쿼리 실행문 대입
		String kopo11_stockCODE = String.format("SELECT * FROM stockDailyPrice where stockCODE = 'A005930'");
		// 쿼리를 실행한 결과값을 저장하는 변수 생성
		ResultSet kopo11_QuaryTxt1 = kopo11_stmt.executeQuery(kopo11_stockCODE);

		int kopo11_count = 0; 							  // int 변수 0 대입

		while ((kopo11_QuaryTxt1.next())) { // 쿼리 결과값을 하나씩 꺼내오는 반복문
			System.out.printf("\n%15s\n", "삼성전가 주가 집계표"); // 머리말
			// ***라인 출력개행
			System.out.printf("*****************************\n");
			// kopo11_QuaryTxt1 1 번째 값 출력 개행
			System.out.printf("종목코드  : %s\n", kopo11_QuaryTxt1.getString(1));
			// kopo11_QuaryTxt1 2 번째 값 출력 개행
			System.out.printf("일  자    : %s\n", kopo11_QuaryTxt1.getString(2));
			// kopo11_QuaryTxt1 3 번째 값 출력 개행
			System.out.printf("시  가    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(3)));
			// kopo11_QuaryTxt1 4 번째 값 출력 개행
			System.out.printf("고  가    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(4)));
			// kopo11_QuaryTxt1 5 번째 값 출력 개행)
			System.out.printf("저  가    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(5)));
			// kopo11_QuaryTxt1 6 번째 값 출력 개행
			System.out.printf("종  가    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(6)));
			// kopo11_QuaryTxt1 7 번째 값 출력 개행
			System.out.printf("거래량    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(7)));
			// kopo11_QuaryTxt1 8 번째 값 출력 개행
			System.out.printf("거래대금  : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getLong(8)));
			kopo11_count++; // 1 추가
			// ***라인 출력개행
			System.out.printf("*****************************\n");
		}
		System.out.printf("삼성전가 종목코드 갯수 : %d", kopo11_count); // 삼성전자 count 출력
		kopo11_stmt.close(); // Statement 객체 종료
	}

	// 2. 특정일자 추출 메서드
	public static void DateCheck(Connection kopo11_conn) throws NumberFormatException, SQLException {
		// DecimalFormat 객체 생성
		DecimalFormat kopo11_df = new DecimalFormat("###,###,###,###,###,###,###");

		// sql 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt = kopo11_conn.createStatement();
		// Individual_performance 변수에 쿼리 실행문 대입
		String kopo11_stockCODE = String.format("SELECT * FROM stockDailyPrice where Daily = '20150112'");
		// 쿼리를 실행한 결과값을 저장하는 변수 생성
		ResultSet kopo11_QuaryTxt1 = kopo11_stmt.executeQuery(kopo11_stockCODE);

		int kopo11_count = 0; 							  // int 변수 0 대입

		while ((kopo11_QuaryTxt1.next())) { // 쿼리 결과값을 하나씩 꺼내오는 반복문
			System.out.printf("%20s\n", "2015년 01월 12일 추출 정보 집계표"); // 머리말
			// ***라인 출력개행
			System.out.printf("**********************************\n");
			// kopo11_QuaryTxt1 1 번째 값 출력 개행
			System.out.printf("종목코드  : %s\n", kopo11_QuaryTxt1.getString(1));
			// kopo11_QuaryTxt1 3 번째 값 출력 개행
			System.out.printf("시  가    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(3)));
			// kopo11_QuaryTxt1 4 번째 값 출력 개행
			System.out.printf("고  가    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(4)));
			// kopo11_QuaryTxt1 5 번째 값 출력 개행
			System.out.printf("저  가    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(5)));
			// kopo11_QuaryTxt1 6 번째 값 출력 개행
			System.out.printf("종  가    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(6)));
			// kopo11_QuaryTxt1 7 번째 값 출력 개행
			System.out.printf("거래량    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(7)));
			// kopo11_QuaryTxt1 8 번째 값 출력 개행
			System.out.printf("거래대금  : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getLong(8)));
			kopo11_count++; // 1 추가
			// ***라인 출력개행
			System.out.printf("**********************************\n");
		}
		System.out.printf("2015년 01월 12일 추출 정보 갯수 : %d", kopo11_count); // 추출 정보 갯수 count 출력
		kopo11_stmt.close(); // Statement 객체 종료
	}
	
	// 0. 데이터 입력 메서드
	public static void inputdata(Connection kopo11_conn) throws SQLException, IOException {
//		kopo11_pstmt.execute("delete from stockDailyPrice;"); // 테이블 field 데이터 삭제(테이블은 남겨둔다.)
		SimpleDateFormat kopo11_Time = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss"); // 날짜, 시간 형식 객체 생성
		Calendar kopo11_current = Calendar.getInstance(); // 출력시간
		System.out.printf("%s\n", "적재 시작 시간 : " + kopo11_Time.format(kopo11_current.getTime())); // 시작 출력
		System.out.println("CSV 파일 적재를 시작합니다."); // 적재시작 문구 print
		
		// kopo11_QueryTxt에 stockDailyPrice 필드 형태 지정 값은 ? 지정
		String kopo11_QueryTxt = "insert into stockDailyPrice (stockCODE,Daily,opening_price,high_price,low_price,closing_price,trading_volume,trading_amount)"
				+ "values (?,?,?,?,?,?,?,?)";
		// PreparedStatement 객체 생성
		PreparedStatement kopo11_pstmt = kopo11_conn.prepareStatement(kopo11_QueryTxt);

		// 파일 불러오기
		File kopo11_f = new File("resource/StockDailyPrice.csv");
		// BufferedReader로 불러온 파일 읽기
		BufferedReader kopo11_br = new BufferedReader(new FileReader(kopo11_f));

		String kopo11_readtxt; 									  // string 변수 생성

		int kopo11_LineCnt = 0; 								  // int LineCnt 변수에 0 지정
		int errorCnt = 1; 										  // int errorCnt 변수에 1 지정
		kopo11_conn.setAutoCommit(false);						  // 자동 커밋 false
		long kopo11_startTime = System.currentTimeMillis();		  // 시작 시간 기록

		while ((kopo11_readtxt = kopo11_br.readLine()) != null) { // 첫줄이 null이 아니면 실행
			String[] kopo11_field = kopo11_readtxt.split(",");	  // 배열에 ,단위로 저장
			// 단축코드,일자, 시가,고가,저가,종가,거래량,거래대금
			kopo11_pstmt.setString(1, kopo11_field[2]);			  // 2번째 값 set
			kopo11_pstmt.setString(2, kopo11_field[1]);			  // 1번째 값 set
			kopo11_pstmt.setString(3, kopo11_field[4]);			  // 4번째 값 set
			kopo11_pstmt.setString(4, kopo11_field[5]); 		  // 5번째 값 set
			kopo11_pstmt.setString(5, kopo11_field[6]); 		  // 6번째 값 set
			kopo11_pstmt.setString(6, kopo11_field[3]);			  // 3번째 값 set
			kopo11_pstmt.setString(7, kopo11_field[11]);		  // 11번째 값 set
			kopo11_pstmt.setString(8, kopo11_field[12]);		  // 12번째 값 set
			kopo11_pstmt.addBatch(); 							  // Batch에 더해놓기 
			kopo11_pstmt.clearParameters();						  // parameter clear
			kopo11_LineCnt++; 									  // 1 추가 
			try {
				if (kopo11_LineCnt % 1000 == 0) {				  // LineCnt % 1000 나머지 값이  0 일 때 실행 
					kopo11_pstmt.executeBatch();				  // Batch 저장 값 실행 
					kopo11_conn.commit();						  // 커밋
				}
				if (kopo11_LineCnt % 100000 == 0) { // LineCnt % 100000 나머지 값이 0 일 때 실행
					// 10만건당 안내문구 print
					System.out.printf("%d번째 항목 addBatch OK\n", kopo11_LineCnt);
				}
			} catch (Exception e) {  // 예외처리
				// 예외문구 프린트
				System.out.printf("%d번째 파일 중복 오류가 발생했습니다.\n",errorCnt);
				e.printStackTrace(); 
				errorCnt++; // 에러 카운트 1추가 
			}
		}
		try {
			kopo11_pstmt.executeBatch();					// Batch 저장 값 실행
		} catch (Exception e) {
			e.printStackTrace();							// 예외처리 프린트
		}
		kopo11_conn.commit();								// 커밋
		kopo11_conn.setAutoCommit(true);					// 자동 커밋 true
		long kopo11_endTime = System.currentTimeMillis();	// 종료 시간 기록

		System.out.println("파일 적재가 완료되었습니다.");  						// 안내 문구 print
		System.out.printf("Insert End\n");											// 안내 문구 print
		System.out.printf("total   : %d\n", kopo11_LineCnt);						// 총 LineCnt 수 print 
		System.out.printf("time    : %dms\n", kopo11_endTime - kopo11_startTime);	// 시작부터 종료까지 걸린 초

		System.out.printf("%s\n", "적재 종료 시간 : " + kopo11_Time.format(kopo11_current.getTime())); // 종료 출력

		kopo11_br.close();		// BufferedReader 종료
		kopo11_pstmt.close();	// PreparedStatement 종료
	}
}

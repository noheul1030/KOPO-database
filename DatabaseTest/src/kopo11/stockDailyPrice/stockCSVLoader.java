package kopo11.stockDailyPrice;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class stockCSVLoader {
	public static void main(String[] args) {
		String dbUrl = "jdbc:mysql://192.168.23.60:3307/kopo11"; // DB 연결 URL
		String dbUsername = "root"; 							 // 사용자 ID
		String dbPassword = "shdmf1030@";								 // 사용자 password
		List<String> csvFilePaths = new ArrayList<>(); 			 // csvFilePaths 리스트생성
		// 리스트에 csv파일들 더하기 
		csvFilePaths.add("C:\\Users\\노을\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_00.csv");
		csvFilePaths.add("C:\\Users\\노을\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_01.csv");
		csvFilePaths.add("C:\\Users\\노을\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_02.csv");
		csvFilePaths.add("C:\\Users\\노을\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_03.csv");
		csvFilePaths.add("C:\\Users\\노을\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_04.csv");
		csvFilePaths.add("C:\\Users\\노을\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_05.csv");
		csvFilePaths.add("C:\\Users\\노을\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_06.csv");
		csvFilePaths.add("C:\\Users\\노을\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_07.csv");
		csvFilePaths.add("C:\\Users\\노을\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_08.csv");
		csvFilePaths.add("C:\\Users\\노을\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_09.csv");
		csvFilePaths.add("C:\\Users\\노을\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_10.csv");
		csvFilePaths.add("C:\\Users\\노을\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_11.csv");

		ExecutorService executorService = Executors.newFixedThreadPool(12); // ExecutorService 객체 생성
		// 12개의 Thread를 돌린다.
		
		SimpleDateFormat kopo11_Time = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss"); 	// 날짜, 시간 형식 객체 생성
		Calendar kopo11_current = Calendar.getInstance(); 								// 출력시간
		System.out.printf("%s\n", "적재 시작 시간 : " + kopo11_Time.format(kopo11_current.getTime())); // 시작 출력

		System.out.println("CSV 파일 적재를 시작합니다."); // 적재시작 문구 print

		for (String csvFilePath : csvFilePaths) { // csvFilePaths의 값들을 하나씩 가져오는 반복문
			CSVLoader csvLoader = new CSVLoader(csvFilePath, dbUrl, dbUsername, dbPassword); // CSVLoader 생성
			executorService.execute(csvLoader);   // ExecutorService(CSVLoader 클래스) 실행
		}
		executorService.shutdown(); 			  // ExecutorService 종료
		
	}
}

class CSVLoader implements Runnable {

	private String csvFilePath; // csvFilePath 변수 선언
	private String dbUrl;		// dbUrl 변수 선언
	private String dbUsername;  // dbUsername 변수 선언
	private String dbPassword;  // dbPassword 변수 선언

	// 생성자 
	public CSVLoader(String csvFilePath, String dbUrl, String dbUsername, String dbPassword) {
		this.csvFilePath = csvFilePath;
		this.dbUrl = dbUrl;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
	}

	@Override
	public void run() { // 실행 메서드
		// csvFilePath bufferedReader로 읽기
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath)); 
				// mysql에 연결될 나의 IP:포워딩 포트번호/database이름,DB사용자,DB비밀번호
				Connection kopo11_conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
				// PreparedStatement 객체에 stockDailyPrice 필드 형태 지정 값은 ? 지정
				PreparedStatement kopo11_pstmt = kopo11_conn.prepareStatement(
						"insert into stockDailyPrice (stockCODE,Daily,opening_price,high_price,low_price,closing_price,trading_volume,trading_amount) "
								+ "values (?,?,?,?,?,?,?,?)")) {
			
			String kopo11_readtxt; 										  // String 변수 선언
			
			int kopo11_LineCnt = 0; 									  // int 변수에 0 대입
			kopo11_conn.setAutoCommit(false); 							  // 자동 커밋 false
			long kopo11_startTime = System.currentTimeMillis(); 		  // 시작 시간 기록
			System.out.println(csvFilePath + " 파일 적재를 시작합니다."); // 적재 시작 파일 문구 print
			while ((kopo11_readtxt = br.readLine()) != null) { 			  // 첫줄이 null이 아니면 실행

				String[] kopo11_field = kopo11_readtxt.split(","); // 배열에 ,단위로 저장 
				kopo11_pstmt.setString(1, kopo11_field[2]); 	   // 2번째 값 set
				kopo11_pstmt.setString(2, kopo11_field[1]); 	   // 1번째 값 set
				kopo11_pstmt.setString(3, kopo11_field[4]); 	   // 4번째 값 set
				kopo11_pstmt.setString(4, kopo11_field[5]); 	   // 5번째 값 set
				kopo11_pstmt.setString(5, kopo11_field[6]); 	   // 6번째 값 set
				kopo11_pstmt.setString(6, kopo11_field[3]); 	   // 3번째 값 set
				kopo11_pstmt.setString(7, kopo11_field[11]); 	   // 11번째 값 set
				kopo11_pstmt.setString(8, kopo11_field[12]); 	   // 12번째 값 set
				kopo11_pstmt.addBatch(); 						   // Batch에 더해놓기 
				kopo11_LineCnt++; 								   // 1 추가 
				try {
					if (kopo11_LineCnt % 1000 == 0) {  // LineCnt % 1000 나머지 값이  0 일 때 실행 
						kopo11_pstmt.executeBatch();   // Batch 저장 값 실행 
						kopo11_conn.commit();		   // 커밋			
					}
					if(kopo11_LineCnt % 100000 == 0) { // LineCnt % 100000 나머지 값이  0 일 때 실행 
						// 10만건당 안내문구 print
						System.out.printf("%d번째 항목 addBatch OK\n", kopo11_LineCnt);						
					}
				} catch (Exception e) { 	// 예외처리
					e.printStackTrace();    // 예외문구 프린트
					System.out.println(csvFilePath + "파일에서 오류 발생했습니다.");
					kopo11_conn.rollback(); // Connection rollback
				}
			}
			try {
				kopo11_pstmt.executeBatch(); // Batch 저장 값 실행 
			} catch (Exception e) {
				e.printStackTrace(); 		 // 예외문구 프린트
			}
			// 마지막으로 남은 배치를 실행
			kopo11_pstmt.executeBatch(); 	 // Batch 저장 값 실행 
			kopo11_conn.commit();			 // 커밋

			long kopo11_endTime = System.currentTimeMillis();  // 종료 시간 기록

			System.out.println(csvFilePath + " 파일 적재가 완료되었습니다."); 			// 안내 문구 print
			System.out.printf("Insert End\n");											// 안내 문구 print
			System.out.printf("total   : %d\n", kopo11_LineCnt);						// 총 LineCnt 수 print 
			System.out.printf("time    : %dms\n", kopo11_endTime - kopo11_startTime); 	// 시작부터 종료까지 걸린 초

			kopo11_pstmt.close();   // PreparedStatement 종료
			br.close();				// BufferedReader 종료
			kopo11_conn.close();	// Connection 종료 
		} catch (Exception e) {		// 예외처리
			e.printStackTrace();	// 예외문구 프린트
		}
		
		SimpleDateFormat kopo11_Time = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss"); // 날짜, 시간 형식 객체 생성
		Calendar kopo11_current = Calendar.getInstance(); // 출력시간
		System.out.printf("%s\n", "적재 종료 시간 : " + kopo11_Time.format(kopo11_current.getTime())); // 종료 출력
	}
}


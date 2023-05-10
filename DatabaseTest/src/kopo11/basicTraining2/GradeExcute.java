package kopo11.basicTraining2;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class GradeExcute {
	private static int dataCount = 1000; // 입력할 데이터의 갯수

	public static int random() { // 랜덤 메서드
		return new Random().nextInt(101); // 0~100 까지의 숫자 중 랜덤으로 리턴한다.
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// JDBC 버전에 따라 com.mysql.cj.jdbc.Driver를 사용
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql에 연결될 나의 IP:포워딩 포트번호/database이름,DB사용자,DB비밀번호
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11", "root",
				"shdmf1030@");
		
//		inputData(kopo11_conn); 			 // 1. 데이터DB입력 메서드
//		Individual_performance(kopo11_conn); // 2. 개인별 성적출력 메서드
		pageDataPrint(kopo11_conn); 		 // 3. page당,누적 출력 메서드

		kopo11_conn.close(); // Connection 객체 종료
	}

	// 1. 데이터DB입력 메서드
	public static void inputData(Connection kopo11_conn) throws ClassNotFoundException, SQLException, IOException {
		// sql 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt = kopo11_conn.createStatement();
		kopo11_stmt.execute("delete from GradeList;"); // 테이블 field 데이터 삭제(테이블은 남겨둔다.)

		String kopo11_QueryTxt; // String 변수 선언
		String kopo11_name = "홍길동"; // string 변수 값 "홍길동" 지정
		int kopo11_count = 1; // int 변수 값 0 지정

		while (kopo11_count <= dataCount) { // 데이터 입력 갯수 만큼 도는 반복문
			int kopo11_kor = random(); // kor 변수에 랜덤값 지정
			int kopo11_eng = random(); // eng 변수에 랜덤값 지정
			int kopo11_mat = random(); // mat 변수에 랜덤값 지정

			if (kopo11_count < 10) { // count가 10 미만이면
				// kopo11_QueryTxt 변수에 쿼리 실행문 대입 (개인 취향.. 홍길동01,홍길동02,홍길동03으로 만들고 싶어서 넣은 조건문)
				kopo11_QueryTxt = String.format(
						"insert into GradeList (studentID,name,kor,eng,mat) values ('%s','%s','%s','%s','%s');",
						kopo11_count, kopo11_name + "0" + kopo11_count, kopo11_kor, kopo11_eng, kopo11_mat);
				// 항목의 insert 진행중인 count와 현황 print 개행
				System.out.printf("%d번째 항목 Insert OK [%s]\n", kopo11_count, kopo11_QueryTxt);
				kopo11_count++; // count 1 추가
			} else { // count가 10 미만이 아니면
				// kopo11_QueryTxt 변수에 쿼리 실행문 대입
				kopo11_QueryTxt = String.format(
						"insert into GradeList (studentID,name,kor,eng,mat) values ('%s','%s','%s','%s','%s');",
						kopo11_count, kopo11_name + kopo11_count, kopo11_kor, kopo11_eng, kopo11_mat);
				// 항목의 insert 진행중인 count와 현황 print 개행
				System.out.printf("%d번째 항목 Insert OK [%s]\n", kopo11_count, kopo11_QueryTxt);
				kopo11_count++; // count 1 추가
			}
			// 문자열이 완성된 값으로 쿼리 실행
			kopo11_stmt.execute(kopo11_QueryTxt);
		}
	}

	// 2. 개인별 성적출력 메서드
	public static void Individual_performance(Connection kopo11_conn) throws NumberFormatException, SQLException {
		// sql 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt = kopo11_conn.createStatement();
		// Individual_performance 변수에 쿼리 실행문 대입
		String kopo11_Individual_performance = String.format(
				"SELECT studentid, name, kor, eng, mat, (kor + eng + mat) AS sum, FLOOR((kor + eng + mat)/3) AS avg FROM GradeList");
		// 쿼리를 실행한 결과값을 저장하는 변수 생성
		ResultSet kopo11_QuaryTxt1 = kopo11_stmt.executeQuery(kopo11_Individual_performance);

		SimpleDateFormat kopo11_sdf = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss"); // 날짜, 시간 형식 객체 생성
		Calendar kopo11_current = Calendar.getInstance(); // 출력시간
		
		System.out.printf("\n%33s\n", "성적 집계표"); // 머리말
		System.out.printf("%66s\n", "출력일자 : " + kopo11_sdf.format(kopo11_current.getTime())); // 출력일자
		// ***라인 출력개행
		System.out.printf("***********************************************************************\n");
		System.out.printf(" %4s%8s%8s%8s%8s%8s%8s\n", "학번", "이름", "국어", "영어", "수학", "합계", "평균"); // 출력 개행
		// ***라인 출력개행
		System.out.printf("***********************************************************************\n");
		
		while ((kopo11_QuaryTxt1.next())) { // 쿼리 결과값을 하나씩 꺼내오는 반복문
			System.out.println(); // 개행
			System.out.printf("%5s번\t", kopo11_QuaryTxt1.getString(1)); // kopo11_rset 1 번째 값 출력 개행
			System.out.printf("%9s\t", kopo11_QuaryTxt1.getString(2)); // kopo11_rset 2 번째 값 출력 개행
			System.out.printf("%2d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(3))); // kopo11_rset 3 번째 출력 개행
			System.out.printf("%4d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(4))); // kopo11_rset 4 번째 출력 개행
			System.out.printf("%6d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(5))); // kopo11_rset 5 번째 출력 개행
			System.out.printf("%8d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(6))); // kopo11_rset 6 번째 출력 개행
			System.out.printf("%2d\n", Integer.parseInt(kopo11_QuaryTxt1.getString(7))); // kopo11_rset 7 번째 출력 개행
		}
		System.out.printf("***********************************************************************\n");
	}

	// 3. page당,누적 출력 메서드
	public static void pageDataPrint(Connection kopo11_conn) throws NumberFormatException, SQLException {
		int kopo11_count = 0; // int 변수 0 지정
		int kopo11_pageCount = 0; // int 변수 0 지정

		// 현재 페이지 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt1 = kopo11_conn.createStatement();
		// 현재 페이지 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt2 = kopo11_conn.createStatement();
		// 누적 페이지 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt3 = kopo11_conn.createStatement();

		while (kopo11_count < dataCount) { // 0 ~ 1000까지 도는 반복문
			
			// Individual_performance 변수에 쿼리 실행문 대입 // 개인 합계,평균 
			String kopo11_Individual_performance = String.format(
					"SELECT studentid, name, kor, eng, mat, (kor + eng + mat) AS sum, FLOOR((kor + eng + mat)/3) AS avg FROM GradeList limit %d, 30",
					kopo11_count);
			// pageDataPrint1 변수에 쿼리 실행문 대입 // 현재 페이지당 과목 합계,평균 입력
			String kopo11_pageDataPrint1 = String.format(
					"select sum(a.kor), sum(a.eng), sum(a.mat), sum(a.kor+a.eng+a.mat), sum((a.kor+a.eng+a.mat)/3), "
							+ "avg(a.kor), avg(a.eng), avg(a.mat), avg(a.kor+a.eng+a.mat), avg((a.kor+a.eng+a.mat)/3)  "
							+ "from (SELECT *,kor+eng+mat as totalsum,(kor+eng+mat)/3 as totalavg FROM GradeList limit %d, 30) as a;",
					kopo11_count);
			// pageDataPrint2 변수에 쿼리 실행문 대입 // 누적 페이지당 과목 합계,평균 입력
			String kopo11_pageDataPrint2 = String.format(
					"select sum(a.kor), sum(a.eng), sum(a.mat), sum(a.kor+a.eng+a.mat), sum((a.kor+a.eng+a.mat)/3), "
							+ "avg(a.kor), avg(a.eng), avg(a.mat), avg(a.kor+a.eng+a.mat), avg((a.kor+a.eng+a.mat)/3)  "
							+ "from (SELECT *,kor+eng+mat as totalsum,(kor+eng+mat)/3 as totalavg FROM GradeList limit 0, %d) as a;",
					kopo11_count + 30);

			// 쿼리를 실행한 결과값을 저장하는 변수 생성
			ResultSet kopo11_QuaryTxt1 = kopo11_stmt1.executeQuery(kopo11_Individual_performance);
			// 학생 30명의 기존 정보+합계+평균에 대한 합계와 평균결과값을 저장하는 변수 생성
			ResultSet kopo11_QuaryTxt2 = kopo11_stmt2.executeQuery(kopo11_pageDataPrint1);
			// 누적되는 정보에 대한 합계와 평균결과값을 저장하는 변수 생성
			ResultSet kopo11_QuaryTxt3 = kopo11_stmt3.executeQuery(kopo11_pageDataPrint2);

			if (kopo11_count % 30 == 0) { // count의 30 나머지 값이 0일 때 
				headPrint(kopo11_pageCount); // headPrint 실행
				kopo11_pageCount++; // pageCount 1 추가 
			}
			while ((kopo11_QuaryTxt1.next())) {  // 쿼리 결과값을 하나씩 꺼내오는 반복문
				System.out.println(); // 개행
				System.out.printf("%5s번\t", kopo11_QuaryTxt1.getString(1)); // kopo11_rset 1 번째 값 출력 개행
				System.out.printf("%9s\t", kopo11_QuaryTxt1.getString(2)); // kopo11_rset 2 번째 값 출력 개행
				System.out.printf("%2d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(3))); // kopo11_rset 3 번째 출력 개행
				System.out.printf("%4d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(4))); // kopo11_rset 4 번째 출력 개행
				System.out.printf("%6d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(5))); // kopo11_rset 5 번째 출력 개행
				System.out.printf("%8d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(6))); // kopo11_rset 6 번째 출력 개행
				System.out.printf("%2d\n", Integer.parseInt(kopo11_QuaryTxt1.getString(7))); // kopo11_rset 7 번째 출력 개행
			}
			linePrint(kopo11_QuaryTxt2); // 합계 페이지 합계, 평균 출력
			totalPrint(kopo11_QuaryTxt3); // 누적 페이지 합계, 평균 출력
			kopo11_count += 30; // count 30씩 누적 더하기
		}
		kopo11_stmt1.close(); // Statement 객체 종료
		kopo11_stmt2.close(); // Statement 객체 종료
		kopo11_stmt3.close(); // Statement 객체 종료

	}

	public static void headPrint(int pageCount) {
		SimpleDateFormat kopo11_sdf = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss"); // 날짜, 시간 형식 객체 생성
		Calendar kopo11_current = Calendar.getInstance(); // 출력시간
		System.out.printf("\n%33s\n", "성적 집계표"); // 포멧 출력 개행
		// 현재페이지,출력일자
		System.out.printf("%s%56s\n", "PAGE : " + (pageCount + 1), "출력일자 : " + kopo11_sdf.format(kopo11_current.getTime())); 

		// ***라인 출력개행
		System.out.printf("***********************************************************************\n");
		System.out.printf(" %4s%8s%8s%8s%8s%8s%8s\n", "학번", "이름", "국어", "영어", "수학", "합계", "평균"); // 포멧 출력 개행
		// ***라인 출력개행
		System.out.printf("***********************************************************************\n");
	}

	public static void linePrint(ResultSet QuaryTxt2) throws SQLException {
		// ***라인 출력개행
		System.out.printf("***********************************************************************\n");
		System.out.printf("현재 페이지\n"); // 출력 개행
		while (QuaryTxt2.next()) { // 현재 데이터 쿼리
			System.out.printf("합계: %20d%10d%10d%10d%10d\n", QuaryTxt2.getInt(1), QuaryTxt2.getInt(2),
					QuaryTxt2.getInt(3), QuaryTxt2.getInt(4), QuaryTxt2.getInt(5)); // 현재 페이지 합계 정보 출력 개행
			System.out.printf("평균: %20d%10d%10d%10d%10d\n", QuaryTxt2.getInt(6), QuaryTxt2.getInt(7),
					QuaryTxt2.getInt(8), QuaryTxt2.getInt(9), QuaryTxt2.getInt(10)); // 현재 페이지 평균 정보 출력 개행
		}
	}

	public static void totalPrint(ResultSet QuaryTxt3) throws SQLException {
		// ***라인 출력개행
		System.out.printf("***********************************************************************\n");
		System.out.printf("누적 페이지\n");
		while (QuaryTxt3.next()) { // 누적 데이터 쿼리 
			System.out.printf("합계: %20d%10d%10d%10d%10d\n", QuaryTxt3.getInt(1), QuaryTxt3.getInt(2),
					QuaryTxt3.getInt(3), QuaryTxt3.getInt(4), QuaryTxt3.getInt(5)); // 누적합계 정보 출력 개행
			System.out.printf("평균: %20d%10d%10d%10d%10d\n", QuaryTxt3.getInt(6), QuaryTxt3.getInt(7),
					QuaryTxt3.getInt(8), QuaryTxt3.getInt(9), QuaryTxt3.getInt(10)); // 누적평균 정보 출력 개행
			// ***라인 출력개행
			System.out.printf("***********************************************************************\n");
		}
	}
}

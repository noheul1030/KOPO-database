package kopo11.freeWifi;

import java.sql.*;
import java.io.*;
public class wifi2 {

	// Class를 찾지 못할 경우 오류발생처리 , SQL오류 발생처리, IOE 오류 처리
	public static void main(String[] args)throws ClassNotFoundException, SQLException, IOException {
		// JDBC 버전에 따라 com.mysql.cj.jdbc.Driver를 사용 
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql에 연결될 나의 IP:포워딩 포트번호/database이름,DB사용자,DB비밀번호
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql 쿼리를 실행하기 위한 객체 변수 생성
		Statement kopo11_stmt = kopo11_conn.createStatement();
		
		// resource/전국무료와이파이표준데이터.txt File 불러 오기 
		File kopo11_f = new File("resource/전국무료와이파이표준데이터.txt");
		// 불러온 파일을 BufferedReaderfh 읽는다.
		BufferedReader kopo11_br = new BufferedReader(new FileReader(kopo11_f));
		
		String kopo11_readtxt; // String 변수 선언
		
		// BufferedReader로 한줄씩 읽는 kopo11_readtxt 값이 null이면 
		if((kopo11_readtxt = kopo11_br.readLine())==null) {
			System.out.printf("빈 파일입니다.\n"); // 안내 문구 출력 개행
			return; // 리턴 
		}
		
		// kopo11_readtxt의 내용을 \t기준으로 split해서 String 배열에 담는다.
		String[] kopo11_field_name = kopo11_readtxt.split("\t");
		
		int kopo11_LineCnt = 0; // int 변수에 0값 대입
		
		// 실습 1,2
		// BufferedReader로 한줄씩 읽는 kopo11_readtxt 값이 null이 아니면 도는 반복문 
		while((kopo11_readtxt = kopo11_br.readLine())!=null) {
			// kopo11_readtxt의 내용을 \t기준으로 split해서 String 배열에 담는다.
			String[] kopo11_field = kopo11_readtxt.split("\t");
			
			String kopo11_QueryTxt; // String 변수 선언
			
			// 원하는 string형태로 포멧한 값을 kopo11_QueryTxt에 저장한다.
			// kopo11_field의 [0]~[15]째 까지 이어 붙인 문자열을 kopo11_QueryTxt에 저장			 
			kopo11_QueryTxt = String.format("insert into freewifi ("
					+ "number,inst_place,inst_place_detail,inst_city,inst_country,inst_place_flag,"
					+ "service_provider,wifi_ssid,inst_date,place_addr_road,place_addr_land,"
					+ "manage_office,manage_office_phone,latitude,longitude,write_date)"
					+ "values ("
					+ "'%s','%s','%s','%s','%s','%s',"
					+ "'%s','%s','%s','%s','%s',"
					+ "'%s','%s','%s','%s','%s');",
					kopo11_field[0],kopo11_field[1],kopo11_field[2],kopo11_field[3],kopo11_field[4],kopo11_field[5],
					kopo11_field[6],kopo11_field[7],kopo11_field[8],kopo11_field[9],kopo11_field[10],
					kopo11_field[11],kopo11_field[12],kopo11_field[13],kopo11_field[14],kopo11_field[15]);
			
			// 문자열이 완성된 값으로 쿼리 실행
			kopo11_stmt.execute(kopo11_QueryTxt);
			// 항목의 insert 진행중인 count와 현황 print 개행
			System.out.printf("%d번째 항목 Insert OK [%s]\n",kopo11_LineCnt,kopo11_QueryTxt);
			
			kopo11_LineCnt++; // kopo11_LineCnt 1 추가 	
		}	
		kopo11_br.close(); // BufferedReader 객체 종료
		kopo11_stmt.close(); // Statement 객체 종료
		kopo11_conn.close();  // Connection 객체 종료
	}
}

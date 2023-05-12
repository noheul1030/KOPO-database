package kopo11.stockDailyPrice;

import java.io.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class stockExcute {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ���
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11", "root",
				"shdmf1030@");

//		inputdata(kopo11_conn); // 0. ������ �Է� �޼��� 
//		Samsung(kopo11_conn); 	// 1. �Ｚ���� ���� ���� �޼���
		DateCheck(kopo11_conn); // 2. Ư������ ���� �޼���

		kopo11_conn.close(); // Connection ����

	}

	// 1. �Ｚ���� ���� ���� �޼���
	public static void Samsung(Connection kopo11_conn) throws NumberFormatException, SQLException {
		// DecimalFormat ��ü ����
		DecimalFormat kopo11_df = new DecimalFormat("###,###,###,###,###,###,###");

		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		// Individual_performance ������ ���� ���๮ ����
		String kopo11_stockCODE = String.format("SELECT * FROM stockDailyPrice where stockCODE = 'A005930'");
		// ������ ������ ������� �����ϴ� ���� ����
		ResultSet kopo11_QuaryTxt1 = kopo11_stmt.executeQuery(kopo11_stockCODE);

		int kopo11_count = 0; 							  // int ���� 0 ����

		while ((kopo11_QuaryTxt1.next())) { // ���� ������� �ϳ��� �������� �ݺ���
			System.out.printf("\n%15s\n", "�Ｚ���� �ְ� ����ǥ"); // �Ӹ���
			// ***���� ��°���
			System.out.printf("*****************************\n");
			// kopo11_QuaryTxt1 1 ��° �� ��� ����
			System.out.printf("�����ڵ�  : %s\n", kopo11_QuaryTxt1.getString(1));
			// kopo11_QuaryTxt1 2 ��° �� ��� ����
			System.out.printf("��  ��    : %s\n", kopo11_QuaryTxt1.getString(2));
			// kopo11_QuaryTxt1 3 ��° �� ��� ����
			System.out.printf("��  ��    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(3)));
			// kopo11_QuaryTxt1 4 ��° �� ��� ����
			System.out.printf("��  ��    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(4)));
			// kopo11_QuaryTxt1 5 ��° �� ��� ����)
			System.out.printf("��  ��    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(5)));
			// kopo11_QuaryTxt1 6 ��° �� ��� ����
			System.out.printf("��  ��    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(6)));
			// kopo11_QuaryTxt1 7 ��° �� ��� ����
			System.out.printf("�ŷ���    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(7)));
			// kopo11_QuaryTxt1 8 ��° �� ��� ����
			System.out.printf("�ŷ����  : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getLong(8)));
			kopo11_count++; // 1 �߰�
			// ***���� ��°���
			System.out.printf("*****************************\n");
		}
		System.out.printf("�Ｚ���� �����ڵ� ���� : %d", kopo11_count); // �Ｚ���� count ���
		kopo11_stmt.close(); // Statement ��ü ����
	}

	// 2. Ư������ ���� �޼���
	public static void DateCheck(Connection kopo11_conn) throws NumberFormatException, SQLException {
		// DecimalFormat ��ü ����
		DecimalFormat kopo11_df = new DecimalFormat("###,###,###,###,###,###,###");

		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		// Individual_performance ������ ���� ���๮ ����
		String kopo11_stockCODE = String.format("SELECT * FROM stockDailyPrice where Daily = '20150112'");
		// ������ ������ ������� �����ϴ� ���� ����
		ResultSet kopo11_QuaryTxt1 = kopo11_stmt.executeQuery(kopo11_stockCODE);

		int kopo11_count = 0; 							  // int ���� 0 ����

		while ((kopo11_QuaryTxt1.next())) { // ���� ������� �ϳ��� �������� �ݺ���
			System.out.printf("%20s\n", "2015�� 01�� 12�� ���� ���� ����ǥ"); // �Ӹ���
			// ***���� ��°���
			System.out.printf("**********************************\n");
			// kopo11_QuaryTxt1 1 ��° �� ��� ����
			System.out.printf("�����ڵ�  : %s\n", kopo11_QuaryTxt1.getString(1));
			// kopo11_QuaryTxt1 3 ��° �� ��� ����
			System.out.printf("��  ��    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(3)));
			// kopo11_QuaryTxt1 4 ��° �� ��� ����
			System.out.printf("��  ��    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(4)));
			// kopo11_QuaryTxt1 5 ��° �� ��� ����
			System.out.printf("��  ��    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(5)));
			// kopo11_QuaryTxt1 6 ��° �� ��� ����
			System.out.printf("��  ��    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(6)));
			// kopo11_QuaryTxt1 7 ��° �� ��� ����
			System.out.printf("�ŷ���    : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getInt(7)));
			// kopo11_QuaryTxt1 8 ��° �� ��� ����
			System.out.printf("�ŷ����  : %s\n", kopo11_df.format(kopo11_QuaryTxt1.getLong(8)));
			kopo11_count++; // 1 �߰�
			// ***���� ��°���
			System.out.printf("**********************************\n");
		}
		System.out.printf("2015�� 01�� 12�� ���� ���� ���� : %d", kopo11_count); // ���� ���� ���� count ���
		kopo11_stmt.close(); // Statement ��ü ����
	}
	
	// 0. ������ �Է� �޼���
	public static void inputdata(Connection kopo11_conn) throws SQLException, IOException {
//		kopo11_pstmt.execute("delete from stockDailyPrice;"); // ���̺� field ������ ����(���̺��� ���ܵд�.)
		SimpleDateFormat kopo11_Time = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss"); // ��¥, �ð� ���� ��ü ����
		Calendar kopo11_current = Calendar.getInstance(); // ��½ð�
		System.out.printf("%s\n", "���� ���� �ð� : " + kopo11_Time.format(kopo11_current.getTime())); // ���� ���
		System.out.println("CSV ���� ���縦 �����մϴ�."); // ������� ���� print
		
		// kopo11_QueryTxt�� stockDailyPrice �ʵ� ���� ���� ���� ? ����
		String kopo11_QueryTxt = "insert into stockDailyPrice (stockCODE,Daily,opening_price,high_price,low_price,closing_price,trading_volume,trading_amount)"
				+ "values (?,?,?,?,?,?,?,?)";
		// PreparedStatement ��ü ����
		PreparedStatement kopo11_pstmt = kopo11_conn.prepareStatement(kopo11_QueryTxt);

		// ���� �ҷ�����
		File kopo11_f = new File("resource/StockDailyPrice.csv");
		// BufferedReader�� �ҷ��� ���� �б�
		BufferedReader kopo11_br = new BufferedReader(new FileReader(kopo11_f));

		String kopo11_readtxt; 									  // string ���� ����

		int kopo11_LineCnt = 0; 								  // int LineCnt ������ 0 ����
		int errorCnt = 1; 										  // int errorCnt ������ 1 ����
		kopo11_conn.setAutoCommit(false);						  // �ڵ� Ŀ�� false
		long kopo11_startTime = System.currentTimeMillis();		  // ���� �ð� ���

		while ((kopo11_readtxt = kopo11_br.readLine()) != null) { // ù���� null�� �ƴϸ� ����
			String[] kopo11_field = kopo11_readtxt.split(",");	  // �迭�� ,������ ����
			// �����ڵ�,����, �ð�,��,����,����,�ŷ���,�ŷ����
			kopo11_pstmt.setString(1, kopo11_field[2]);			  // 2��° �� set
			kopo11_pstmt.setString(2, kopo11_field[1]);			  // 1��° �� set
			kopo11_pstmt.setString(3, kopo11_field[4]);			  // 4��° �� set
			kopo11_pstmt.setString(4, kopo11_field[5]); 		  // 5��° �� set
			kopo11_pstmt.setString(5, kopo11_field[6]); 		  // 6��° �� set
			kopo11_pstmt.setString(6, kopo11_field[3]);			  // 3��° �� set
			kopo11_pstmt.setString(7, kopo11_field[11]);		  // 11��° �� set
			kopo11_pstmt.setString(8, kopo11_field[12]);		  // 12��° �� set
			kopo11_pstmt.addBatch(); 							  // Batch�� ���س��� 
			kopo11_pstmt.clearParameters();						  // parameter clear
			kopo11_LineCnt++; 									  // 1 �߰� 
			try {
				if (kopo11_LineCnt % 1000 == 0) {				  // LineCnt % 1000 ������ ����  0 �� �� ���� 
					kopo11_pstmt.executeBatch();				  // Batch ���� �� ���� 
					kopo11_conn.commit();						  // Ŀ��
				}
				if (kopo11_LineCnt % 100000 == 0) { // LineCnt % 100000 ������ ���� 0 �� �� ����
					// 10���Ǵ� �ȳ����� print
					System.out.printf("%d��° �׸� addBatch OK\n", kopo11_LineCnt);
				}
			} catch (Exception e) {  // ����ó��
				// ���ܹ��� ����Ʈ
				System.out.printf("%d��° ���� �ߺ� ������ �߻��߽��ϴ�.\n",errorCnt);
				e.printStackTrace(); 
				errorCnt++; // ���� ī��Ʈ 1�߰� 
			}
		}
		try {
			kopo11_pstmt.executeBatch();					// Batch ���� �� ����
		} catch (Exception e) {
			e.printStackTrace();							// ����ó�� ����Ʈ
		}
		kopo11_conn.commit();								// Ŀ��
		kopo11_conn.setAutoCommit(true);					// �ڵ� Ŀ�� true
		long kopo11_endTime = System.currentTimeMillis();	// ���� �ð� ���

		System.out.println("���� ���簡 �Ϸ�Ǿ����ϴ�.");  						// �ȳ� ���� print
		System.out.printf("Insert End\n");											// �ȳ� ���� print
		System.out.printf("total   : %d\n", kopo11_LineCnt);						// �� LineCnt �� print 
		System.out.printf("time    : %dms\n", kopo11_endTime - kopo11_startTime);	// ���ۺ��� ������� �ɸ� ��

		System.out.printf("%s\n", "���� ���� �ð� : " + kopo11_Time.format(kopo11_current.getTime())); // ���� ���

		kopo11_br.close();		// BufferedReader ����
		kopo11_pstmt.close();	// PreparedStatement ����
	}
}

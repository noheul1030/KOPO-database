package kopo11.stockDailyPrice;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class stockCSVLoader {
	public static void main(String[] args) {
		String dbUrl = "jdbc:mysql://192.168.23.60:3307/kopo11"; // DB ���� URL
		String dbUsername = "root"; 							 // ����� ID
		String dbPassword = "shdmf1030@";								 // ����� password
		List<String> csvFilePaths = new ArrayList<>(); 			 // csvFilePaths ����Ʈ����
		// ����Ʈ�� csv���ϵ� ���ϱ� 
		csvFilePaths.add("C:\\Users\\����\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_00.csv");
		csvFilePaths.add("C:\\Users\\����\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_01.csv");
		csvFilePaths.add("C:\\Users\\����\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_02.csv");
		csvFilePaths.add("C:\\Users\\����\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_03.csv");
		csvFilePaths.add("C:\\Users\\����\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_04.csv");
		csvFilePaths.add("C:\\Users\\����\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_05.csv");
		csvFilePaths.add("C:\\Users\\����\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_06.csv");
		csvFilePaths.add("C:\\Users\\����\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_07.csv");
		csvFilePaths.add("C:\\Users\\����\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_08.csv");
		csvFilePaths.add("C:\\Users\\����\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_09.csv");
		csvFilePaths.add("C:\\Users\\����\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_10.csv");
		csvFilePaths.add("C:\\Users\\����\\Documents\\GitHub\\KOPO-database\\DatabaseTest\\txt\\StockDaily_11.csv");

		ExecutorService executorService = Executors.newFixedThreadPool(12); // ExecutorService ��ü ����
		// 12���� Thread�� ������.
		
		SimpleDateFormat kopo11_Time = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss"); 	// ��¥, �ð� ���� ��ü ����
		Calendar kopo11_current = Calendar.getInstance(); 								// ��½ð�
		System.out.printf("%s\n", "���� ���� �ð� : " + kopo11_Time.format(kopo11_current.getTime())); // ���� ���

		System.out.println("CSV ���� ���縦 �����մϴ�."); // ������� ���� print

		for (String csvFilePath : csvFilePaths) { // csvFilePaths�� ������ �ϳ��� �������� �ݺ���
			CSVLoader csvLoader = new CSVLoader(csvFilePath, dbUrl, dbUsername, dbPassword); // CSVLoader ����
			executorService.execute(csvLoader);   // ExecutorService(CSVLoader Ŭ����) ����
		}
		executorService.shutdown(); 			  // ExecutorService ����
		
	}
}

class CSVLoader implements Runnable {

	private String csvFilePath; // csvFilePath ���� ����
	private String dbUrl;		// dbUrl ���� ����
	private String dbUsername;  // dbUsername ���� ����
	private String dbPassword;  // dbPassword ���� ����

	// ������ 
	public CSVLoader(String csvFilePath, String dbUrl, String dbUsername, String dbPassword) {
		this.csvFilePath = csvFilePath;
		this.dbUrl = dbUrl;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
	}

	@Override
	public void run() { // ���� �޼���
		// csvFilePath bufferedReader�� �б�
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath)); 
				// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
				Connection kopo11_conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
				// PreparedStatement ��ü�� stockDailyPrice �ʵ� ���� ���� ���� ? ����
				PreparedStatement kopo11_pstmt = kopo11_conn.prepareStatement(
						"insert into stockDailyPrice (stockCODE,Daily,opening_price,high_price,low_price,closing_price,trading_volume,trading_amount) "
								+ "values (?,?,?,?,?,?,?,?)")) {
			
			String kopo11_readtxt; 										  // String ���� ����
			
			int kopo11_LineCnt = 0; 									  // int ������ 0 ����
			kopo11_conn.setAutoCommit(false); 							  // �ڵ� Ŀ�� false
			long kopo11_startTime = System.currentTimeMillis(); 		  // ���� �ð� ���
			System.out.println(csvFilePath + " ���� ���縦 �����մϴ�."); // ���� ���� ���� ���� print
			while ((kopo11_readtxt = br.readLine()) != null) { 			  // ù���� null�� �ƴϸ� ����

				String[] kopo11_field = kopo11_readtxt.split(","); // �迭�� ,������ ���� 
				kopo11_pstmt.setString(1, kopo11_field[2]); 	   // 2��° �� set
				kopo11_pstmt.setString(2, kopo11_field[1]); 	   // 1��° �� set
				kopo11_pstmt.setString(3, kopo11_field[4]); 	   // 4��° �� set
				kopo11_pstmt.setString(4, kopo11_field[5]); 	   // 5��° �� set
				kopo11_pstmt.setString(5, kopo11_field[6]); 	   // 6��° �� set
				kopo11_pstmt.setString(6, kopo11_field[3]); 	   // 3��° �� set
				kopo11_pstmt.setString(7, kopo11_field[11]); 	   // 11��° �� set
				kopo11_pstmt.setString(8, kopo11_field[12]); 	   // 12��° �� set
				kopo11_pstmt.addBatch(); 						   // Batch�� ���س��� 
				kopo11_LineCnt++; 								   // 1 �߰� 
				try {
					if (kopo11_LineCnt % 1000 == 0) {  // LineCnt % 1000 ������ ����  0 �� �� ���� 
						kopo11_pstmt.executeBatch();   // Batch ���� �� ���� 
						kopo11_conn.commit();		   // Ŀ��			
					}
					if(kopo11_LineCnt % 100000 == 0) { // LineCnt % 100000 ������ ����  0 �� �� ���� 
						// 10���Ǵ� �ȳ����� print
						System.out.printf("%d��° �׸� addBatch OK\n", kopo11_LineCnt);						
					}
				} catch (Exception e) { 	// ����ó��
					e.printStackTrace();    // ���ܹ��� ����Ʈ
					System.out.println(csvFilePath + "���Ͽ��� ���� �߻��߽��ϴ�.");
					kopo11_conn.rollback(); // Connection rollback
				}
			}
			try {
				kopo11_pstmt.executeBatch(); // Batch ���� �� ���� 
			} catch (Exception e) {
				e.printStackTrace(); 		 // ���ܹ��� ����Ʈ
			}
			// ���������� ���� ��ġ�� ����
			kopo11_pstmt.executeBatch(); 	 // Batch ���� �� ���� 
			kopo11_conn.commit();			 // Ŀ��

			long kopo11_endTime = System.currentTimeMillis();  // ���� �ð� ���

			System.out.println(csvFilePath + " ���� ���簡 �Ϸ�Ǿ����ϴ�."); 			// �ȳ� ���� print
			System.out.printf("Insert End\n");											// �ȳ� ���� print
			System.out.printf("total   : %d\n", kopo11_LineCnt);						// �� LineCnt �� print 
			System.out.printf("time    : %dms\n", kopo11_endTime - kopo11_startTime); 	// ���ۺ��� ������� �ɸ� ��

			kopo11_pstmt.close();   // PreparedStatement ����
			br.close();				// BufferedReader ����
			kopo11_conn.close();	// Connection ���� 
		} catch (Exception e) {		// ����ó��
			e.printStackTrace();	// ���ܹ��� ����Ʈ
		}
		
		SimpleDateFormat kopo11_Time = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss"); // ��¥, �ð� ���� ��ü ����
		Calendar kopo11_current = Calendar.getInstance(); // ��½ð�
		System.out.printf("%s\n", "���� ���� �ð� : " + kopo11_Time.format(kopo11_current.getTime())); // ���� ���
	}
}


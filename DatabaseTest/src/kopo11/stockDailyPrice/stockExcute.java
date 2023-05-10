package kopo11.stockDailyPrice;

import java.io.*;
import java.sql.*;
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

		inputdata(kopo11_conn);
		kopo11_conn.close();

	}
	
	public static void inputdata(Connection kopo11_conn) throws SQLException, IOException {
		String kopo11_QueryTxt = "insert into stockDailyPrice (stockCODE,Daily,opening_price,high_price,low_price,closing_price,trading_volume,trading_amount)"
				+ "values (?,?,?,?,?,?,?,?)";
		PreparedStatement kopo11_pstmt = kopo11_conn.prepareStatement(kopo11_QueryTxt);

		kopo11_pstmt.execute("delete from stockDailyPrice;"); // ���̺� field ������ ����(���̺��� ���ܵд�.)

		File kopo11_f = new File("resource/StockDailyPrice.csv");
		BufferedReader kopo11_br = new BufferedReader(new FileReader(kopo11_f));

		String kopo11_readtxt;

		if ((kopo11_readtxt = kopo11_br.readLine()) == null) {
			System.out.printf("�� �����Դϴ�.\n");
			return;
		}
		String[] kopo11_field_name = kopo11_readtxt.split(",");

		int kopo11_LineCnt = 0;
		kopo11_conn.setAutoCommit(false);
		long kopo11_startTime = System.currentTimeMillis();

		while ((kopo11_readtxt = kopo11_br.readLine()) != null) {
			String[] kopo11_field = kopo11_readtxt.split(",");
			// �����ڵ�,����, �ð�,��,����,����,�ŷ���,�ŷ����
			kopo11_pstmt.setString(1, kopo11_field[2]);
			kopo11_pstmt.setString(2, kopo11_field[1]);
			kopo11_pstmt.setString(3, kopo11_field[4]);
			kopo11_pstmt.setString(4, kopo11_field[5]);
			kopo11_pstmt.setString(5, kopo11_field[6]);
			kopo11_pstmt.setString(6, kopo11_field[3]);
			kopo11_pstmt.setString(7, kopo11_field[11]);
			kopo11_pstmt.setString(8, kopo11_field[12]);
			kopo11_pstmt.addBatch();
			kopo11_pstmt.clearParameters();
			kopo11_LineCnt++;
			try {
				if (kopo11_LineCnt % 10000 == 0) {
					System.out.printf("%d��° �׸� addBatch OK\n", kopo11_LineCnt);
					kopo11_pstmt.executeBatch();
					kopo11_conn.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			kopo11_pstmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		kopo11_conn.commit();
		kopo11_conn.setAutoCommit(true);
		long kopo11_endTime = System.currentTimeMillis();
		
		System.out.printf("Insert End\n");
		System.out.printf("total   : %d\n",kopo11_LineCnt);
		System.out.printf("time    : %dms\n",kopo11_endTime-kopo11_startTime);
		
		kopo11_br.close();
		kopo11_pstmt.close();
	}
}

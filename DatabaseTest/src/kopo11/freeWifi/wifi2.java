package kopo11.freeWifi;

import java.sql.*;
import java.io.*;
public class wifi2 {

	// Class�� ã�� ���� ��� �����߻�ó�� , SQL���� �߻�ó��, IOE ���� ó��
	public static void main(String[] args)throws ClassNotFoundException, SQLException, IOException {
		// JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ��� 
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
				,"root","shdmf1030@");
		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		
		// resource/���������������ǥ�ص�����.txt File �ҷ� ���� 
		File kopo11_f = new File("resource/���������������ǥ�ص�����.txt");
		// �ҷ��� ������ BufferedReaderfh �д´�.
		BufferedReader kopo11_br = new BufferedReader(new FileReader(kopo11_f));
		
		String kopo11_readtxt; // String ���� ����
		
		// BufferedReader�� ���پ� �д� kopo11_readtxt ���� null�̸� 
		if((kopo11_readtxt = kopo11_br.readLine())==null) {
			System.out.printf("�� �����Դϴ�.\n"); // �ȳ� ���� ��� ����
			return; // ���� 
		}
		
		// kopo11_readtxt�� ������ \t�������� split�ؼ� String �迭�� ��´�.
		String[] kopo11_field_name = kopo11_readtxt.split("\t");
		
		int kopo11_LineCnt = 0; // int ������ 0�� ����
		
		// �ǽ� 1,2
		// BufferedReader�� ���پ� �д� kopo11_readtxt ���� null�� �ƴϸ� ���� �ݺ��� 
		while((kopo11_readtxt = kopo11_br.readLine())!=null) {
			// kopo11_readtxt�� ������ \t�������� split�ؼ� String �迭�� ��´�.
			String[] kopo11_field = kopo11_readtxt.split("\t");
			
			String kopo11_QueryTxt; // String ���� ����
			
			// ���ϴ� string���·� ������ ���� kopo11_QueryTxt�� �����Ѵ�.
			// kopo11_field�� [0]~[15]° ���� �̾� ���� ���ڿ��� kopo11_QueryTxt�� ����			 
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
			
			// ���ڿ��� �ϼ��� ������ ���� ����
			kopo11_stmt.execute(kopo11_QueryTxt);
			// �׸��� insert �������� count�� ��Ȳ print ����
			System.out.printf("%d��° �׸� Insert OK [%s]\n",kopo11_LineCnt,kopo11_QueryTxt);
			
			kopo11_LineCnt++; // kopo11_LineCnt 1 �߰� 	
		}	
		kopo11_br.close(); // BufferedReader ��ü ����
		kopo11_stmt.close(); // Statement ��ü ����
		kopo11_conn.close();  // Connection ��ü ����
	}
}

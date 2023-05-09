package kopo11.freeWifi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class wifi3 {

	// Class�� ã�� ���� ��� �����߻�ó�� , SQL���� �߻�ó��, IOE ���� ó��
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ���
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11", "root",
				"shdmf1030@");
		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		
		double kopo11_lat = 37.3860521; // ����
		double kopo11_lng = 127.1214038; // �浵
		
		String kopo11_QueryTxt;// String ���� ����
		// ���ϴ� string���·� ������ ���� kopo11_QueryTxt�� �����Ѵ�.
		// ����,�浵�� ������ �ִܰŸ� ���ϴ� ������ 
		kopo11_QueryTxt = String.format("select * from freewifi where " 
				+ " SQRT(POWER(latitude-%f,2) + POWER(longitude-%f,2))="
				+ "(select MIN(SQRT(POWER(latitude-%f,2) + POWER(longitude-%f,2)))from freewifi);",
				kopo11_lat,kopo11_lng,kopo11_lat,kopo11_lng);
		
		// freewifi�� ��� ������ ��ȸ�ϴ�  ������ 
//		kopo11_QueryTxt = "select * from freewifi";
		// freewifi�� service_provider field���� 'SKT'�� ��ȸ�ϴ�  ������ 
//		kopo11_QueryTxt = "select * from freewifi where service_provider = 'SKT'";
		// freewifi�� service_country field���� '������'�� ��ȸ�ϴ�  ������ 
//		kopo11_QueryTxt = "select * from freewifi where service_country = '������'";
		
		// ������ ������ ������� �����ϴ� ���� ����(ȣ�� �� �� show databases�� ������ش�.)
		ResultSet kopo11_rset = kopo11_stmt.executeQuery(kopo11_QueryTxt);
		int kopo11_iCnt = 0; // int ������ 0�� ����
		
		// ���� ���� ��� ���� �����ִ� �ݺ���(���� ������ ���ð� ��� false�� �Ǹ� �ݺ��� ����)
		while((kopo11_rset.next())) {
			// ��,***���� ��� ����
			System.out.printf("*(%s��)*******************************************\n",kopo11_rset.getString(1)); 
			System.out.printf("��ġ��Ҹ�               : %s\n",kopo11_rset.getString(2)); // kopo11_rset 1 ��° �� ��� ����
			System.out.printf("��ġ��һ�             : %s\n",kopo11_rset.getString(3)); // kopo11_rset 2 ��° �� ��� ����
			System.out.printf("��ġ�õ���               : %s\n",kopo11_rset.getString(4)); // kopo11_rset 3 ��° �� ��� ����
			System.out.printf("��ġ�ñ�����             : %s\n",kopo11_rset.getString(5)); // kopo11_rset 4 ��° �� ��� ����
			System.out.printf("��ġ�ü�����             : %s\n",kopo11_rset.getString(6)); // kopo11_rset 5 ��° �� ��� ����
			System.out.printf("�����������           : %s\n",kopo11_rset.getString(7)); // kopo11_rset 6 ��° �� ��� ����
			System.out.printf("��������SSID             : %s\n",kopo11_rset.getString(8)); // kopo11_rset 7 ��° �� ��� ����
			System.out.printf("��ġ���                 : %s\n",kopo11_rset.getString(9)); // kopo11_rset 8 ��° �� ��� ����
			System.out.printf("���������θ��ּ�         : %s\n",kopo11_rset.getString(10)); // kopo11_rset 9 ��° �� ��� ����
			System.out.printf("�����������ּ�           : %s\n",kopo11_rset.getString(11)); // kopo11_rset 10 ��° �� ��� ����
			System.out.printf("���������               : %s\n",kopo11_rset.getString(12)); // kopo11_rset 11 ��° �� ��� ����
			System.out.printf("���������ȭ��ȣ         : %s\n",kopo11_rset.getString(13)); // kopo11_rset 12 ��° �� ��� ����
			// kopo11_rset 13 ��° �� ��� ����
			System.out.printf("����                     : %f\n",Double.parseDouble(kopo11_rset.getString(14))); 
			// kopo11_rset 14 ��° �� ��� ����
			System.out.printf("�浵                     : %f\n",Double.parseDouble(kopo11_rset.getString(15))); 
			System.out.printf("�����ͱ�������           : %s\n",kopo11_rset.getString(16)); // kopo11_rset 15 ��° �� ��� ����
			System.out.printf("****************************************************\n");// ***���� ��� ����
			
		}
		kopo11_rset.close(); // ResultSet ��ü ����
		kopo11_stmt.close(); // Statement ��ü ����
		kopo11_conn.close();  // Connection ��ü ����
	}
	

}

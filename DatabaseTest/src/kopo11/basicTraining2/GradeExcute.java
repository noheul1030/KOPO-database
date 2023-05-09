package kopo11.basicTraining2;

import java.io.*;
import java.sql.*;
import java.util.Random;

public class GradeExcute {
	public static int random() {
		return new Random().nextInt(101);
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ���
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11", "root",
				"shdmf1030@");
		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		kopo11_stmt.execute("delete from GradeList;");

		/*
		 * // resource/���������������ǥ�ص�����.txt File �ҷ� ���� File kopo11_f = new
		 * File("resource/gradeSave.csv"); // �ҷ��� ������ BufferedReaderfh �д´�.
		 * BufferedReader kopo11_br = new BufferedReader(new FileReader(kopo11_f));
		 * 
		 * String kopo11_readtxt; // String ���� ����
		 * 
		 * // BufferedReader�� ���پ� �д� kopo11_readtxt ���� null�̸� if ((kopo11_readtxt =
		 * kopo11_br.readLine()) == null) { System.out.printf("�� �����Դϴ�.\n"); // �ȳ� ���� ���
		 * ���� return; // ���� }
		 * 
		 * // kopo11_readtxt�� ������ \t�������� split�ؼ� String �迭�� ��´�. String[]
		 * kopo11_field_name = kopo11_readtxt.split(",");
		 * 
		 * int kopo11_LineCnt = 0; // int ������ 0�� ����
		 */
		
		String kopo11_minimumTxt;// String ���� ����
//		// ���ϴ� string���·� ������ ���� kopo11_QueryTxt�� �����Ѵ�.
//		// ����,�浵�� ������ �ִܰŸ� ���ϴ� ������ 
//		kopo11_minimumTxt = String.format("select * from ParkingStation where " 
//				+ " SQRT(POWER(latitude-%f,2) + POWER(longitude-%f,2))="
//				+ "(select MIN(SQRT(POWER(latitude-%f,2) + POWER(longitude-%f,2)))from ParkingStation);",
//				kopo11_lat,kopo11_lng,kopo11_lat,kopo11_lng);
//		
//		// ������ ������ ������� �����ϴ� ���� ����(ȣ�� �� �� show databases�� ������ش�.)
//		ResultSet kopo11_rset = kopo11_stmt.executeQuery(kopo11_minimumTxt);
//		
//		while((kopo11_rset.next())) {
//			// ��,***���� ��� ����
//			System.out.printf("*(ID Number %s��)*********************************\n",kopo11_rset.getString(1)); 
//			System.out.printf("���������               : %s\n",kopo11_rset.getString(2)); // kopo11_rset 1 ��° �� ��� ����
//			System.out.printf("��ġ��������             : %s\n",kopo11_rset.getString(3)); // kopo11_rset 2 ��° �� ��� ����
//			System.out.printf("���������θ��ּ�         : %s\n",kopo11_rset.getString(4)); // kopo11_rset 3 ��° �� ��� ����
//			System.out.printf("�����������ּ�           : %s\n",kopo11_rset.getString(5)); // kopo11_rset 4 ��° �� ��� ����
//			System.out.printf("ī�޶���               : %d\n",Integer.parseInt(kopo11_rset.getString(6))); // kopo11_rset 5 ��° �� ��� ����
//			System.out.printf("ī�޶�ȭ��               : %d\n",Integer.parseInt(kopo11_rset.getString(7))); // kopo11_rset 6 ��° �� ��� ����
//			System.out.printf("�Կ��������             : %s\n",kopo11_rset.getString(8)); // kopo11_rset 7 ��° �� ��� ����
//			System.out.printf("�����ϼ�                 : %d\n",Integer.parseInt(kopo11_rset.getString(9))); // kopo11_rset 8 ��° �� ��� ����
//			System.out.printf("��ġ���                 : %s\n",kopo11_rset.getString(10)); // kopo11_rset 9 ��° �� ��� ����
//			System.out.printf("���������ȭ��ȣ         : %s\n",kopo11_rset.getString(11)); // kopo11_rset 10 ��° �� ��� ����
//			// kopo11_rset 12 ��° �� ��� ����
//			System.out.printf("����                     : %f\n",Double.parseDouble(kopo11_rset.getString(12))); 
//			// kopo11_rset 13 ��° �� ��� ����
//			System.out.printf("�浵                     : %f\n",Double.parseDouble(kopo11_rset.getString(13))); 
//			System.out.printf("�����ͱ�������           : %s\n",kopo11_rset.getString(14)); // kopo11_rset 14 ��° �� ��� ����
//			System.out.printf("****************************************************\n");// ***���� ��� ����
//			
//		}
		
		
		//// ������DB�Է��ڵ�///////////////////////////////////////////////////////////////////////////////

		String kopo11_QueryTxt; // String ���� ����
		String name = "ȫ�浿";
		int count = 0;

		// ���ϴ� string���·� ������ ���� kopo11_QueryTxt�� �����Ѵ�.
		// kopo11_field�� [0]~[15]° ���� �̾� ���� ���ڿ��� kopo11_QueryTxt�� ����
		while (count < 1000) {
			int kor = random();
			int eng = random();
			int mat = random();

			if (count < 10) {
				kopo11_QueryTxt = String.format(
						"insert into GradeList (studentID,name,kor,eng,mat,sum,avg) values ('%s','%s','%s','%s','%s','%s','%s');",
						count, name + "0" + count, kor, eng, mat, kor + eng + mat, (kor + eng + mat) / 3);
				// �׸��� insert �������� count�� ��Ȳ print ����
				System.out.printf("%d��° �׸� Insert OK [%s]\n", count, kopo11_QueryTxt);
				count++;
			} else {
				kopo11_QueryTxt = String.format(
						"insert into GradeList (studentID,name,kor,eng,mat,sum,avg) values ('%s','%s','%s','%s','%s','%s','%s');",
						count, name + count, kor, eng, mat, kor + eng + mat, (kor + eng + mat) / 3);
				// �׸��� insert �������� count�� ��Ȳ print ����
				System.out.printf("%d��° �׸� Insert OK [%s]\n", count, kopo11_QueryTxt);
				count++;
			}
			// ���ڿ��� �ϼ��� ������ ���� ����
			kopo11_stmt.execute(kopo11_QueryTxt);
		}
		kopo11_stmt.close(); // Statement ��ü ����
		kopo11_conn.close(); // Connection ��ü ����
	}

}

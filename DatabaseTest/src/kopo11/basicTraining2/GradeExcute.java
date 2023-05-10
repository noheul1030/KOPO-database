package kopo11.basicTraining2;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class GradeExcute {
	private static int dataCount = 1000; // �Է��� �������� ����

	public static int random() { // ���� �޼���
		return new Random().nextInt(101); // 0~100 ������ ���� �� �������� �����Ѵ�.
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ���
		Class.forName("com.mysql.cj.jdbc.Driver");
		// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
		Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11", "root",
				"shdmf1030@");
		
//		inputData(kopo11_conn); 			 // 1. ������DB�Է� �޼���
//		Individual_performance(kopo11_conn); // 2. ���κ� ������� �޼���
		pageDataPrint(kopo11_conn); 		 // 3. page��,���� ��� �޼���

		kopo11_conn.close(); // Connection ��ü ����
	}

	// 1. ������DB�Է� �޼���
	public static void inputData(Connection kopo11_conn) throws ClassNotFoundException, SQLException, IOException {
		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		kopo11_stmt.execute("delete from GradeList;"); // ���̺� field ������ ����(���̺��� ���ܵд�.)

		String kopo11_QueryTxt; // String ���� ����
		String kopo11_name = "ȫ�浿"; // string ���� �� "ȫ�浿" ����
		int kopo11_count = 1; // int ���� �� 0 ����

		while (kopo11_count <= dataCount) { // ������ �Է� ���� ��ŭ ���� �ݺ���
			int kopo11_kor = random(); // kor ������ ������ ����
			int kopo11_eng = random(); // eng ������ ������ ����
			int kopo11_mat = random(); // mat ������ ������ ����

			if (kopo11_count < 10) { // count�� 10 �̸��̸�
				// kopo11_QueryTxt ������ ���� ���๮ ���� (���� ����.. ȫ�浿01,ȫ�浿02,ȫ�浿03���� ����� �; ���� ���ǹ�)
				kopo11_QueryTxt = String.format(
						"insert into GradeList (studentID,name,kor,eng,mat) values ('%s','%s','%s','%s','%s');",
						kopo11_count, kopo11_name + "0" + kopo11_count, kopo11_kor, kopo11_eng, kopo11_mat);
				// �׸��� insert �������� count�� ��Ȳ print ����
				System.out.printf("%d��° �׸� Insert OK [%s]\n", kopo11_count, kopo11_QueryTxt);
				kopo11_count++; // count 1 �߰�
			} else { // count�� 10 �̸��� �ƴϸ�
				// kopo11_QueryTxt ������ ���� ���๮ ����
				kopo11_QueryTxt = String.format(
						"insert into GradeList (studentID,name,kor,eng,mat) values ('%s','%s','%s','%s','%s');",
						kopo11_count, kopo11_name + kopo11_count, kopo11_kor, kopo11_eng, kopo11_mat);
				// �׸��� insert �������� count�� ��Ȳ print ����
				System.out.printf("%d��° �׸� Insert OK [%s]\n", kopo11_count, kopo11_QueryTxt);
				kopo11_count++; // count 1 �߰�
			}
			// ���ڿ��� �ϼ��� ������ ���� ����
			kopo11_stmt.execute(kopo11_QueryTxt);
		}
	}

	// 2. ���κ� ������� �޼���
	public static void Individual_performance(Connection kopo11_conn) throws NumberFormatException, SQLException {
		// sql ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt = kopo11_conn.createStatement();
		// Individual_performance ������ ���� ���๮ ����
		String kopo11_Individual_performance = String.format(
				"SELECT studentid, name, kor, eng, mat, (kor + eng + mat) AS sum, FLOOR((kor + eng + mat)/3) AS avg FROM GradeList");
		// ������ ������ ������� �����ϴ� ���� ����
		ResultSet kopo11_QuaryTxt1 = kopo11_stmt.executeQuery(kopo11_Individual_performance);

		SimpleDateFormat kopo11_sdf = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss"); // ��¥, �ð� ���� ��ü ����
		Calendar kopo11_current = Calendar.getInstance(); // ��½ð�
		
		System.out.printf("\n%33s\n", "���� ����ǥ"); // �Ӹ���
		System.out.printf("%66s\n", "������� : " + kopo11_sdf.format(kopo11_current.getTime())); // �������
		// ***���� ��°���
		System.out.printf("***********************************************************************\n");
		System.out.printf(" %4s%8s%8s%8s%8s%8s%8s\n", "�й�", "�̸�", "����", "����", "����", "�հ�", "���"); // ��� ����
		// ***���� ��°���
		System.out.printf("***********************************************************************\n");
		
		while ((kopo11_QuaryTxt1.next())) { // ���� ������� �ϳ��� �������� �ݺ���
			System.out.println(); // ����
			System.out.printf("%5s��\t", kopo11_QuaryTxt1.getString(1)); // kopo11_rset 1 ��° �� ��� ����
			System.out.printf("%9s\t", kopo11_QuaryTxt1.getString(2)); // kopo11_rset 2 ��° �� ��� ����
			System.out.printf("%2d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(3))); // kopo11_rset 3 ��° ��� ����
			System.out.printf("%4d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(4))); // kopo11_rset 4 ��° ��� ����
			System.out.printf("%6d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(5))); // kopo11_rset 5 ��° ��� ����
			System.out.printf("%8d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(6))); // kopo11_rset 6 ��° ��� ����
			System.out.printf("%2d\n", Integer.parseInt(kopo11_QuaryTxt1.getString(7))); // kopo11_rset 7 ��° ��� ����
		}
		System.out.printf("***********************************************************************\n");
	}

	// 3. page��,���� ��� �޼���
	public static void pageDataPrint(Connection kopo11_conn) throws NumberFormatException, SQLException {
		int kopo11_count = 0; // int ���� 0 ����
		int kopo11_pageCount = 0; // int ���� 0 ����

		// ���� ������ ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt1 = kopo11_conn.createStatement();
		// ���� ������ ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt2 = kopo11_conn.createStatement();
		// ���� ������ ������ �����ϱ� ���� ��ü ���� ����
		Statement kopo11_stmt3 = kopo11_conn.createStatement();

		while (kopo11_count < dataCount) { // 0 ~ 1000���� ���� �ݺ���
			
			// Individual_performance ������ ���� ���๮ ���� // ���� �հ�,��� 
			String kopo11_Individual_performance = String.format(
					"SELECT studentid, name, kor, eng, mat, (kor + eng + mat) AS sum, FLOOR((kor + eng + mat)/3) AS avg FROM GradeList limit %d, 30",
					kopo11_count);
			// pageDataPrint1 ������ ���� ���๮ ���� // ���� �������� ���� �հ�,��� �Է�
			String kopo11_pageDataPrint1 = String.format(
					"select sum(a.kor), sum(a.eng), sum(a.mat), sum(a.kor+a.eng+a.mat), sum((a.kor+a.eng+a.mat)/3), "
							+ "avg(a.kor), avg(a.eng), avg(a.mat), avg(a.kor+a.eng+a.mat), avg((a.kor+a.eng+a.mat)/3)  "
							+ "from (SELECT *,kor+eng+mat as totalsum,(kor+eng+mat)/3 as totalavg FROM GradeList limit %d, 30) as a;",
					kopo11_count);
			// pageDataPrint2 ������ ���� ���๮ ���� // ���� �������� ���� �հ�,��� �Է�
			String kopo11_pageDataPrint2 = String.format(
					"select sum(a.kor), sum(a.eng), sum(a.mat), sum(a.kor+a.eng+a.mat), sum((a.kor+a.eng+a.mat)/3), "
							+ "avg(a.kor), avg(a.eng), avg(a.mat), avg(a.kor+a.eng+a.mat), avg((a.kor+a.eng+a.mat)/3)  "
							+ "from (SELECT *,kor+eng+mat as totalsum,(kor+eng+mat)/3 as totalavg FROM GradeList limit 0, %d) as a;",
					kopo11_count + 30);

			// ������ ������ ������� �����ϴ� ���� ����
			ResultSet kopo11_QuaryTxt1 = kopo11_stmt1.executeQuery(kopo11_Individual_performance);
			// �л� 30���� ���� ����+�հ�+��տ� ���� �հ�� ��հ������ �����ϴ� ���� ����
			ResultSet kopo11_QuaryTxt2 = kopo11_stmt2.executeQuery(kopo11_pageDataPrint1);
			// �����Ǵ� ������ ���� �հ�� ��հ������ �����ϴ� ���� ����
			ResultSet kopo11_QuaryTxt3 = kopo11_stmt3.executeQuery(kopo11_pageDataPrint2);

			if (kopo11_count % 30 == 0) { // count�� 30 ������ ���� 0�� �� 
				headPrint(kopo11_pageCount); // headPrint ����
				kopo11_pageCount++; // pageCount 1 �߰� 
			}
			while ((kopo11_QuaryTxt1.next())) {  // ���� ������� �ϳ��� �������� �ݺ���
				System.out.println(); // ����
				System.out.printf("%5s��\t", kopo11_QuaryTxt1.getString(1)); // kopo11_rset 1 ��° �� ��� ����
				System.out.printf("%9s\t", kopo11_QuaryTxt1.getString(2)); // kopo11_rset 2 ��° �� ��� ����
				System.out.printf("%2d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(3))); // kopo11_rset 3 ��° ��� ����
				System.out.printf("%4d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(4))); // kopo11_rset 4 ��° ��� ����
				System.out.printf("%6d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(5))); // kopo11_rset 5 ��° ��� ����
				System.out.printf("%8d\t", Integer.parseInt(kopo11_QuaryTxt1.getString(6))); // kopo11_rset 6 ��° ��� ����
				System.out.printf("%2d\n", Integer.parseInt(kopo11_QuaryTxt1.getString(7))); // kopo11_rset 7 ��° ��� ����
			}
			linePrint(kopo11_QuaryTxt2); // �հ� ������ �հ�, ��� ���
			totalPrint(kopo11_QuaryTxt3); // ���� ������ �հ�, ��� ���
			kopo11_count += 30; // count 30�� ���� ���ϱ�
		}
		kopo11_stmt1.close(); // Statement ��ü ����
		kopo11_stmt2.close(); // Statement ��ü ����
		kopo11_stmt3.close(); // Statement ��ü ����

	}

	public static void headPrint(int pageCount) {
		SimpleDateFormat kopo11_sdf = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss"); // ��¥, �ð� ���� ��ü ����
		Calendar kopo11_current = Calendar.getInstance(); // ��½ð�
		System.out.printf("\n%33s\n", "���� ����ǥ"); // ���� ��� ����
		// ����������,�������
		System.out.printf("%s%56s\n", "PAGE : " + (pageCount + 1), "������� : " + kopo11_sdf.format(kopo11_current.getTime())); 

		// ***���� ��°���
		System.out.printf("***********************************************************************\n");
		System.out.printf(" %4s%8s%8s%8s%8s%8s%8s\n", "�й�", "�̸�", "����", "����", "����", "�հ�", "���"); // ���� ��� ����
		// ***���� ��°���
		System.out.printf("***********************************************************************\n");
	}

	public static void linePrint(ResultSet QuaryTxt2) throws SQLException {
		// ***���� ��°���
		System.out.printf("***********************************************************************\n");
		System.out.printf("���� ������\n"); // ��� ����
		while (QuaryTxt2.next()) { // ���� ������ ����
			System.out.printf("�հ�: %20d%10d%10d%10d%10d\n", QuaryTxt2.getInt(1), QuaryTxt2.getInt(2),
					QuaryTxt2.getInt(3), QuaryTxt2.getInt(4), QuaryTxt2.getInt(5)); // ���� ������ �հ� ���� ��� ����
			System.out.printf("���: %20d%10d%10d%10d%10d\n", QuaryTxt2.getInt(6), QuaryTxt2.getInt(7),
					QuaryTxt2.getInt(8), QuaryTxt2.getInt(9), QuaryTxt2.getInt(10)); // ���� ������ ��� ���� ��� ����
		}
	}

	public static void totalPrint(ResultSet QuaryTxt3) throws SQLException {
		// ***���� ��°���
		System.out.printf("***********************************************************************\n");
		System.out.printf("���� ������\n");
		while (QuaryTxt3.next()) { // ���� ������ ���� 
			System.out.printf("�հ�: %20d%10d%10d%10d%10d\n", QuaryTxt3.getInt(1), QuaryTxt3.getInt(2),
					QuaryTxt3.getInt(3), QuaryTxt3.getInt(4), QuaryTxt3.getInt(5)); // �����հ� ���� ��� ����
			System.out.printf("���: %20d%10d%10d%10d%10d\n", QuaryTxt3.getInt(6), QuaryTxt3.getInt(7),
					QuaryTxt3.getInt(8), QuaryTxt3.getInt(9), QuaryTxt3.getInt(10)); // ������� ���� ��� ����
			// ***���� ��°���
			System.out.printf("***********************************************************************\n");
		}
	}
}

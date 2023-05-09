package kopo11.basicTraining1;

import java.sql.*;

public class ParkingStation {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ��� 
				Class.forName("com.mysql.cj.jdbc.Driver");
				// mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
				Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.60:3307/kopo11"
						,"root","shdmf1030@");
				// sql ������ �����ϱ� ���� ��ü ���� ����
				Statement kopo11_stmt = kopo11_conn.createStatement();
				
				// freewifi ���̺� �����ͺ��̽��� ������ �����ϴ� ����
				kopo11_stmt.execute("drop table ParkingStation;");
				
				kopo11_stmt.execute(  "create table ParkingStation(" // sql ���� ���� : ParkingStation ���̺��� ����� 
						+"number int not null,"						 // 1.number field not null ����
						+"manage_office	varchar(50), " 				 // 2.��������� (field ũ�� 50 ����)
						+"installation_purpose	varchar(50), "		 // 3.��ġ�������� (field ũ�� 50 ����)
						+"place_addr_road	varchar(200), "			 // 4.���������θ��ּ� (field ũ�� 200 ����)
						+"place_addr_land	varchar(200), " 		 // 5.�����������ּ� (field ũ�� 200 ����)
						+"camera_count int," 						 // 6.ī�޶���
						+"camera_pixel int," 						 // 7.ī�޶�ȭ��
						+"shooting_angle varchar(200)," 			 // 8.�Կ��������
						+"storage_period int," 						 // 9.�����ϼ�
						+"inst_date	date NULL, " 					 // 10.��ġ���
						+"manage_office_phone	varchar(50), " 		 // 11.���������ȭ��ȣ (field ũ�� 50 ����)
						+"latitude	double, " 						 // 12.���� (field �� double ����)
						+"longitude	double, " 						 // 13.�浵 (field �� double ����)
						+"write_date date not null, " 				 // 14.�����ͱ������� field not null ����
						+"primary key (number, write_date)" 		 // primary key(number,write_date)���� 
						+") DEFAULT CHARSET=utf8;" 					 // UTF-8�� default
		 				);
				
				kopo11_stmt.close(); // Statement ��ü ����
				kopo11_conn.close();  // Connection ��ü ����
	}
}

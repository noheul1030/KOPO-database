package kopo11.freeWifi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class wifi1 {

    // Class�� ã�� ���� ��� �����߻� , SQL���� �߻�
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // JDBC ������ ���� com.mysql.cj.jdbc.Driver�� ���
        Class.forName("com.mysql.cj.jdbc.Driver");
        // mysql�� ����� ���� IP:������ ��Ʈ��ȣ/database�̸�,DB�����,DB��й�ȣ
        Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/noheul", "root",
                "shdmf1030@");
        // sql ������ �����ϱ� ���� ��ü ���� ����
        Statement kopo11_stmt = kopo11_conn.createStatement();

        // freewifi ���̺� �����ͺ��̽��� ������ �����ϴ� ����
        kopo11_stmt.execute("drop table if exists freewifi;");

        kopo11_stmt.execute("create table freewifi(" // sql ���� ���� : freewifi ���̺��� �����
                + "number int not null," // number field not null ����
                + "inst_place	varchar(50), " // ��ġ��Ҹ�(field ũ�� 50 ����)
                + "inst_place_detail	varchar(500), " // ��ġ ��һ� (field ũ�� 200 ����)
                + "inst_city	varchar(50), " // ��ġ �õ��� (field ũ�� 50 ����)
                + "inst_country	varchar(50), " // ��ġ �ñ����� (field ũ�� 50 ����)
                + "inst_place_flag	varchar(50), " // ��ġ �ü����� (field ũ�� 50 ����)
                + "service_provider	varchar(50), " // ���� ������� (field ũ�� 50 ����)
                + "wifi_ssid	varchar(200), " // �������� SSID (field ũ�� 200 ����)
                + "inst_date	date NULL, " // ��ġ��� (�����ؾ��� ����data)
                + "place_addr_road	varchar(200), " // ������ ���θ� �ּ� (field ũ�� 200 ����)
                + "place_addr_land	varchar(200), " // ������ ���� �ּ� (field ũ�� 200 ����)
                + "manage_office	varchar(50), " // ���� ����� (field ũ�� 50 ����)
                + "manage_office_phone	varchar(50), " // ���� ��� ��ȭ��ȣ (field ũ�� 50 ����)
                + "latitude	double, " // ���� (field �� double ����)
                + "longitude	double, " // �浵 (field �� double ����)
                + "write_date date not null, " // ������ �������� field not null ����
                + "primary key (number, write_date)" // primary key(number,write_date)����
                + ") DEFAULT CHARSET=utf8;" // UTF-8�� default
        );

        kopo11_stmt.close(); // Statement ��ü ����
        kopo11_conn.close(); // Connection ��ü ����
    }

}

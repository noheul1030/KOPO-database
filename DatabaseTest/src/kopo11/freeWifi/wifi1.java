package kopo11.freeWifi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class wifi1 {

    // Class를 찾지 못할 경우 오류발생 , SQL오류 발생
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // JDBC 버전에 따라 com.mysql.cj.jdbc.Driver를 사용
        Class.forName("com.mysql.cj.jdbc.Driver");
        // mysql에 연결될 나의 IP:포워딩 포트번호/database이름,DB사용자,DB비밀번호
        Connection kopo11_conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/noheul", "root",
                "shdmf1030@");
        // sql 쿼리를 실행하기 위한 객체 변수 생성
        Statement kopo11_stmt = kopo11_conn.createStatement();

        // freewifi 테이블 데이터베이스를 완전히 삭제하는 쿼리
        kopo11_stmt.execute("drop table if exists freewifi;");

        kopo11_stmt.execute("create table freewifi(" // sql 쿼리 실행 : freewifi 테이블을 만들고
                + "number int not null," // number field not null 지정
                + "inst_place	varchar(50), " // 설치장소명(field 크기 50 지정)
                + "inst_place_detail	varchar(500), " // 설치 장소상세 (field 크기 200 지정)
                + "inst_city	varchar(50), " // 설치 시도명 (field 크기 50 지정)
                + "inst_country	varchar(50), " // 설치 시군구명 (field 크기 50 지정)
                + "inst_place_flag	varchar(50), " // 설치 시설구분 (field 크기 50 지정)
                + "service_provider	varchar(50), " // 서비스 제공사명 (field 크기 50 지정)
                + "wifi_ssid	varchar(200), " // 와이파이 SSID (field 크기 200 지정)
                + "inst_date	date NULL, " // 설치년월 (정제해야할 정보data)
                + "place_addr_road	varchar(200), " // 소재지 도로명 주소 (field 크기 200 지정)
                + "place_addr_land	varchar(200), " // 소재지 지번 주소 (field 크기 200 지정)
                + "manage_office	varchar(50), " // 관리 기관명 (field 크기 50 지정)
                + "manage_office_phone	varchar(50), " // 관리 기관 전화번호 (field 크기 50 지정)
                + "latitude	double, " // 위도 (field 값 double 지정)
                + "longitude	double, " // 경도 (field 값 double 지정)
                + "write_date date not null, " // 데이터 기준일자 field not null 지정
                + "primary key (number, write_date)" // primary key(number,write_date)지정
                + ") DEFAULT CHARSET=utf8;" // UTF-8로 default
        );

        kopo11_stmt.close(); // Statement 객체 종료
        kopo11_conn.close(); // Connection 객체 종료
    }

}

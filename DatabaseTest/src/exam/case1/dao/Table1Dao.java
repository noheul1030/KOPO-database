package exam.case1.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;

import exam.case1.dto.Table1Dto;

public class Table1Dao {
    long beforeTime = System.currentTimeMillis();
    long afterTime = 0;

    public void insertData(Connection con) {
        PreparedStatement pstmt = null;
        BufferedReader br = null;

        try {
            String query = "insert into table1 (id, name) values (?, ?)";
//            String query = "insert into stockDailyPrice (stockCODE,Daily,opening_price,high_price,low_price,closing_price,trading_volume,trading_amount)"
//            + "values (?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(query);
            br = new BufferedReader(new FileReader("filename"));

            String line;
            int loopCount = 0;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                // 컬럼 갯수 체크
                checkColumnLength(columns.length);

                // Dto Set
                Table1Dto dto = setTableDto(columns);

                // Pstmt Set
                setPreparedStatement(pstmt, dto);

                // 대용량쿼리 수행을 위한 임시수행
                pstmt.addBatch();

                // 반복건수가 1000건 마다 쿼리 수행
                if (++loopCount % 1000 == 0) {
                    executeBatch(pstmt, con, loopCount);
                }
            }

            // 미실행된 나머지 쿼리 수행
            executeBatch(pstmt, con, loopCount);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 오류가 발생했거나 커밋되지 않은 데이터는 롤백 수행
                con.rollback();

                if (br != null) {
                    br.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Table1Dto setTableDto(String[] columns) {
        Table1Dto dto = new Table1Dto();
        dto.setId(columns[0]);
        dto.setId(columns[1]);

        return dto;
    }

    private void setPreparedStatement(PreparedStatement pstmt, Table1Dto dto) throws SQLException {
        int cellIndex = 0;
        pstmt.setString(++cellIndex, dto.getId());
        pstmt.setString(++cellIndex, dto.getName());
    }

    private void executeBatch(PreparedStatement pstmt, Connection con, int loopCount) throws SQLException {
        pstmt.executeBatch();
        con.commit();

        printInsertDataCount(loopCount);
    }

    // 누적건수 및 경과시간 출력
    private void printInsertDataCount(int loopCount) {
        this.afterTime = System.currentTimeMillis();
        System.out.println(MessageFormat.format("insert data count = {0} / elapsedTime = {1}", +loopCount,
                afterTime - beforeTime));
        this.beforeTime = this.afterTime;
    }

    private void checkColumnLength(int length) {
        if (length != 10) {
            throw new IllegalArgumentException(MessageFormat.format("컬럼 갯수가 올바르지 않습니다. 컬럼갯수={0}", length));
        }
    }
}
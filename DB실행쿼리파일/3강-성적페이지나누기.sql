use kopo11;

select * from GradeList;
select count(*) from GradeList;

drop procedure if exists print_report;
DELIMITER $$
CREATE PROCEDURE print_report(_page INT, _page_size INT)
BEGIN
    declare _start INT;
    declare _maxpage integer;
    set _start = 0;
    set _maxpage = (select count(*) from GradeList)/_page_size;
    
		if _maxpage >= _page and _page > 0 then
			SET _start = (_page - 1) * _page_size;
		elseif _maxpage < _page then
			SET _start = (_maxpage - 1)* _page_size;
		end if;
	
    SELECT studentID as 번호, name as 이름, kor as 국어, eng as 영어, mat as 수학, kor+eng+mat as 총점, (kor+eng+mat)/3 평균 FROM GradeList
    LIMIT _start, _page_size;
END $$
DELIMITER ;

CALL print_report(1, 25);
SELECT AVG(value) AS average
FROM (
  SELECT value
  FROM YourTable
  LIMIT 100, 25
) AS subquery;
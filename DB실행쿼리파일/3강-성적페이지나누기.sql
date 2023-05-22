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
	
    SELECT studentID as 번호
          , name as 이름
          , kor as 국어
          , eng as 영어
          , mat as 수학
          , kor+eng+mat as 총점
          , (kor+eng+mat)/3 평균 
    FROM GradeList
    LIMIT _start, _page_size;
   

END $$
DELIMITER ;

CALL print_report(6, 25);

## 누적페이지의 합계
SELECT "누적페이지"
      ,''
	  ,''
	  ,''
	  ,''
	  ,''
FROM dual
union all
SELECT '합계' as "누적페이지"
      ,sum(kor) as korsum
	  ,sum(mat) as matsum
	  ,sum(eng) as engsum
	  ,sum((kor+mat+eng)) as allsum
	  ,sum(round(((kor+mat+eng)/3), 1)) as avgsum
FROM gradelist
union all
SELECT '평균' as "누적페이지"
      ,round(avg(kor)) as koravg
	  ,round(avg(mat)) as matavg
	  ,round(avg(eng)) as engavg
	  ,round(avg((kor+mat+eng))) as allavg
	  ,round(avg((kor+mat+eng)/3), 1) as avgavg
FROM gradelist
;



SELECT AVG(value) AS average
FROM (
  SELECT value
  FROM GradeList
  LIMIT 100, 25
) AS subquery;


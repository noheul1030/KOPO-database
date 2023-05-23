use kopo11; # kopo11 

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

CALL print_report(5, 25);

## 현재 페이지의 합계 
select "현재페이지"
		,''
        ,''
        ,''
        ,''
        ,''
from dual
union all
select '합계' as "현재페이지"
		,sum(a.kor) as korsum
		,sum(a.mat) as matsum
		,sum(a.eng) as engsum
		,sum(a.kor+a.mat+a.eng) as allsum
		,ROUND(AVG(a.kor + a.mat + a.eng), 1) AS avgsum
FROM (call print_report(5, 25)) as a;


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
FROM GradeList
union all
SELECT '평균' as "누적페이지"
      ,round(avg(kor)) as koravg
	  ,round(avg(mat)) as matavg
	  ,round(avg(eng)) as engavg
	  ,round(avg((kor+mat+eng))) as allavg
	  ,round(avg((kor+mat+eng)/3), 1) as avgavg
FROM GradeList
;

# 등수 매기기
select  ranking(kor, eng, mat)as 등수, studentID as 학번, name as 이름, kor as 국어, eng as 영어, mat as 수학
 , (kor+eng+mat) as 총점, (kor+eng+mat)/3 as 평균 from GradeList;

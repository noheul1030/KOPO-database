use kopo11; # kopo11 

select * from GradeList; # 테이블 조회
select count(*) from GradeList; # 테이블 전체 행 개수 조회

drop procedure if exists print_report; # 프로시저가 존재하면 삭재
DELIMITER $$
CREATE PROCEDURE print_report(_page int, _page_size int)
BEGIN
    declare _start int;
    declare _maxpage int;
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

# 현재 페이지 테이블
drop procedure if exists print_report2; # 프로시저가 존재하면 삭제
DELIMITER $$ # 딜리미터 선언
CREATE PROCEDURE print_report2(_page int, _page_size int) # 프로시저 생성
BEGIN # 시작
    declare _start int; # int 변수 선언
    declare _maxpage int; # int 변수 선언
    set _start = 0; # 변수 초기값 지정
    # 최대 페이지에 대한 값 계산
    set _maxpage = (select count(*) from GradeList)/_page_size;
		
        # page보다 크거나 같고, 그리고 page가 0보다 크면 true 조건문
		if _maxpage >= _page and _page > 0 then
			SET _start = (_page - 1) * _page_size; # 변수 값 지정
		# maxpage가 page 보다 작으면 true 조건문
		elseif _maxpage < _page then
			SET _start = (_maxpage - 1)* _page_size; # 변수 값 지정
		# page가 0보다 작으면
		elseif _page < 0 then
			set _start = 0 * _page_size; # 변수 값 지정
		end if; # if 문 종료
	
    # 비어있는 임의의 테이블 select
	select "현재페이지",'','','','','' from dual 
    union all # 테이블 모든 값 병합하기
    # 현재 페이지 합계,국어,영어,수학,총합,평균 의 합계 계산
	select '합계' as "현재페이지"
		,sum(kor) as korsum ,sum(eng) as engsum
		,sum(mat) as matsum ,sum(kor+eng+mat) as pagesum
		,round(sum((kor+eng+mat)/3),1) as pageavg 
	# 테이블의 limit만큼 제한 전체 조회
	from (select * from GradeList LIMIT _start, _page_size) as a 
    union all # 테이블 모든 값 병합하기
    # 현재 페이지 합계,국어,영어,수학,총합,평균 의 평균 계산
	select '평균' as "현재페이지"
		,floor(avg(kor)) as koravg ,floor(avg(eng)) as matavg
		,floor(avg(mat)) as engavg ,floor(avg(kor+eng+mat)) as allavg
		,round((avg((kor+eng+mat)/3)),1) as avgavg # 소숫점 0.1자리까지 나오게
	# 테이블의 limit만큼 제한 전체 조회
	FROM (select * from GradeList LIMIT _start, _page_size)as b;
END $$
DELIMITER ;

CALL print_report2(-1, 25); # 함수 호출

# 누적 페이지 테이블
drop procedure if exists print_report3;
DELIMITER $$
CREATE PROCEDURE print_report3(_page INT, _page_size INT) # 프로시저 생성
BEGIN # 시작
	declare _start int; # int 변수 선언
	declare _sumpage int; # int 변수 선언
    declare _maxpage int; # int 변수 선언
    declare _cnt int; # int 변수 선언
    set _start = 0; # 변수 초기값 지정
    # 최대 페이지에 대한 값 계산
    set _maxpage = (select count(*) from GradeList)/_page_size;
		
        # page보다 크거나 같고, 그리고 page가 0보다 크면 true 조건문
		if _maxpage >= _page and _page > 0 then
			SET _start = (_page - 1) * _page_size; # 변수 값 지정
		# maxpage보다 page가 크면 
		elseif _maxpage < _page then
			SET _start = (_maxpage - 1)* _page_size;  # 변수 값 지정
		end if; # if 문 종료
		if _page <= 0 then
			set _sumpage = 1 * _page_size; # 변수 값 지정
		elseif _page > 0 then
			set _sumpage = _page * _page_size; # 변수 값 지정
		end if; # if 문 종료
		
	# 총합누적페이지의 값 계산
	set _sumpage = _page * _page_size;
    
	# 비어있는 임의의 테이블 select
	select "누적페이지",'','','','','' from dual 
	union all # 테이블 모든 값 병합하기
	# 누적 페이지 합계,국어,영어,수학,총합,평균 의 합계 계산
	select '합계' as "누적페이지"
		,sum(kor) as korsum ,sum(eng) as engsum
		,sum(mat) as matsum ,sum(kor+eng+mat) as pagesum
		,round(sum((kor+eng+mat)/3),1) as pageavg 
	# 테이블의 limit만큼 제한 전체 조회
	from (select * from GradeList LIMIT 0, _sumpage) as a 
	union all # 테이블 모든 값 병합하기
	# 누적 페이지 합계,국어,영어,수학,총합,평균 의 평균 계산
	SELECT '평균' as "누적페이지"
		,floor(avg(kor)) as koravg ,floor(avg(eng)) as matavg
		,floor(avg(mat)) as engavg ,floor(avg(kor+eng+mat)) as allavg
		,round((avg((kor+eng+mat)/3)),1) as avgavg # 소숫점 0.1자리까지 나오게
	FROM (select * from GradeList LIMIT 0, _sumpage)as b; # 테이블의 limit만큼 제한 전체 조회
END $$
DELIMITER ;

CALL print_report3(5, 25); # 함수 호출



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
select  studentID as 학번, name as 이름, kor as 국어, eng as 영어, mat as 수학
 , (kor+eng+mat) as 총점, (kor+eng+mat)/3 as 평균 , rank() over(order by kor+eng+mat desc) 
as 등수 from GradeList;

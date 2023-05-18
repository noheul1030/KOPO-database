use kopo11; # kopo11을 사용
drop table if exists twiceVote; # examtable이 존재하면 drop 삭제한다

select * from twicename;
create table twiceVote( # examtable table을 만든다
	name varchar(20), # name char 20 설정
    age int); # age int 설정
desc twiceVote; # twiceVote에 대한 정보 조회

# 실습 1 함수
drop procedure if exists twice;
delete from twiceVote where age>0; # examtable age 갯수가 0보다 크면 내용 삭제 
delimiter $$
create procedure twice(_last integer)
begin
declare _name varchar(20);
declare _cnt integer;
set _cnt = 0;

	_loop:loop
		set _cnt = _cnt+1;
        set _name = (SELECT name FROM (
			SELECT '나연' AS name UNION ALL
			SELECT '정연' UNION ALL
			SELECT '모모' UNION ALL
			SELECT '사나' UNION ALL
			SELECT '지효' UNION ALL
			SELECT '미나' UNION ALL
			SELECT '다현' UNION ALL
			SELECT '채영' UNION ALL
			SELECT '쯔위'
		) AS names ORDER BY RAND() LIMIT 1);
		insert into twiceVote value(_name,(floor(rand()*9)+1)*10);
        if _cnt = _last Then
			leave _loop;
		end if;
	end loop _loop;
end $$
delimiter ;

call twice(1000);

select count(*) from twiceVote; # 테이블의 전체 행갯수 
select * from twiceVote; # 테이블의 전체 내용 조회

drop function if exists tt;
delimiter $$
create function tt(_name varchar(20)) returns float
begin 
return (select (count(_name) / (select count(*) from twiceVote)) * 100 from twiceVote where name = _name);
end $$
delimiter ;

# name->이름, count(name)->득표수, tt(name)->선호도비율
# twiceVote테이블에서 name에 대한 행을 그룹화 
select name as 이름,count(name) as 득표수, tt(name) as 선호도비율 
from twiceVote group by name;


use kopo11; # kopo11을 사용
drop table if exists examtable; # examtable이 존재하면 drop 삭제한다
create table examtable( # examtable table을 만든다
	name varchar(20), # name char 20 설정
    id int not null primary key, # id를 null값이 없는 프라이머리키 설정
    kor int, eng int, mat int); # 국어,영어,수학 점수를 int 설정
desc examtable; # examtable에 대한 정보 조회

# 실습 1 함수
drop procedure if exists insert_examtable;
delimiter $$
create procedure insert_examtable(_last integer)
begin
declare _name varchar(20);
declare _id integer;
declare _cnt integer;
set _cnt=0;
delete from examtable where id>0 ;
	_loop: loop
		set _cnt= _cnt+1;
        set _name = concat('홍길동',cast(_cnt as char(200)));
        set _id = 209900+_cnt;
        
        insert into examtable value (_name,_id, rand()*100, rand()*100, rand()*100);
        
        if _cnt= _last Then
			leave _loop;
		end if;
	end loop _loop;
end $$
delimiter ;

call insert_examtable(10000);
select * from examtable;
# 실습1
select name as 이름, id as 학번, kor as 국어, eng as 영어, mat as 수학, kor+eng+mat as 합계,
	(kor+eng+mat)/3 as 평균 from examtable;
    
#######################################################################################

drop function if exists ranking;
delimiter $$
create function ranking(_kor integer,_eng integer, _mat integer) returns integer 
begin
    declare _rank integer;
    declare _sum integer;
    set _sum =_kor+_eng+_mat;
    select count(*) into _rank from examtable where kor+eng+mat > _sum;
return _rank + 1;
end $$
delimiter ;

# 실습 1 학번 이름 국어 영어 수학 총점 평균 등수 가 나오는 결과 테이블을 만드시오
select  ranking(kor, eng, mat)as 등수, name as 이름, id as 학번, kor as 국어, eng as 영어, mat as 수학
 , (kor+eng+mat) as 총점, (kor+eng+mat)/3 as 평균 from examtable;
# 실습 2 등수를 출력하는 함수를 만드시오 ,# 실습 3 해당 테이블을 오름차순 소트 하시오 
select  ranking(kor, eng, mat)as 등수,name as 이름, id as 학번, kor as 국어, eng as 영어, mat as 수학
 , (kor+eng+mat) as 총점, (kor+eng+mat)/3 as 평균 from examtable order by 등수;

#######################################################################################
# 실습2
select 
	row_number() over (order by (kor+eng+mat)/3 desc) as 등수, 
	name as 이름, id as 학번, kor as 국어, 
	eng as 영어, mat as 수학, kor+eng+mat as 합계,	(kor+eng+mat)/3 as 평균 
from 
	examtable
order by 
	학번;
#######################################################################################
# 실습3
select 
	row_number() over (order by (kor+eng+mat)/3 desc) as 등수, 
	name as 이름, id as 학번, kor as 국어, 
	eng as 영어, mat as 수학, kor+eng+mat as 합계,	(kor+eng+mat)/3 as 평균 
from 
	examtable
order by 
	(kor+eng+mat)/3 desc;

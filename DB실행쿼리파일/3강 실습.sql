use kopo11; # kopo11을 사용
drop table if exists examtable; # examtable이 존재하면 drop 삭제한다
create table examtable( # examtable table을 만든다
	name varchar(20), # name char 20 설정
    id int not null primary key, # id를 null값이 없는 프라이머리키 설정
    kor int, eng int, mat int); # 국어,영어,수학 점수를 int 설정
desc examtable; # examtable에 대한 정보 조회

delete from examtable where id>0; # examtable id갯수가 0보다 크면 내용 삭제 
insert into examtable value ('나연',209901,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('정연',209902,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('모모',209903,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('사나',209904,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('지효',209905,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('미나',209906,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('다현',209907,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('채영',209908,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('쯔위',209909,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('송가인',209910,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('나연',209911,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('정연',209912,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('모모',209913,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('사나',209914,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('지효',209915,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('미나',209916,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('다현',209917,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('채영',209918,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('쯔위',209919,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력
insert into examtable value ('송가인',209920,rand()*100, rand()*100, rand()*100); # 이름,학번,0~100까지의 랜덤수 입력

select* from examtable; # examtable에 담긴 정보 조회
select * from examtable order by kor; # 국어점수 오름차순으로 정렬해서 보여준다
select * from examtable order by eng; # 영어점수 낮은 순서로 정렬해서 보여준다
select * from examtable order by kor,eng; # 국어,영어 점수의 정렬로 보여준다.
select * from examtable order by kor asc; # 국어 asc 오름차순
select * from examtable order by kor desc; # 국어 desc 내림차순

select * from examtable order by name desc; # 이름의 역순 정렬
select * from examtable order by mat desc; # 수학의 역순 정렬
select *, kor+eng+mat, (kor+eng+mat)/3 from examtable; # 합계와 평균의 정보 조회
# 합계와 평균의 정보를 합계의 역순으로 정렬
select *, kor+eng+mat, (kor+eng+mat)/3 from examtable order by kor+eng+mat desc; 
# 합계의 약어 total 평균의 약어 average로 지정, total의 역순으로 examtable 조회 
select *, kor+eng+mat as total, (kor+eng+mat)/3 as average from examtable order by total desc;
# name->이름, id->학번, kor->국어, eng->영어, mat->수학, kor+eng+mat->합계, (kor+eng+mat)/3->평균 으로 
# 약어 설정, 합계의 역순으로 examtable 조회
select name as 이름, id as 학번, kor as 국어, eng as 영어, mat as 수학, kor+eng+mat as 합계,
	(kor+eng+mat)/3 as 평균 from examtable order by 합계 desc;
    
    
select * from examtable group by name; # name에 대한 행 보여주기 -> 오류!!
select name, count(name) from examtable group by name; # name과 갯수를 name 그룹에서 조회
select * from examtable group by kor; # 위와 같이 그룹에 대한 select 매칭조건이 맞지않아서 오류 발생
select kor,count(kor) from examtable group by kor; # 국어 점수에 대한 그룹조회와, 카운트
select kor,count(kor) from examtable group by eng; # 영어 점수 조회, 국어 점수에 대한 카운트?
select kor,count(kor),eng,count(eng) from examtable group by kor,eng; # 국어,영어 그룹 조회 매칭
select eng,count(eng) from examtable group by eng; # 영어 점수에 대한 그룹조회와, 카운트


insert into examtable value ('팽수',209921,100,90,rand()*100); # 팽수에 대한 정보 입력
insert into examtable value ('팽수',209922,100,90,rand()*100); # 팽수에 대한 정보 입력
select kor,count(kor),eng,count(eng) from examtable group by kor,eng; # 국어,영어 그룹 조회 매칭
# 이름,국어,영어 그룹 조회 매칭
select name,count(name),kor,count(kor),eng,count(eng) from examtable group by name,kor,eng;
# 이름,국어,영어 그룹 조회 매칭 -> 오류
select *, name,count(name),kor,count(kor),eng,count(eng) from examtable group by name,kor,eng;
# 그룹에 영어 카운트가 1보다 크면 조건 추가, 영어 그룹,카운트 조회
select eng,count(eng) from examtable group by eng having count(eng)>1;


drop procedure if exists get_sum;
DELIMITER $$
CREATE procedure get_sum(
	IN _id integer,
    OUT _name varchar(20),
    OUT _sum integer
)
begin
	declare _kor integer;
    declare _eng integer;
    declare _mat integer;
    set _kor=0;
     select name,kor,eng,mat
		into _name,_kor,_eng,_mat from examtable where id=_id;
        
	set _sum = _kor+_eng+_mat;
end $$
delimiter ;

call get_sum(209901,@name,@sum);
select @name,@sum;

#################
drop function if exists f_get_sum;

show global variables like 'log_bin_trust_function_creators';
SET GLOBAL log_bin_trust_function_creators = 'ON';

delimiter $$
create function f_get_sum(_id integer) returns integer
begin
	declare _sum integer;
    select kor+eng+mat into _sum from examtable where id =_id;
return _sum;
end $$
delimiter ;

select * ,f_get_sum(id) from examtable ; 

#####################################################14P

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
        set _name = concat('홍길동',cast(_cnt as char(4)));
        set _id = 209900+_cnt;
        
        insert into examtable value (_name,_id, rand()*100, rand()*100, rand()*100);
        
        if _cnt= _last Then
			leave _loop;
		end if;
	end loop _loop;
end $$
delimiter ;

call insert_examtable(1000);
select * from examtable;
# 셀렉트된 집합에서 30번째부터 59개를 출력하라.
select *, kor+eng+mat as sum, (kor+eng+mat)/3 as ave from examtable limit 30,59;




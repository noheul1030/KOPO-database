use kopo11;

##################### 17 page
drop table if exists examtable2; # 해당 테이블이 존재하면 삭제 
create table examtable2( # 테이블 생성
	name varchar(20), # 컬럼값 char
    id int not null primary key, # 컬럼값 int not null 프라이머리키 지정
    kor int,eng int,mat int); # 컬럼값 int
desc examtable2;
delete from examtable2 where id > 0; # id 컬럼에 값이 있으면 해당 테이블 값 삭제 
drop procedure if exists insert_examtable; # 해당 프로시저가 존재하면 삭제 
delimiter $$
create procedure insert_examtable(_limit integer) # 프로시저 생성
begin # 시작
declare _name varchar(20); # char 변수 선언 
declare _id integer; # int 변수 선언
declare _cnt integer; # int 변수 선언
set _cnt = 0; # 변수 초기값 0 지정
	_loop:loop # 반복문
		set _cnt = _cnt +1; # 변수 값 +1
		
		set _name = concat('홍길동',cast(_cnt as char(4))); # 값 형변환 하여 변수에 대입
		set _id = 209900+ _cnt; # id값 계산하여 대입
		# 값 입력 이름,id,랜덤값 0~100,0~100,0~100
		insert into examtable2 value (_name,_id,rand()*100,rand()*100,rand()*100);
		
		if _cnt = _limit then # cnt가 limit와 같으면
			leave _loop; # loop 떠나기
		end if; # if문 종료 
	end loop _loop; # loop 종료
end $$ # 프로시저 종료
delimiter ;
 
call insert_examtable(1000); # 함수 실행
select * from examtable2; # 테이블 전체 값 조회

##################### 18 page
drop view if exists examview; # 해당 view가 있으면 삭제 
create view examview(name,id,kor,eng,mat,tot,ave,ran) # view 생성
as select *, #이름,학번,국어,영어,수학
	b.kor+b.eng+b.mat, # 총점
    (b.kor + b.eng + b.mat)/3, # 평균
    (select count(*)+1 from examtable2 as a # 해당 테이블의 전체 행 기수+1 조회
		where (a.kor+a.eng+a.mat) > (b.kor+b.eng+b.mat)) # 등수
        from examtable2 as b; # 테이블 조인트 b 지정 
        
##################### 19 page
select * from  examview; # 해당 view 테이블 전체 조회
select name,ran from examview; # 해당 테이블의 name 컬럼, ran컬럼 조회

select * from examview where ran > 5 order by ran asc; # ran 값이 5 이상 인 조건 조회
insert into examview values ('나연',309933,100,100,100,300,100,1); # 에러 

##################### 20 page
drop table if exists examtableEX; # 해당 테이블이 존재하면 삭제
create table examtableEX( # 테이블 생성
	name varchar(20), # 컬럼값 char
    id int not null primary key, # 컬럼값 int not null 프라이머리키 지정
    kor int,eng int,mat int,sum int, ave double, ranking int); # 컬럼값 int 
desc examtableEX;

insert into examtableEX # 값 입력 
	# b 테이블의 국어 영어 수학 평균 값 전부,
	select *, b.kor+b.eng+ b.mat,(b.kor+b.eng+b.mat)/3,
    # 테이블 조인트 a 지정 b테이블과 비교 하여 값이 더 큰 조건의 갯수 +1
    (select count(*)+1 from examtable2 as a where (a.kor+a.eng+a.mat) > (b.kor+b.eng+b.mat))
    from examtable2 as b; # 테이블 조인트 b 지정 

select * from examtableEX order by ranking desc; # 해당 테이블의 전체 랭킹 역순 조회
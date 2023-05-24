use kopo11; # database kopo11 사용

##################### 6 page
drop table if exists hubo; # 해당 테이블이 있으면 삭제 
create table hubo( # 테이블 생성
	kiho int not null, # 해당 컬럼 int, null 값이 없게
    name varchar(10), # 이름 컬럼 char 타입
    gongyak varchar(50), # 공약 컬럼 char 타입
    primary key(kiho), # 프라이머리키로 기호
    index(kiho)); # index 기호 
    desc hubo;
    
drop table if exists tupyo; # 해당 테이블이 있으면 삭제 
create table tupyo(  # 테이블 생성
	kiho int, # 컬럼 int 타입
    age int, # 컬럼 int 타입
    foreign key(kiho) references hubo(kiho)); # foreign key =위의 후보 테이블과 연동
    desc hubo;

##################### 7 page
delete from hubo where kiho > 0; # 해당 테이블에 값이 있으면 삭제 
insert into hubo value('1',"나연","정의사회 구현"); # 값 입력 
insert into hubo value('2',"정연","모두 1억 줌"); # 값 입력 
insert into hubo value('3',"모모","월화수목토토일"); # 값 입력 
insert into hubo value('4',"사나","살맛나는 세상, 비계맛도 조금"); # 값 입력 
insert into hubo value('5',"지효","먹다 지쳐 잠드는 세상"); # 값 입력 
insert into hubo value('6',"미나","나 뽑으면 너하고 싶은거 다해"); # 값 입력 
insert into hubo value('7',"다현","장바구니 다 사줄께"); # 값 입력 
insert into hubo value('8',"채영","노는게 제일좋은 뽀로로세상 구현"); # 값 입력 
insert into hubo value('9',"쯔위","싱글 파라다이스"); # 값 입력 
select kiho as 기호, name as 성명, gongyak as 공약 from hubo; # hubo 테이블에 대한 조회

##################### 8 page
delete from tupyo where kiho>0; # tupyo의 kiho 값이 0 이상이면 삭제 
drop procedure if exists insert_tupyo; # 해당 프로시저가 있으면 삭제 
delimiter $$
create procedure insert_tupyo(_limit integer) # 프로시저 생성 
begin # 시작
declare _cnt integer; # int 변수 선언
set _cnt = 0; # 변수 초기값 0 지정
	_loop:loop # 반복문
		set _cnt = _cnt+1; # 변수 값 + 1
        # 값 입력 랜덤 1~9, 10~90
		insert into tupyo value (rand()*8+1,floor((rand()*8)+1)*10);
		if _cnt = _limit then # cnt가 limit와 같으면 true 조건문
			
			leave _loop; # 반복문 떠나기
		end if; # if문 종료
	end loop _loop; # 반복문 종료
end $$ # 프로시져 종료 
delimiter ;
call insert_tupyo(1000); # 함수 실행
select count(*) from tupyo; # tupyo 테이블 전체행 개수 조회
select kiho as 투표한기호, age as 투표자연령대 from tupyo; # tupyo 테이블에 대한 컬럼 조회

##################### 9 page
# hubo 테이블에 대한 컬럼 조회
select kiho as 기호, name as 성명, gongyaK as 공약 from hubo;
# tupyo 테이블에 대한 컬럼 조회
select kiho as 투표한기호, age as 투표자연령대 from tupyo;

##################### 10 page
# kiho 그룹의 전체 숫자 
select kiho, count(*) from tupyo group by kiho;

##################### 11 page
# tupyo 테이블을 a, hubo 테이블을 b로 지정 
select b.name,b.gongyak, count(a.kiho) from tupyo as a, hubo as b
where a.kiho = b.kiho # 두 컬럼의 값이 같으면 조회
group by a.kiho; # 해당 컬럼의 값을 기준으로 

##################### 12 page
select 
(select name from hubo where kiho= a.kiho)as 이름, # 이름을 뽑아내기위한 select
(select gongyak from hubo where kiho = a.kiho)as 공약, # 공약을 뽑아내기 위한 select
count(a.kiho) as 투표수 # 해당 컬럼 count
from tupyo as a # tupyo 테이블을 a로 지정
group by a.kiho; # 해당 컬럼의 값을 기준으로 조회

##################### 13 page
drop table if exists tupyo2; # 해당 테이블이 존재하면 삭제 
create table tupyo2( # 테이블 생성
	kiho1 int, # 기호1번
    kiho2 int, # 기호2번
    kiho3 int, # 기호3번
    age int); # 연령대1: 10대, 2:20대,...9:90대
desc tupyo2;

drop procedure if exists insert_tupyo2; # 해당 프로시저가 있으면 삭제 
delete from tupyo2; # tupyo2 값 삭제 
delimiter $$
create procedure insert_tupyo2(_limit integer) # 프로시저 생성
begin # 시작
declare _cnt integer; # int 변수 선언
set _cnt = 0; # 변수 초기값 0 지정
	_loop:loop # 반복문
		set _cnt = _cnt+1; # 변수 값 + 1
        # 컬럼 값 입력 랜덤 1~9,1~9,1~9,10~90
		insert into tupyo2 value (rand()*8+1,rand()*8+1,rand()*8+1,floor((rand()*8)+1)*10);
		if _cnt = _limit then # cnt가 limit와 같으면 true 조건문
			
			leave _loop;
		end if;
	end loop _loop;
end $$
delimiter ;
call insert_tupyo2(1000);
select * from tupyo2;

##################### 14 page
select
	a.age as '연령대', # tupyo2 a 조인트 age 컬럼
    h1.name as '투표1', # hubo h1 조인트 name 컬럼 
    h2.name as '투표2', # hubo h2 조인트 name 컬럼 
    h3.name as '투표3' # hubo h3 조인트 name 컬럼 
from tupyo2 as a, # tupyo2 a 조인트  
	hubo as h1, # hubo h1 조인트
    hubo as h2, # hubo h2 조인트
    hubo as h3 # hubo h3 조인트
where a.kiho1 = h1.kiho # a 조인트의 기호1 컬럼과  h1 조인트의 기호 컬럼이 같으면 
	and a.kiho2 = h2.kiho # 그리고 a 조인트의 기호2 컬럼과  h2 조인트의 기호 컬럼이 같으면 
	and a.kiho3 = h3.kiho; # 그리고 a 조인트의 기호3 컬럼과  h3 조인트의 기호 컬럼이 같으면 조회

select
a.age as 연령대, # tupyo2 a 조인트 age 컬럼
# hubo 테이블의 a 조인트의 기호1 컬럼값과 기호가 같은 name 컬럼조회
(select name from hubo where kiho = a.kiho1) as 투표1, 
# hubo 테이블의 a 조인트의 기호2 컬럼값과 기호가 같은 name 컬럼조회
(select name from hubo where kiho = a.kiho2) as 투표2, 
# hubo 테이블의 a 조인트의 기호3 컬럼값과 기호가 같은 name 컬럼조회
(select name from hubo where kiho= a.kiho3) as 투표3 
from tupyo2 as a; # tupyo2 a 조인트  

##################### 15 page
select
(select count(*) from tupyo2 where kiho1=1 or kiho2=1 or kiho3=1)as '나연', # 해당 조건에 맞는 컬럼 갯수
(select count(*) from tupyo2 where kiho1=2 or kiho2=2 or kiho3=2)as '정연', # 해당 조건에 맞는 컬럼 갯수
(select count(*) from tupyo2 where kiho1=3 or kiho2=3 or kiho3=3)as '모모', # 해당 조건에 맞는 컬럼 갯수
(select count(*) from tupyo2 where kiho1=4 or kiho2=4 or kiho3=4)as '사나', # 해당 조건에 맞는 컬럼 갯수
(select count(*) from tupyo2 where kiho1=5 or kiho2=5 or kiho3=5)as '지효', # 해당 조건에 맞는 컬럼 갯수
(select count(*) from tupyo2 where kiho1=6 or kiho2=6 or kiho3=6)as '미나', # 해당 조건에 맞는 컬럼 갯수
(select count(*) from tupyo2 where kiho1=7 or kiho2=7 or kiho3=7)as '다현', # 해당 조건에 맞는 컬럼 갯수
(select count(*) from tupyo2 where kiho1=8 or kiho2=8 or kiho3=8)as '채영', # 해당 조건에 맞는 컬럼 갯수
(select count(*) from tupyo2 where kiho1=9 or kiho2=9 or kiho3=9)as '쯔위', # 해당 조건에 맞는 컬럼 갯수
(select count(*) from tupyo2 where kiho1=1 or kiho2=1 or kiho3=1)+ # 해당 조건에 맞는 컬럼 갯수 더하기 
(select count(*) from tupyo2 where kiho1=2 or kiho2=2 or kiho3=2)+ # 해당 조건에 맞는 컬럼 갯수 더하기 
(select count(*) from tupyo2 where kiho1=3 or kiho2=3 or kiho3=3)+ # 해당 조건에 맞는 컬럼 갯수 더하기 
(select count(*) from tupyo2 where kiho1=4 or kiho2=4 or kiho3=4)+ # 해당 조건에 맞는 컬럼 갯수 더하기 
(select count(*) from tupyo2 where kiho1=5 or kiho2=5 or kiho3=5)+ # 해당 조건에 맞는 컬럼 갯수 더하기 
(select count(*) from tupyo2 where kiho1=6 or kiho2=6 or kiho3=6)+ # 해당 조건에 맞는 컬럼 갯수 더하기 
(select count(*) from tupyo2 where kiho1=7 or kiho2=7 or kiho3=7)+ # 해당 조건에 맞는 컬럼 갯수 더하기 
(select count(*) from tupyo2 where kiho1=8 or kiho2=8 or kiho3=8)+ # 해당 조건에 맞는 컬럼 갯수 더하기 
(select count(*) from tupyo2 where kiho1=9 or kiho2=9 or kiho3=9) as '총합', # 총합의 컬럼 갯수 
(select count(*) from tupyo2 where kiho1=kiho2 or kiho1=kiho3 or kiho2=kiho3) as '2중복', # 2중복 컬럼 갯수
(select count(*) from tupyo2 where kiho1=kiho2 and kiho1 = kiho3) as '3중복'; # 3중복 컬럼 갯수
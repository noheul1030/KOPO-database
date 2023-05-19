use kopo11;

##################### 6 page
drop table if exists hubo;
create table hubo(
	kiho int not null,
    name varchar(10),
    gongyak varchar(50),
    primary key(kiho),
    index(kiho));
    desc hubo;
    
drop table if exists tupyo;
create table tupyo(
	kiho int,
    age int,
    foreign key(kiho) references hubo(kiho));
    desc hubo;

##################### 7 page
delete from hubo where kiho > 0;
insert into hubo value('1',"나연","정의사회 구현");
insert into hubo value('2',"정연","모두 1억 줌");
insert into hubo value('3',"모모","월화수목토토일");
insert into hubo value('4',"사나","살맛나는 세상, 비계맛도 조금");
insert into hubo value('5',"지효","먹다 지쳐 잠드는 세상");
insert into hubo value('6',"미나","나 뽑으면 너하고 싶은거 다해");
insert into hubo value('7',"다현","장바구니 다 사줄께");
insert into hubo value('8',"채영","노는게 제일좋은 뽀로로세상 구현");
insert into hubo value('9',"쯔위","싱글 파라다이스");
select kiho as 기호, name as 성명, gongyak as 공약 from hubo;

##################### 8 page
delete from tupyo where kiho>0;
drop procedure if exists insert_tupyo;
delimiter $$
create procedure insert_tupyo(_limit integer)
begin
declare _cnt integer;
set _cnt = 0;
	_loop:loop
		set _cnt = _cnt+1;
		insert into tupyo value (rand()*8+1,floor((rand()*8)+1)*10);
		if _cnt = _limit then
			
			leave _loop;
		end if;
	end loop _loop;
end $$
delimiter ;
call insert_tupyo(1000);
select count(*) from tupyo;
select kiho as 투표한기호, age as 투표자연령대 from tupyo;

##################### 9 page
select kiho as 기호, name as 성명, gongyaK as 공약 from hubo;
select kiho as 투표한기호, age as 투표자연령대 from tupyo;

##################### 10 page
select kiho, count(*) from tupyo group by kiho;

##################### 11 page
select b.name,b.gongyak, count(a.kiho) from tupyo as a, hubo as b
where a.kiho = b.kiho
group by a.kiho;

##################### 12 page
select 
(select name from hubo where kiho= a.kiho)as 이름, # 이름
(select gongyak from hubo where kiho = a.kiho)as 공약, # 공약
count(a.kiho) as 투표수
from tupyo as a
group by a.kiho;

##################### 13 page
drop table if exists tupyo2;
create table tupyo2(
	kiho1 int, # 기호1번
    kiho2 int, # 기호2번
    kiho3 int, # 기호3번
    age int); # 연령대1: 10대, 2:20대,...9:90대
desc tupyo2;

drop procedure if exists insert_tupyo2;
delete from tupyo2;
delimiter $$
create procedure insert_tupyo2(_limit integer)
begin
declare _cnt integer;
set _cnt = 0;
	_loop:loop
		set _cnt = _cnt+1;
		insert into tupyo2 value (rand()*8+1,rand()*8+1,rand()*8+1,floor((rand()*8)+1)*10);
		if _cnt = _limit then
			
			leave _loop;
		end if;
	end loop _loop;
end $$
delimiter ;
call insert_tupyo2(1000);
select * from tupyo2;


drop procedure if exists insert_tupyo2;
delete from tupyo2;
delimiter $$
create procedure insert_tupyo2name(_limit integer)
begin
declare _cnt integer;

set _cnt = 0;
	_loop:loop
		set _cnt = _cnt+1;
		insert into tupyo2 value (rand()*8+1,rand()*8+1,rand()*8+1,floor((rand()*8)+1)*10);
		if _cnt = _limit then
			
			leave _loop;
		end if;
	end loop _loop;
end $$
delimiter ;
call insert_tupyo2(1000);

##################### 14 page
select
	a.age as '연령대',
    h1.name as '투표1',
    h2.name as '투표2',
    h3.name as '투표3'
from tupyo2 as a,
	hubo as h1,
    hubo as h2,
    hubo as h3
where a.kiho1 = h1.kiho
	and a.kiho2 = h2.kiho
	and a.kiho3 = h3.kiho;

select
a.age as 연령대,
(select name from hubo where kiho = a.kiho1) as 투표1,
(select name from hubo where kiho = a.kiho2) as 투표2,
(select name from hubo where kiho= a.kiho3) as 투표3
from tupyo2 as a;

##################### 15 page
select
(select count(*) from tupyo2 where kiho1=1 or kiho2=1 or kiho3=1)as '나연', 
(select count(*) from tupyo2 where kiho1=2 or kiho2=2 or kiho3=2)as '정연', 
(select count(*) from tupyo2 where kiho1=3 or kiho2=3 or kiho3=3)as '모모', 
(select count(*) from tupyo2 where kiho1=4 or kiho2=4 or kiho3=4)as '사나', 
(select count(*) from tupyo2 where kiho1=5 or kiho2=5 or kiho3=5)as '지효', 
(select count(*) from tupyo2 where kiho1=6 or kiho2=6 or kiho3=6)as '미나', 
(select count(*) from tupyo2 where kiho1=7 or kiho2=7 or kiho3=7)as '다현', 
(select count(*) from tupyo2 where kiho1=8 or kiho2=8 or kiho3=8)as '채영', 
(select count(*) from tupyo2 where kiho1=9 or kiho2=9 or kiho3=9)as '쯔위',
(select count(*) from tupyo2 where kiho1=1 or kiho2=1 or kiho3=1)+ 
(select count(*) from tupyo2 where kiho1=2 or kiho2=2 or kiho3=2)+  
(select count(*) from tupyo2 where kiho1=3 or kiho2=3 or kiho3=3)+  
(select count(*) from tupyo2 where kiho1=4 or kiho2=4 or kiho3=4)+  
(select count(*) from tupyo2 where kiho1=5 or kiho2=5 or kiho3=5)+ 
(select count(*) from tupyo2 where kiho1=6 or kiho2=6 or kiho3=6)+  
(select count(*) from tupyo2 where kiho1=7 or kiho2=7 or kiho3=7)+  
(select count(*) from tupyo2 where kiho1=8 or kiho2=8 or kiho3=8)+  
(select count(*) from tupyo2 where kiho1=9 or kiho2=9 or kiho3=9) as '총합',
(select count(*) from tupyo2 where kiho1=kiho2 or kiho1=kiho3 or kiho2=kiho3) as '2중복',
(select count(*) from tupyo2 where kiho1=kiho2 and kiho1 = kiho3) as '3중복';
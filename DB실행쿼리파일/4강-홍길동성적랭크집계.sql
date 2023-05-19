use kopo11;

##################### 17 page
drop table if exists examtable2;
create table examtable2(
	name varchar(20),
    id int not null primary key,
    kor int,eng int,mat int);
desc examtable2;
delete from examtable2 where id > 0;
drop procedure if exists insert_examtable;
delimiter $$
create procedure insert_examtable(_limit integer)
begin
declare _name varchar(20);
declare _id integer;
declare _cnt integer;
set _cnt = 0;
	_loop:loop
		set _cnt = _cnt +1;
		
		set _name = concat('홍길동',cast(_cnt as char(4)));
		set _id = 209900+ _cnt;
		
		insert into examtable2 value (_name,_id,rand()*100,rand()*100,rand()*100);
		
		if _cnt = _limit then
			leave _loop;
		end if;
	end loop _loop;
end $$
delimiter ;
 
call insert_examtable(1000);
select * from examtable2;

##################### 18 page
drop view if exists examview;
create view examview(name,id,kor,eng,mat,tot,ave,ran)
as select *, #이름,학번,국어,영어,수학
	b.kor+b.eng+b.mat, # 총점
    (b.kor + b.eng + b.mat)/3, # 평균
    (select count(*)+1 from examtable2 as a
		where (a.kor+a.eng+a.mat) > (b.kor+b.eng+b.mat)) # 등수
        from examtable2 as b;
        
##################### 19 page
select * from  examview; 
select name,ran from examview;

select * from examview where ran > 5;
insert into examview values ('나연',309933,100,100,100,300,100,1); # 에러 

##################### 20 page
drop table if exists examtableEX;
create table examtableEX(
	name varchar(20),
    id int not null primary key,
    kor int,eng int,mat int,sum int, ave double, ranking int);
desc examtableEX;

insert into examtableEX
	select *, b.kor+b.eng+ b.mat,(b.kor+b.eng+b.mat)/3,
    (select count(*)+1 from examtable2 as a where (a.kor+a.eng+a.mat) > (b.kor+b.eng+b.mat))
    from examtable2 as b;

select * from examtableEX order by ranking desc;
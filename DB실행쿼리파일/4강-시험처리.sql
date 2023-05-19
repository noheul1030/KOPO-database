use kopo11;

##################################### 과목 정답 테이블
drop table if exists Answer;
create table Answer (
	subjectID int not null primary key,
    a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,
    a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int);
desc Answer;
delete from Answer where subjectID > 0;
drop procedure if exists insert_Answer;
delimiter $$
create procedure insert_Answer(_num int)
begin
declare _cnt int;
set _cnt = 0;
	_loop:loop
		set _cnt= _cnt+1;
		insert into Answer value 
		(_cnt,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1
        ,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1);
        if _cnt = _num then
			leave _loop;
		end if;
	end loop;
end $$
delimiter ;
call insert_Answer(3);
select * from Answer;

##################################### 시험 테이블
drop table if exists Testing;    
create table Testing (
	subjectID int not null,
    stu_name varchar(20),
    stu_id int not null,
    a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,
    a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int,
    primary key(subjectID, stu_id));
desc Testing;
delete from Testing where subjectID > 0;
drop procedure if exists insert_Test;
delimiter $$
create procedure insert_Test(_num int)
begin
declare _subcnt int;
declare _cnt int;
declare _studentName varchar(20);
declare _studentID int;

set _subcnt = 0;
set _cnt = 0;
set _studentID = 231000;

	_loop:loop
		set _cnt= _cnt+1;
        set _studentName = concat('홍길동',cast(_cnt as char(5)));
        _loop2:loop
			set _subcnt= _subcnt+1;
			insert into Testing value 
			(_subcnt,_studentName,(_studentID+_cnt),
			rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,
			rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,
			rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,
			rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1,rand()*3+1);
			if _subcnt = (select count(subjectID) from Answer) then
				set _subcnt = 0;
				leave _loop2;
			end if;
		end loop;
		if _cnt = _num then
			leave _loop;
		end if;
	end loop;
end $$
delimiter ;
call insert_Test(1000);
select * from Testing order by stu_id, subjectID;

##################################### 채점 테이블
drop table if exists Scoring;
create table Scoring(
    subjectID int not null,
    stu_name varchar(20),
    stu_id int not null,
    a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,
    a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int,
    score int,
    primary key(subjectID, stu_id));
desc Scoring;

DELETE FROM Scoring where subjectID <4;
INSERT INTO Scoring select b.subjectID, b.stu_name, b.stu_id,
(a.a01=b.a01) as s01 , (a.a02=b.a02) as s02 , (a.a03=b.a03) as s03 , (a.a04=b.a04) as s04 , (a.a05=b.a05) as s05 , 
(a.a06=b.a06) as s06 , (a.a07=b.a07) as s07 , (a.a08=b.a08) as s08 , (a.a09=b.a09) as s09 , (a.a10=b.a10) as s10 ,
(a.a11=b.a11) as s11 , (a.a12=b.a12) as s12 , (a.a13=b.a13) as s13 , (a.a14=b.a14) as s14 , (a.a15=b.a15) as s15 , 
(a.a16=b.a16) as s16 , (a.a17=b.a17) as s17 , (a.a18=b.a18) as s18 , (a.a19=b.a19) as s19 , (a.a20=b.a20) as s20 ,
((a.a01=b.a01)+(a.a02=b.a02)+(a.a03=b.a03)+(a.a04=b.a04)+(a.a05=b.a05)+
(a.a06=b.a06)+(a.a07=b.a07)+(a.a08=b.a08)+(a.a09=b.a09)+(a.a10=b.a10)+
(a.a11=b.a11)+(a.a12=b.a12)+(a.a13=b.a13)+(a.a14=b.a14)+(a.a15=b.a15)+
(a.a16=b.a16)+(a.a17=b.a17)+(a.a18=b.a18)+(a.a19=b.a19)+(a.a20=b.a20))*5 as sum
from Answer as a, Testing as b where a.subjectID = b.subjectID;

select * from Scoring where subjectID = 3;

##################################### 채점리포트 테이블
drop table if exists Reporttable;    
create table Reporttable (
    stu_name varchar(20),
    stu_id int not null primary key,
    kor int, eng int, mat int);
desc Reporttable;

use kopo11;
DELETE FROM Reporttable;
INSERT INTO Reporttable (stu_name, stu_id, kor, eng, mat)
select a.stu_name, a.stu_id,
(a.score) as kor,(a.score) as eng,(a.score) as mat
from Scoring as a WHERE a.subjectID IN (1, 2, 3);

insert into Reporttable
select distinct a.stu_name,a.stu_id,
	a1.score,
    a2.score,
    a3.score 
from Scoring as a,
	Scoring as a1,
    Scoring as a2,
    Scoring as a3
where a1.stu_id = a.stu_id
	and a2.stu_id = a.stu_id
	and a3.stu_id = a.stu_id
    and a1.subjectID = 1
	and a2.subjectID = 2
	and a3.subjectID = 3;

select *, kor+eng+mat as sum, (kor+eng+mat)/3 as ave, row_number() over (order by kor+eng+mat desc) as ranking from Reporttable;

# 과목 점수별 득점자수,득점률 리포트
select kor as 국어,count(kor) as 카운트,count(kor)/(select count(*) from Reporttable)*100 as 점수득점률 from Reporttable group by kor order by 국어 asc;
select eng as 영어,count(eng) as 카운트,count(eng)/(select count(*) from Reporttable)*100 as 점수득점률 from Reporttable group by eng order by 영어 asc;
select mat as 수학,count(mat) as 카운트,count(mat)/(select count(*) from Reporttable)*100 as 점수득점률 from Reporttable group by mat order by 수학 asc;
# 국어 문제별 득점자수, 득점률 리포트 
select a01 as 국어a01 ,count(a01),count(a01)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a01;
select a02 as 국어a02 ,count(a02),count(a02)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a02;
select a03 as 국어a03 ,count(a03),count(a03)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a03;
select a04 as 국어a04 ,count(a04),count(a04)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a04;
select a05 as 국어a05 ,count(a05),count(a05)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a05;
select a06 as 국어a06 ,count(a06),count(a06)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a06;
select a07 as 국어a07 ,count(a07),count(a07)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a07;
select a08 as 국어a08 ,count(a08),count(a08)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a08;
select a09 as 국어a09 ,count(a09),count(a09)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a09;
select a10 as 국어a10 ,count(a10),count(a10)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a10;
select a11 as 국어a11 ,count(a11),count(a11)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a11;
select a12 as 국어a12 ,count(a12),count(a12)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a12;
select a13 as 국어a13 ,count(a13),count(a13)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a13;
select a14 as 국어a14 ,count(a14),count(a14)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a14;
select a15 as 국어a15 ,count(a15),count(a15)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a15;
select a16 as 국어a16 ,count(a16),count(a16)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a16;
select a17 as 국어a17 ,count(a17),count(a17)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a17;
select a18 as 국어a18 ,count(a18),count(a18)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a18;
select a19 as 국어a19 ,count(a19),count(a19)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a19;
select a20 as 국어a20 ,count(a20),count(a20)/(select count(*) from Scoring where subjectID = 1)*100 as 점수득점률 from Scoring  where subjectID = 1 group by a20;

# 영어 문제별 득점자수, 득점률 리포트 
select a01 as 영어a01 ,count(a01),count(a01)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a01;
select a02 as 영어a02 ,count(a02),count(a02)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a02;
select a03 as 영어a03 ,count(a03),count(a03)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a03;
select a04 as 영어a04 ,count(a04),count(a04)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a04;
select a05 as 영어a05 ,count(a05),count(a05)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a05;
select a06 as 영어a06 ,count(a06),count(a06)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a06;
select a07 as 영어a07 ,count(a07),count(a07)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a07;
select a08 as 영어a08 ,count(a08),count(a08)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a08;
select a09 as 영어a09 ,count(a09),count(a09)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a09;
select a10 as 영어a10 ,count(a10),count(a10)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a10;
select a11 as 영어a11 ,count(a11),count(a11)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a11;
select a12 as 영어a12 ,count(a12),count(a12)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a12;
select a13 as 영어a13 ,count(a13),count(a13)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a13;
select a14 as 영어a14 ,count(a14),count(a14)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a14;
select a15 as 영어a15 ,count(a15),count(a15)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a15;
select a16 as 영어a16 ,count(a16),count(a16)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a16;
select a17 as 영어a17 ,count(a17),count(a17)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a17;
select a18 as 영어a18 ,count(a18),count(a18)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a18;
select a19 as 영어a19 ,count(a19),count(a19)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a19;
select a20 as 영어a20 ,count(a20),count(a20)/(select count(*) from Scoring where subjectID = 2)*100 as 점수득점률 from Scoring  where subjectID = 2 group by a20;

# 수학 문제별 득점자수, 득점률 리포트 
select a01 as 수학a01 ,count(a01),count(a01)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a01;
select a02 as 수학a02 ,count(a02),count(a02)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a02;
select a03 as 수학a03 ,count(a03),count(a03)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a03;
select a04 as 수학a04 ,count(a04),count(a04)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a04;
select a05 as 수학a05 ,count(a05),count(a05)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a05;
select a06 as 수학a06 ,count(a06),count(a06)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a06;
select a07 as 수학a07 ,count(a07),count(a07)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a07;
select a08 as 수학a08 ,count(a08),count(a08)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a08;
select a09 as 수학a09 ,count(a09),count(a09)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a09;
select a10 as 수학a10 ,count(a10),count(a10)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a10;
select a11 as 수학a11 ,count(a11),count(a11)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a11;
select a12 as 수학a12 ,count(a12),count(a12)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a12;
select a13 as 수학a13 ,count(a13),count(a13)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a13;
select a14 as 수학a14 ,count(a14),count(a14)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a14;
select a15 as 수학a15 ,count(a15),count(a15)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a15;
select a16 as 수학a16 ,count(a16),count(a16)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a16;
select a17 as 수학a17 ,count(a17),count(a17)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a17;
select a18 as 수학a18 ,count(a18),count(a18)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a18;
select a19 as 수학a19 ,count(a19),count(a19)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a19;
select a20 as 수학a20 ,count(a20),count(a20)/(select count(*) from Scoring where subjectID = 3)*100 as 점수득점률 from Scoring  where subjectID = 3 group by a20;





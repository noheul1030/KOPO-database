use kopo11;

##################################### 정답 테이블
drop table if exists Answer;
create table korAnswer (
	subjectID int not null primary key,
    a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,
    a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int);
desc Answer;
delete from Answer where subjectID > 0;
drop procedure if exists insert_Answer;
delimiter $$
create function insert_Answer() return integer
begin
return (insert into Answer value 
(rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1
,rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1,rand()*4+1))
;
end $$
delimiter ;
call insert_Answer(20);
select * from Answer;





    
drop table if exists Testing;    
create table Testing (
	subjectID int not null,
    stu_name varchar(20),
    stu_id int not null,
    a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,
    a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int,
    primary key(subjectID, stu_id));
    
drop table if exists Scoring;
create table Scoring(
    subjectID int not null,
    stu_name varchar(20),
    stu_id int not null,
    a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,
    a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int,
    score int,
    primary key(subjectID, stu_id));
    
drop table if exists Reporttable;    
create table Reporttable (
    stu_name varchar(20),
    stu_id int not null primary key,
    kor int, eng int, mat int);
    
desc Answer;
desc Testing;
desc Scoring;
desc Reporttable;
    
use kopo11; # kopo11을 사용
drop table if exists twiceVote; # examtable이 존재하면 drop 삭제한다
create table twiceVote( # examtable table을 만든다
	name varchar(20), # name char 20 설정
    age int); # age int 설정
desc twiceVote; # twiceVote에 대한 정보 조회
    
delete from twiceVote where age>0; # examtable age 갯수가 0보다 크면 내용 삭제 
insert into twiceVote value ('나연',(floor(rand()*9)+1)*10); # 이름, 랜덤 연령대 숫자 입력
insert into twiceVote value ('정연',(floor(rand()*9)+1)*10); # 이름, 랜덤 연령대 숫자 입력
insert into twiceVote value ('모모',(floor(rand()*9)+1)*10); # 이름, 랜덤 연령대 숫자 입력
insert into twiceVote value ('사나',(floor(rand()*9)+1)*10); # 이름, 랜덤 연령대 숫자 입력
insert into twiceVote value ('지효',(floor(rand()*9)+1)*10); # 이름, 랜덤 연령대 숫자 입력
insert into twiceVote value ('미나',(floor(rand()*9)+1)*10); # 이름, 랜덤 연령대 숫자 입력
insert into twiceVote value ('다현',(floor(rand()*9)+1)*10); # 이름, 랜덤 연령대 숫자 입력
insert into twiceVote value ('채영',(floor(rand()*9)+1)*10); # 이름, 랜덤 연령대 숫자 입력
insert into twiceVote value ('쯔위',(floor(rand()*9)+1)*10); # 이름, 랜덤 연령대 숫자 입력

select count(*) from twiceVote; # 테이블의 전체 행갯수 
select * from twiceVote; # 테이블의 전체 내용 조회

# name->이름, count(name)->득표수, count(name)/(select count(*) from twiceVote)*100->득표율
# twiceVote테이블에서 name에 대한 행을 그룹화 하고 득표수에 대한 역순 정렬
select name as 이름,count(name) as 득표수, count(name)/(select count(*) from twiceVote)*100 as 득표율 
from twiceVote group by name order by 득표수 desc;

# age->연령대, count(age)->득표수, count(age)/(select count(*) from twiceVote)*100->득표율
# twiceVote테이블에서 age에 대한 행을 그룹화 하고 연령대에 대한 정렬
select age as 연령대,count(age) as 득표수, count(age)/(select count(*) from twiceVote)*100 as 득표율 
from twiceVote group by age order by 연령대 asc;

# 나연에 대한 조회
# age->연령대, count(age)->득표수, count(age)/(select count(*) from twiceVote)*100->득표율
# twiceVote테이블에서 name 값 '나연'을 찾고 age에 대한 행을 그룹화 하고 득표수에 대한 역순 정렬
select age as 연령대,count(age) as 득표수, count(age)/(select count(*) from twiceVote)*100 as 득표율 
from twiceVote where name= '나연' group by age order by 득표수 desc;

# 정연에 대한 조회
# age->연령대, count(age)->득표수, count(age)/(select count(*) from twiceVote)*100->득표율
# twiceVote테이블에서 name 값 '정연'을 찾고 age에 대한 행을 그룹화 하고 득표수에 대한 역순 정렬
select age as 연령대,count(age) as 득표수, count(age)/(select count(*) from twiceVote)*100 as 득표율 
from twiceVote where name= '정연' group by age order by 득표수 desc;
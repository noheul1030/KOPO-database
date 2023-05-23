use noheul;

drop table if exists reservation;
truncate reservation;
create table reservation(
	name varchar(20),
    reserve_date varchar(20),
    room int,
    addr varchar(20),
    tel varchar(20),
    ipgum_name varchar(20),
    memo varchar(100),
    input_date varchar(20));

truncate reservation;

insert into reservation values ('나연','2021-06-14',2,'서울','010-0101-0101','나연','따뜻한방 주세요',now());    
insert into reservation values ('정연','2021-06-15',3,'서울','010-0101-0101','나연','따뜻한방 주세요',now());  
insert into reservation values ('모모','2021-06-16',1,'서울','010-0101-0101','나연','따뜻한방 주세요',now());  
insert into reservation values ('사나','2021-06-17',1,'서울','010-0101-0101','나연','따뜻한방 주세요',now());  
insert into reservation values ('지효','2021-06-18',1,'서울','010-0101-0101','나연','따뜻한방 주세요',now());  
insert into reservation values ('다현','2021-06-19',1,'서울','010-0101-0101','나연','따뜻한방 주세요',now());  
insert into reservation values ('채영','2021-06-20',3,'서울','010-0101-0101','나연','따뜻한방 주세요',now());  
insert into reservation values ('쯔위','2021-06-21',2,'서울','010-0101-0101','나연','따뜻한방 주세요',now());  
insert into reservation values ('미나','2021-06-22',2,'서울','010-0101-0101','나연','따뜻한방 주세요',now());  

# 한달 간 예약상황 보여주는 procedure 생성
drop procedure if exists reservstat_calc;
delimiter //
create procedure reservstat_calc()
begin
    drop table if exists reserv_stat;
    create table reserv_stat(
      reserve_date date not null primary key,
        room1 varchar(20),
        room2 varchar(20),
        room3 varchar(20));

    insert into reserv_stat
   select r.reserve_date,
       if(r.room = 1, r.name, "예약가능") as room1_name,
       if(r.room = 2, r.name, "예약가능") as room2_name,
       if(r.room = 3, r.name, "예약가능") as room3_name
   from reservation as r;
   select * from reserv_stat;
end//
delimiter ;
call reservstat_calc();
select * from reserv_stat;
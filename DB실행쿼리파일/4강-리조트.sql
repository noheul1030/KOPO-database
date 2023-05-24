use kopo11;

drop table if exists reservation; # 해당 테이블이 존재하면 삭제 

create table reservation( # 테이블 생성
	name varchar(20), # 칼럼 char 지정
    reserve_date date, # 칼럼 char 지정
    room int, # 칼럼 int 지정
    addr varchar(20), # 칼럼 char 지정
    tel varchar(20), # 칼럼 char 지정
    ipgum_name varchar(20), # 칼럼 char 지정
    memo varchar(100), # 칼럼 char 지정
    input_date varchar(20),
    primary key(reserve_date, room));
# 칼럼 char 지정

truncate reservation; # 테이블 내용 삭제 

# 테이블 값 입력 (날짜 다르게)
insert into reservation values ('나연','2023-06-01',2,'서울','010-0101-0101','정연','따뜻한방 주세요',now());    
insert into reservation values ('정연','2023-06-02',3,'대전','010-0101-0101','모모','따뜻한방 주세요',now());  
insert into reservation values ('모모','2023-06-03',1,'서울','010-0101-0101','사나','따뜻한방 주세요',now());  
insert into reservation values ('사나','2023-06-04',1,'전주','010-0101-0101','지효','따뜻한방 주세요',now());  
insert into reservation values ('지효','2023-06-05',2,'서울','010-0101-0101','다현','따뜻한방 주세요',now());  
insert into reservation values ('다현','2023-06-06',1,'인천','010-0101-0101','모모','따뜻한방 주세요',now());
insert into reservation values ('모모','2023-06-07',1,'전주','010-0101-0101','사나','따뜻한방 주세요',now());  
insert into reservation values ('사나','2023-06-08',3,'서울','010-0101-0101','사나','따뜻한방 주세요',now());  
insert into reservation values ('사나','2023-06-09',3,'서울','010-0101-0101','지효','따뜻한방 주세요',now());  
#insert into reservation values ('지효','2023-06-10',1,'인천','010-0101-0101','채영','따뜻한방 주세요',now());    
#insert into reservation values ('채영','2023-06-11',3,'서울','010-0101-0101','모모','따뜻한방 주세요',now());  
insert into reservation values ('쯔위','2023-06-12',2,'대전','010-0101-0101','사나','따뜻한방 주세요',now());  
insert into reservation values ('미나','2023-06-13',1,'서울','010-0101-0101','사나','따뜻한방 주세요',now());
insert into reservation values ('나연','2023-06-14',2,'인천','010-0101-0101','지효','따뜻한방 주세요',now());
insert into reservation values ('모모','2023-06-15',1,'전주','010-0101-0101','다현','따뜻한방 주세요',now());  
insert into reservation values ('사나','2023-06-16',3,'서울','010-0101-0101','채영','따뜻한방 주세요',now());  
insert into reservation values ('사나','2023-06-17',3,'서울','010-0101-0101','쯔위','따뜻한방 주세요',now());  
insert into reservation values ('지효','2023-06-18',1,'인천','010-0101-0101','미나','따뜻한방 주세요',now());      
#insert into reservation values ('정연','2023-06-19',3,'서울','010-0101-0101','다현','따뜻한방 주세요',now());  
insert into reservation values ('모모','2023-06-20',1,'전주','010-0101-0101','채영','따뜻한방 주세요',now());  
insert into reservation values ('사나','2023-06-21',3,'서울','010-0101-0101','쯔위','따뜻한방 주세요',now());  
insert into reservation values ('사나','2023-06-22',3,'서울','010-0101-0101','지효','따뜻한방 주세요',now());  
insert into reservation values ('지효','2023-06-23',1,'인천','010-0101-0101','다현','따뜻한방 주세요',now());  
insert into reservation values ('다현','2023-06-24',1,'서울','010-0101-0101','모모','따뜻한방 주세요',now());  
insert into reservation values ('채영','2023-06-25',3,'부산','010-0101-0101','사나','따뜻한방 주세요',now());  
insert into reservation values ('쯔위','2023-06-26',2,'대전','010-0101-0101','사나','따뜻한방 주세요',now());  
insert into reservation values ('미나','2023-06-27',2,'전주','010-0101-0101','지효','따뜻한방 주세요',now());
insert into reservation values ('다현','2023-06-28',1,'서울','010-0101-0101','채영','따뜻한방 주세요',now());  
insert into reservation values ('채영','2023-06-29',3,'부산','010-0101-0101','쯔위','따뜻한방 주세요',now());  
insert into reservation values ('쯔위','2023-06-30',2,'대전','010-0101-0101','미나','따뜻한방 주세요',now());    
select * from reservation; # 해당 reservation 테이블 전체 값 조회

drop table if exists reserv_stat; # 해당 테이블 존재하면 삭제
create table reserv_stat( # 테이블 생성
	reserve_date date not null primary key, # date 칼럼 프라이머리키 지정
	room1 varchar(20), # 칼럼 char 지정
	room2 varchar(20), # 칼럼 char 지정
	room3 varchar(20)); # 칼럼 char 지정
		

# 한달 간 예약상황 보여주는 procedure 생성
drop procedure if exists reservstat_calc;
delimiter %%
create procedure reservstat_calc(_last integer) # 프로시져 생성
begin # 시작
	DECLARE _date date; # date 변수 선언
	DECLARE _room1 varchar(20); # char 타입 변수 선언
	DECLARE _room2 varchar(20); # char 타입 변수 선언
	DECLARE _room3 varchar(20); # char 타입 변수 선언
	DECLARE _cnt int; # int 타입 변수 선언
	
	set _cnt = 0; # 변수에 초기값 0 대입 
	_loop : LOOP # 반복문
		set _cnt = _cnt+1; # 변수 + 1
		set _date = date_add(now(), interval _cnt DAY); # date 변수에 현재 date, cnt 값 대입
		# 1번 방 셋팅값 입력
		set _room1 = (select name from reservation where reserve_date = _date and room = 1);
        #  null 값이 들어오면 예약가능
		if _room1 is null then set _room1 = '예약가능';
		end if; # if문 종료
        # 2번 방 셋팅값 입력
		set _room2 = (select name from reservation where reserve_date = _date and room = 2);
        #  null 값이 들어오면 예약가능
		if _room2 is null then set _room2 = '예약가능';
		end if; # if문 종료
        # 3번 방 셋팅값 입력
		set _room3 = (select name from reservation where reserve_date = _date and room = 3);
        #  null 값이 들어오면 예약가능
		if _room3 is null then set _room3 = '예약가능';
		end if; # if문 종료
		
        # reserv_stat 값 입력
		insert into reserv_stat values(_date, _room1, _room2, _room3);
		
        # cnt 값이 last와 같아지면
		if _cnt = _last then 
			leave _loop; # 반복문 떠나기 
		end if; # if문 종료
	end loop _loop;	# 반복문 종료	
end %% # 프로시저 종료
delimiter ;
call reservstat_calc(30); # 함수 실행
select * from reserv_stat order by reserve_date; # 해당 테이블의 reserve_date그룹의 정보조회

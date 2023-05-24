use kopo11; # kopo11 

select * from freewifi; # 테이블 전체 정보 조회
select count(*) from freewifi; # 테이블 전체 행 개수 조회

drop procedure if exists print_freewifi; # 프로시저가 존재하면 삭제
DELIMITER $$
CREATE PROCEDURE print_freewifi(_page int, _page_size int) # 프로시저 생성
BEGIN # 시작
    declare _start int; # int 변수 선언
    declare _maxpage int; # int 변수 선언
    declare _lat double; # double 변수 선언
    declare _long double;  # double 변수 선언
    set _lat = 37.3860521; # 위도 
    set _long = 127.1214038; # 경도
    set _start = 0; # 변수 초기값 지정
    # 최대 페이지 값 계산
    set _maxpage = (select count(*) from freewifi)/_page_size;
		# maxpage가 page보다 크거나 같고 그리고 page가 0보다 크면 true 조건문
		if _maxpage >= _page and _page > 0 then
			SET _start = (_page - 1) * _page_size; # 변수 값 지정
		# maxpage가 page보다 작으면 조건문
		elseif _maxpage < _page then
			SET _start = (_maxpage - 1)* _page_size; # 변수 값 지정
		end if; # if조건문 종료
        
	# 번호,주소,위도,경도,거리계산 한 정보를 리미트 제한으로 조회
    select number as 번호
      ,place_addr_road as 주소 
      ,latitude as 위도
      ,longitude as 경도 
      ,sqrt(power(latitude-_lat,2) + power(longitude-_long,2)) as 거리
	from freewifi
    LIMIT _start, _page_size;

END $$ # 프로시저 종료
DELIMITER ;

CALL print_freewifi(5, 25); # 함수 실행


# 최단거리 구하기 
SELECT 
	number,
	place_addr_road AS '도로명 주소',
	latitude AS '위도',
	longitude AS '경도'
FROM freewifi
WHERE SQRT(POWER(latitude-37.3860521,2) + POWER(longitude-127.1214038,2)) = (
	SELECT MIN(SQRT(POWER(latitude-37.3860521,2) + POWER(longitude-127.1214038,2)))
	FROM freewifi
);


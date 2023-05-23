use noheul; # kopo11 

select * from freewifi;
select count(*) from freewifi;

drop procedure if exists print_freewifi;
DELIMITER $$
CREATE PROCEDURE print_freewifi(_page int, _page_size int)
BEGIN
    declare _start int;
    declare _maxpage int;
    set _start = 0;
    set _maxpage = (select count(*) from freewifi)/_page_size;
    
		if _maxpage >= _page and _page > 0 then
			SET _start = (_page - 1) * _page_size;
		elseif _maxpage < _page then
			SET _start = (_maxpage - 1)* _page_size;
		end if;
	
    select number as 번호
      ,place_addr_road as 주소 
      ,latitude as 위도
      ,longitude as 경도 
      ,sqrt(power(latitude-37.3860521,2) + power(longitude-127.1214038,2)) as 거리
	from freewifi
    LIMIT _start, _page_size;
   

END $$
DELIMITER ;

CALL print_freewifi(5, 25);
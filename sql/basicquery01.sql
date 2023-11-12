-- Basic Query Practice --

drop table pet;

create table pet(
	name varchar(20),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

desc pet;

insert into pet values ('마음이', '대혁', 'dog', 'f', '2018-10-10', null);

select * from pet;

select name, owner from pet;


-- load data --

load data local infile 'c:\\pet.txt' into table pet;

select * from pet;

drop table pet;


-- sql select --
-- from, where, select 순서로 실행됨 --

select * from pet; -- 모든 행을 다 선택하는 경우 --

select * from pet where name = 'Bowser'; -- 안에 있는 데이터는 대소문자 구분함 --

select * from pet where birth > '1998-01-01'; -- 1998년 포함 이후 출생자 --

select * from pet where gender = 'f' and species = 'dog'; -- 암컷 강아지 --

select * from pet where species = 'snake' or species = 'bird'; -- 뱀과 새 --

select name, birth from pet; -- 필요한 데이터만 찾아보는 것이 좋음 --


-- order by : 정렬 (asc - 오름차순, desc - 내림차순) --

select * from pet order by birth; -- default : asc --

select name, birth from pet order by birth desc;

select * from pet order by gender, birth desc; -- 여러 칼럼 정렬 가능 --


-- null 다루기 (is null, is not null) --

select * from pet where gender = null;

select * from pet where gender is null;

select * from pet where gender is not null;


-- 패턴 매칭 (Like) --

select * from pet where name Like 'b%'; -- b로 시작하는 모든 문자열 : 값 대소 구분 안함 --

select * from pet where name Like 'b____'; -- b로 시작하는 5문자열 --

select * from pet where name Like '%fy'; -- fy로 끝나는 문자열 --

select * from pet where name Like '%w%'; -- w가 들어간 문자열 --

select * from pet where name Like '_____'; -- 문자열 5개 --

select * from pet where length(name) = 5; -- 문자열 5개 --


-- 집계함수 (count, avg, max, min, sum. . . 결과가 하나만 나옴) --

select count(*) from pet; -- 전체 행 개수 --
select * from pet;

select count(*) as '총 애완동물 수'  from pet;

select name, count(*) from pet; -- 집계함수에 다른 컬럼도 같이 뽑으면 안됨 : 원래 에러가 맞음 --


-- group by : 집계합수가 select 절에 있는 경우 사용 --
-- group by가 없는 상태에서 select문에 집계함수 사용시 데이터는 오직 하나 --
-- from, where, group by, select 순으로 실행 --

select owner, count(*) as '애완동물 수' from pet group by owner; -- 주인별로 몇마리 --


-- having : group by 마친 후 실행 --

select owner, count(*) from pet group by owner having count(*) > 1; -- having 절에서 count(*)를 다시 하는 것이 아님 (column의 이름) --

select owner, count(*) from pet where death is null or death = '0000-00-00' group by owner having count(*) > 1;


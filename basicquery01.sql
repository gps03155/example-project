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


--
-- drop
--

drop table member;


--
-- create
-- 

create table member(
	no int unsigned NOT NULL,
    email varchar(50) NOT NULL,
    password varchar(20) NOT NULL,
    name varchar(20),
    dept_name varchar(20) NOT NULL default 'none',
    
    constraint no_pk PRIMARY KEY (no)
);

desc member;


-- 
-- alter
--

-- column 추가

alter table member add column (juminbunho char(13) NOT NULL);    

desc member;

alter table member drop column juminbunho;

-- 위치 지정
alter table member add column juminbunho char(13) NOT NULL first;

alter table member add column juminbunho char(13) NOT NULL after name;

alter table member add column join_date DATETIME NOT NULL;

desc member;

alter table member change no no int unsigned NOT NULL auto_increment;

alter table member rename user;

desc user;


--
-- foreign key : constraint (제약 조건 : 데이터의 생성, 삭제 수정에 제약을 준다.)
--

create table dept(
	no int unsigned NOT NULL auto_increment,
    name varchar(50) NOT NULL,
    
    constraint no_pk PRIMARY KEY (no)
);

desc dept;

alter table user drop column dept_name;

drop table user;

create table user(
	no int unsigned NOT NULL auto_increment,
    email varchar(50) NOT NULL,
    password varchar(64) NOT NULL,
    name varchar(20),
    juminbunho char(13) NOT NULL,
    join_date DATETIME NOT NULL,
    dept_no int unsigned,
    
    constraint no_pk PRIMARY KEY (no),
    constraint dept_no_fk FOREIGN KEY (dept_no) references dept (no) on delete cascade -- 삭제하는 경우 에러내라 restrict, set null, cascade
);

desc user;

insert into dept (name) values ('개발팀');

select * from dept;

insert into user values (null, 'a@a.com', password('1234'), '둘리', '', now(), 1); -- password() : 비밀번호 암호화해서 저장

select * from user;

delete from user where no = 1;

delete from dept where no = 2;
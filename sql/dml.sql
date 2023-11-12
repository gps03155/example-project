--
-- insert
--

desc employee;

select * from employee;

insert into employee values(null, 'test', null);

insert into employee (name, no, dept_no) values ('test', null, null);

-- default 세팅 안해주면 기본 null 값
insert into employee (name) values ('test2');


--
-- update
--

select * from employee;

-- 한 테이블에 하나씩 적용해야함

update employee 
set name = '도우넛', dept_no = 1
where no = 5;


--
-- delete
--

delete from employee
where name = 'test2';

delete from employee
where name = 'test';

select * from employee;
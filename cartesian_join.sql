desc employee;

desc department;

-- auto_increment : null 넣으면 자동으로 올라감 --
insert into department values(null, '총무팀');
insert into department values(null, '개발팀');
insert into department values(null, '인사팀');
insert into department values(null, '영업팀');

select *
from department;

insert into employee values(null, '둘리', 1);
insert into employee values(null, '마이콜', 2);
insert into employee values(null, '또치', 3);
insert into employee values(null, '길동', null);

select *
from employee;

-- cartesian join --

select *
from employee, department;


-- join --

-- inner join에서 null 값을 가지고 있으면 데이터가 빠져버림 --
select * 
from employee e, department d
where e.dept_no = d.no;


--
-- outer join --
--

select *
from employee e, department d
where e.dept_no = d.no;


-- left outer join --

select *
from employee e left outer join department d on e.dept_no = d.no;

select e.name, ifnull(d.name, '없음')
from employee e left outer join department d on e.dept_no = d.no;


-- right outer join --

select *
from employee e right outer join department d on e.dept_no = d.no;


-- full outer join : MySQL에서는 full outer join 지원 안함 --

select *
from employee e left outer join department d on e.dept_no = d.no
union
select *
from employee e right outer join department d on e.dept_no = d.no;


-- join ~ on (ineer join) --

select *
from employee e join department d on e.dept_no = d.no;


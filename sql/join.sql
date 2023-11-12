-- equi join (inner join) --

-- 현재 근무중인 직원의 이름과 직책을 출력하세요. --
select *
from employees, titles
where employees.emp_no = titles.emp_no;

select e.emp_no, e.first_name, t.title
from employees e, titles t
where e.emp_no = t.emp_no  -- join 조건 --
	  and t.to_date = '9999-01-01'; -- 선택 조건 --
      

-- 현재 근무중인 직책이 Senior Engineer인 여자 직원의 이름을 출력하세요. --

select e.emp_no, e.first_name
from employees e, titles t
where e.emp_no = t.emp_no
		and t.title = 'Senior Engineer'
        and e.gender = 'F'
        and t.to_date = '9999-01-01';

--
-- ANSI/ISO SQL의 JOIN
--

-- 1. natural join --

select e.emp_no, e.first_name, t.title
from employees e natural join titles t
where t.to_date = '9999-01-01';

select e.emp_no, e.first_name, t.title
from employees e join titles t on e.emp_no = t.emp_no
where t.to_date = '9999-01-01';


-- 2. join ~ using

select e.emp_no, e.first_name, t.title
from employees e join titles t using (emp_no)
where t.to_date = '9999-01-01';


-- 3. join ~ on : 공통된 컬럼명이 없을 경우

select e.emp_no, e.first_name, t.title
from employees e join titles t on e.emp_no = t.emp_no
where t.to_date = '9999-01-01';


-- ex1)
select emp_no, concat(first_name, ' ', last_name), dept_name
from (employees join dept_emp using(emp_no)) join departments using(dept_no)
where to_date = '9999-01-01'
order by concat(first_name, ' ', last_name);

-- ex2)
select emp_no, concat(first_name, ' ', last_name), salary
from employees join salaries using(emp_no)
where to_date = '9999-01-01'
order by concat(first_name, ' ', last_name);
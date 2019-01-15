-- MySQL 함수 --
select ucase('Seoul'), upper('seoul');

select lcase('Seoul'), lower('SEOUL');

select substring('Happy Day', 3, 2); -- 1부터 시작 --

select substring('Happy Day', 3, 2) from dual; -- table 없을 때 dual 사용하면 빈 테이블 --

select substring(first_name, 3, 2) from employees;

select first_name, gender, hire_date
from employees
where hire_date like '1989-__-__';

select first_name, gender, hire_date
from employees
where substring(hire_date, 1, 4) = '1989';


-- pad --
select LPAD('hi', 10, '*') from dual;

select RPAD('hi', 10, '*') from dual; 


-- 2001, 2002년 salary 7만 달러 이하... 정렬 --
select emp_no, LPAD(cast(salary as char), 10, '*')
from salaries;
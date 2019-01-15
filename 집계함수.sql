-- 집계함수 (통계) --
select *
from salaries
where emp_no = 10060;

select avg(salary), sum(salary)
from salaries
where emp_no = 10060;

select emp_no, avg(salary), sum(salary)
from salaries
group by emp_no;


-- 최저 임금, 최대 임금 --
select *
from (select max(salary) as max_salary, min(salary) as min_salary
		from salaries
		where emp_no = 10060) alias;

select salary, from_date
from salaries
where emp_no = 10060;
-- 테이블간 조인(JOIN) SQL 문제입니다.

-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.

select emp_no, first_name, salary
from employees join salaries using (emp_no)
where to_date = '9999-01-01'
order by salary desc;


-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.

select emp_no, concat(first_name, ' ', last_name) as '이름', title
from employees join titles using (emp_no)
where to_date = '9999-01-01'
order by concat(first_name, ' ', last_name);


-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요..

select emp_no, concat(first_name, ' ', last_name) as '이름', dept_name
from (employees join dept_emp using (emp_no)) join departments using (dept_no)
where to_date = '9999-01-01'
order by concat(first_name, ' ', last_name);


-- 문제4.
-- 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.

select emp_no, concat(first_name, ' ', last_name) as '이름', salary, title, dept_name
from (((employees join titles using (emp_no)) join dept_emp using(emp_no)) join departments using (dept_no)) join salaries using (emp_no)
where titles.to_date = '9999-01-01' and dept_emp.to_date = '9999-01-01' and salaries.to_date = '9999-01-01'
order by concat(first_name, ' ', last_name);


-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요. 
-- (현재 ‘Technique Leader’의 직책(으로 근무하는 사원은 고려하지 않습니다.) 
-- 이름은 first_name과 last_name을 합쳐 출력 합니다.

select emp_no, concat(first_name, ' ', last_name) as '이름'
from employees join titles using (emp_no)
where title = 'Technique Leader' and to_date < '9999-01-01';


-- 문제6.
-- 직원 이름(last_name) 중에서 S(대문자)로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.

select concat(first_name, ' ', last_name) as '이름', dept_name, title
from ((employees join titles using (emp_no)) join dept_emp using (emp_no)) join departments using (dept_no)
where last_name Like 'S%'
	  and titles.to_date = '9999-01-01' and dept_emp.to_date = '9999-01-01';


-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 큰 순서대로 출력하세요.

select emp_no, title, salary
from titles join salaries using (emp_no)
where titles.to_date = '9999-01-01' and salaries.to_date = '9999-01-01'
	  and salary >= 40000 and title = 'Engineer'
order by salary desc;


-- 문제8.
-- 현재 급여가 50000이 넘는 직책을 직책, 급여로 급여가 큰 순서대로 출력하시오

-- 직책별 -- 
select title, avg(salary)
from salaries join titles using (emp_no)
where salaries.to_date = '9999-01-01'
group by title
having avg(salary) > 50000
order by salary desc;

-- 사원별 --
select title, salary
from salaries join titles using (emp_no)
where salaries.to_date = '9999-01-01' and titles.to_date = '9999-01-01'
		and salary > 50000
order by salary desc;


-- 문제9.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.

select dept_name, avg(salary) as '평균 연봉'
from (salaries join dept_emp using (emp_no)) join departments using (dept_no)
where salaries.to_date = '9999-01-01' and dept_emp.to_date = '9999-01-01'
group by dept_name
order by avg(salary) desc;


-- 문제10.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.

select title, avg(salary)
from salaries join titles using (emp_no)
where salaries.to_date = '9999-01-01' and titles.to_date = '9999-01-01'
group by title
order by avg(salary) desc;


--
-- Group By 절에 여러 컬럼 사용
--

-- 예제5: 현재 직책별로 평균 연봉과 인원수를 구하되 직책별로 인원이 
--          100명 이상인 직책만 출력하세요.

select title, avg(salary) as '평균 연봉', count(*) as '인원 수'
from titles join salaries using (emp_no)
where titles.to_date = '9999-01-01' and salaries.to_date='9999-01-01'
group by title
having count(*) >= 100;


-- 예제6: 현재 부서별로 현재 직책이 Engineer인 직원들에 대해서만 
--         평균급여를 구하세요.

select avg(salary) as '평균 급여'
from (((employees join titles using (emp_no)) join dept_emp using (emp_no)) join departments using (dept_no)) join salaries using (emp_no)
where titles.to_date = '9999-01-01' and dept_emp.to_date = '9999-01-01' and salaries.to_date = '9999-01-01'
		and title = 'Engineer';
        
 
-- 예제7: 현재 직책별로 급여의 총합을 구하되 Engineer직책은 제외하세요
--         단, 총합이 2,000,000,000이상인 직책만 나타내며 급여총합에
--         대해서 내림차순(DESC)로 정렬하세요

select title, sum(salary)
from titles join salaries using (emp_no)
where titles.to_date = '9999-01-01' and salaries.to_date = '9999-01-01'
		and title != 'Engineer'
group by title
having sum(salary) >= 2000000000
order by sum(salary) desc;
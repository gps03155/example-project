-- 서브쿼리(SUBQUERY) SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?

select count(*) as '인원 수'
from employees join salaries using (emp_no)
where to_date = '9999-01-01' and salary > (select avg(salary)
											from salaries
											where to_date = '9999-01-01');


-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 
-- 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 

select emp_no, concat(first_name, ' ', last_name) as '이름', dept_name, salary
from ((employees join dept_emp using (emp_no)) join departments using (dept_no)) join salaries using (emp_no)
where dept_emp.to_date = '9999-01-01' and salaries.to_date = '9999-01-01'
      and (dept_name, salary) in (select dept_name, max(salary)
                                  from ((employees join dept_emp using (emp_no)) join departments using (dept_no)) join salaries using (emp_no)
		                          where dept_emp.to_date = '9999-01-01' and salaries.to_date = '9999-01-01'
                                  group by dept_name)
order by salary desc;


-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 

select e.emp_no, concat(first_name, ' ', last_name) as '이름', s.salary
from ((employees e join dept_emp d on e.emp_no = d.emp_no)
      join (select d.dept_no as 'avg_dept_no', avg(s.salary) as 'avg_salary'
            from (employees e join dept_emp d on e.emp_no = d.emp_no) join salaries s on e.emp_no = s.emp_no
		    where d.to_date = '9999-01-01' and s.to_date = '9999-01-01'
			group by d.dept_no) result on d.dept_no = result.avg_dept_no) join salaries s on e.emp_no = s.emp_no
where d.to_date = '9999-01-01' and s.to_date = '9999-01-01'
      and s.salary > avg_salary;


-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.

select e.emp_no, concat(e.first_name, ' ', e.last_name) as '이름', manager_name, dept_name
from ((employees e join dept_emp d on e.emp_no = d.emp_no) join departments d2 on d.dept_no = d2.dept_no)
      join (select dept_no as 'manager_dept_no', concat(first_name, ' ', last_name) as 'manager_name' 
			from employees e join dept_manager d on e.emp_no = d.emp_no
			where d.to_date = '9999-01-01') manager on d.dept_no = manager.manager_dept_no
where d.to_date = '9999-01-01';


-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.

select e.emp_no, concat(e.first_name, ' ', e.last_name) as '이름', t.title, s.salary
from ((employees e join titles t on e.emp_no = t.emp_no) join dept_emp d on e.emp_no = d.emp_no) join salaries s on e.emp_no = s.emp_no
where t.to_date = '9999-01-01' and d.to_date = '9999-01-01' and s.to_date = '9999-01-01'
      and d.dept_no = (select d.dept_no -- , round(avg(s.salary)) as 'avg_salary'
				       from salaries s join dept_emp d on s.emp_no = d.emp_no
				       where s.to_date = '9999-01-01' and d.to_date = '9999-01-01'
                       group by d.dept_no
                       having round(avg(s.salary)) = (select round(max(avg_salary))
												      from (select dept_no as 'avg_dept_no', avg(s.salary) as 'avg_salary'
							                          from salaries s join dept_emp d on s.emp_no = d.emp_no
													  where d.to_date = '9999-01-01' and s.to_date = '9999-01-01'
						                              group by d.dept_no) result))
order by s.salary desc;


-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 

select dept_no
from salaries s join dept_emp d on s.emp_no = d.emp_no
where s.to_date = '9999-01-01' and d.to_date = '9999-01-01'
group by d.dept_no
having round(avg(s.salary)) = (select round(max(avg_salary))
                               from (select d.dept_no, avg(s.salary) as 'avg_salary'
                                     from salaries s join dept_emp d on s.emp_no = d.emp_no
                                     where s.to_date = '9999-01-01' and d.to_date = '9999-01-01'
						             group by d.dept_no) result);


-- 문제7.
-- 평균 연봉이 가장 높은 직책?

select t.title
from salaries s join titles t on s.emp_no = t.emp_no
where s.to_date = '9999-01-01' and t.to_date = '9999-01-01'
group by t.title
having round(avg(s.salary)) = (select round(max(avg_salary))
                               from (select avg(salary) as 'avg_salary'
                                     from salaries s join titles t on s.emp_no = t.emp_no
                                     where s.to_date = '9999-01-01' and t.to_date = '9999-01-01'
                                     group by t.title) result);



-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.

select d2.dept_name, concat(e.first_name, ' ', e.last_name) as '사원이름', s.salary, manager.manager_name, manager.manager_salary
from (((employees e join salaries s on e.emp_no = s.emp_no) join dept_emp d on e.emp_no = d.emp_no) join departments d2 on d.dept_no = d2.dept_no)
      join (select d.dept_no as 'manager_dept_no', e.emp_no as 'manager_emp_no', s.salary as 'manager_salary', concat(e.first_name, ' ', e.last_name) as 'manager_name'
            from (employees e join dept_manager d on e.emp_no = d.emp_no) join salaries s on e.emp_no = s.emp_no
            where d.to_date = '9999-01-01' and s.to_date = '9999-01-01') manager on d.dept_no = manager.manager_dept_no
where s.to_date = '9999-01-01' and d.to_date = '9999-01-01'
      and s.salary > manager.manager_salary;
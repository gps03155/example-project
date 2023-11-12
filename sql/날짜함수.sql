select curdate(), current_date;

select curtime(), current_time;

-- now()는 쿼리가 실행 될 때 시간이 계속 유지 --
-- sysdate()는 출력될 때마다 현재 시간으로 세팅 --
select now(), sysdate();

select emp_no, now()
from employees; -- now : 쿼리가 시작한 시간이 다 똑같음 --

select emp_no, sysdate()
from employees; -- sysdate : 쿼리를 부를때 시간이 레코드 별로 달라짐 --


-- formatting --
select first_name, date_format(hire_date, '%Y-%m-%d %h:%i:%s') -- y : 2자리 , Y : 4자리
from employees;


-- 각 직원들에 대해 직원의 이름과 근무월수를 출력하라. --
select first_name, concat(cast(period_diff(date_format(curdate(), '%Y%m'), date_format(hire_date, '%Y%m')) as char), '개월')
from employees;


-- 입사 후 6개월 뒤에 정직원으로 발령이 된다. 발령날은 언제인가 --
-- interval year, month, week, day --
select first_name, hire_date, date_add(hire_date, interval 6 month)
from employees;

select date_add(date_format(cast('19961208' as date), '%Y%m%d'), interval 100 day);


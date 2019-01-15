select abs(10), abs(-10);

select mod(234, 10), 254 % 10;

select floor(1.23), floor(-1.23);

select ceil(3.14), ceiling(3.14),
		ceil(-3.14), ceiling(-3.14);
        
-- 반올림 --
select round(-1.23), round(-1.58), round(1.58);

select round(1.298, 1), round(1.298, 0); -- 소수점 뒷자리 지정한 부분에서 반올림 --


-- 제곱 --
select pow(2, 2), power(2, -2);


select sign(-10), sign(0), sign(10); -- 음수 : -1, 0 : 0, 양수 : 1 --

select greatest(5, 4, 3, 2, 1),
		greatest(3.14, 4.26, 5.8),
        greatest('ABCD', 'ABCDF', 'AZ');

select least(5, 4, 3, 2, 1),
		least(3.14, 4.26, 5.8),
        least('ABCD', 'ABCDF', 'AZ');
        

	
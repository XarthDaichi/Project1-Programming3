CREATE DATABASE SISE;
use SISE;
create table Branch_Office (
    code varchar(10) not null,
    reference varchar(30) not null,
    zonage_percentage double,
    x int,
    y int,
    Primary Key (code)
);

create table Employee (
    id varchar(10) not null,
    employee_name varchar(30) not null,
    phone varchar(10) not null,
    base_salary double,
    branch_office varchar(10) not null,
    Primary Key (id)
);
ALTER TABLE Employee ADD Foreign Key (branch_office) REFERENCES Branch_Office(code);
insert into branch_office 
	(code, reference, zonage_percentage, x, y) 
	values('001', 'Sabana', 2, 100, 100);
insert into branch_office
	(code, reference, zonage_percentage, x, y)
	values('002', 'Nicoya', 3, 50, 50);
insert into employee
	(id, employee_name, phone, base_salary, branch_office)
	values('901150326', 'Diego', '72044757', 12, '001');_
update employee set phone='42222', base_salary=14 where id='901150326';

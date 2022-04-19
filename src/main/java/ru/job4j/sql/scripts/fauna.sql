create table if not exists fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values
('чупакабра', 12000, '1939-03-01'),
('кин-конг', 30, '1943-02-21'),
('чебурашка', 30, '1953-01-11'),
('nofish', 20000, null),
('afishа', 30, '2016-09-07'),
('rexegc', 400, '1001-09-01'),
('чупfish', 30, '1682-11-30'),
('жывотное', 30, '1995-04-03'),
('qwerty', 10500, null),
('fish', 3, '2010-04-21'),
(null, null, null),
('чук', 60, '2003-09-25');

select * from fauna where name like '%fish%';

select * from fauna where avg_age > 10000  and  avg_age < 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '01.01.1950';

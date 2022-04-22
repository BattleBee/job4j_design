-- отработка присоединения(inner join), группировки и агрегатных функций

create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

INSERT INTO devices (name, price) values
('телефон', 2500.25),
('часы', 1200.73),
('ноутбук', 5120.00),
('планшет', 4000.19),
('телефон кнопочный', 1200.75),
('наушники беспроводные', 1000.00);

INSERT INTO people (name) values
('Alex'),
('Sergey'),
('Pasha');

insert into devices_people (device_id, people_id) values
(1, 1), (3, 1), (6, 1),
(1, 2), (2, 2), (4, 2), (6, 2),
(5, 3), (4, 3), (6, 3);

-- средняя цена всех девайсов в таблице
select avg(price) from devices;

-- средняя цена девайсов у каждого персонажа
select p.name, avg(d.price)
from people as p
inner join devices_people dp on p.id= dp.people_id
inner join devices as d on dp.device_id = d.id
group by p.name;

-- средняя цена девайсов у каждого персонажа если она больше 5000
select p.name, avg(d.price)
from people as p
inner join devices_people dp on p.id= dp.people_id
inner join devices as d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;

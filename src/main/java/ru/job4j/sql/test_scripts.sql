-- Создать таблицу с заданными полями (наименование, тип, размер, запрет нулевых значений)
create table persons (
	id serial primary key,
	first_name varchar (50) not null,
	second_name varchar(50) not null,
	age int not null,
	address text not null
)
-- Добавить значение в указанные поля
insert into persons(first_name, second_name, age, address) values('Jon', 'Snow', 17, 'Winterfell');

-- Изменить значение поля
update persons set second_name = 'Targaryen', age = 23, address= 'the Wall';

-- добавить колонку в таблицу
alter table persons add column age int not null;

-- Показать выборку значений таблицы
select * from persons;

-- Удалить все значения из таблиц
delete from persons;



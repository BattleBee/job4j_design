
create table persons (
	id serial primary key,
	first_name varchar (50) not null,
	second_name varchar(50) not null,
	age int not null,
	address text not null
)

insert into persons(first_name, second_name, age, address) values('Jon', 'Snow', 17, 'Winterfell');

update persons set second_name = 'Targaryen', age = 23, address= 'the Wall';

select * from persons;

delete from persons;



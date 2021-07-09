-- SQL скрипт create.sql создающий таблицы для хранения структуры системы заявок.

CREATE TABLE users (
                        id serial primary key,
                        first_name varchar (50) NOT NULL,
                        second_name varchar(50) NOT NULL,
                        role_id int references role(id)
);

CREATE TABLE role (
                      id serial primary key,
                      name varchar (50) NOT NULL
);

CREATE TABLE rules (
                      id serial primary key,
                      name varchar (50) NOT NULL,
                      description text NOT NULL
);

CREATE TABLE role_rules(
                            id serial primary key,
                            role_id int references role(id),
                            rules_id int references rules(id)
);

CREATE TABLE item (
                       id serial primary key,
                       users_id int references users(id),
                       category_id int references category(id),
                       state_id int references state(id)
);

CREATE TABLE comments  (
                       id serial primary key,
                       content text NOT NULL,
                       item_id int references item(id)
);

CREATE TABLE attaches  (
                           id serial primary key,
                           item_id int references item(id)
);

CREATE TABLE category  (
                           id serial primary key,
                           name varchar (50) NOT NULL
);

CREATE TABLE state  (
                           id serial primary key,
                           name varchar (50) NOT NULL
);

DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;

CREATE TABLE author (
                        id serial primary key,
                        first_name varchar (50) NOT NULL,
                        second_name varchar(50) NOT NULL

);

CREATE TABLE book (
                         id serial primary key,
                         title text NOT NULL,
                         isbn varchar(50) NOT NULL,
                         book_id int references book(id)
 );


INSERT INTO author (first_name, second_name)
VALUES
       ('Лев', 'Толстой'),
       ('Александр', 'Пушкин'),
       ('Агния', 'Барто');

INSERT INTO book (title, isbn, book_id)
VALUES
        ('Война и мир', '123AB1345', 1),
        ('Анна Каренина', '54ЩB13985', 1),
        ('Капитанская дочка', '8793AУ1345', 2);

-- DROP TABLE book;
-- DROP TABLE author;



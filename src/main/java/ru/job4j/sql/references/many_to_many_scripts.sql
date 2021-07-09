
DROP TABLE IF EXISTS actor_movie; -- удалять таблицу с зависимостями первой, чтоб не выдаало ошибку
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS actor;

CREATE TABLE movie (
                      id serial primary key,
                      name varchar(250) NOT NULL
);


CREATE TABLE actor (
                        id serial primary key,
                        first_name varchar (50) NOT NULL,
                        second_name varchar(50) NOT NULL
);

CREATE TABLE actor_movie(
                            id serial primary key,
                            actor_id int references actor(id),
                            movie_id int references movie(id)
);

INSERT INTO movie(name)
VALUES
       ('Я, робот'),
       ('Я - легенда'),
       ('Титаник');


INSERT INTO actor(first_name, second_name)
VALUES
       ('Уилл', 'Смит'),
       ('Леонардо', 'ДиКаприо');

INSERT INTO actor_movie (actor_id, movie_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3);


-- DROP TABLE actor_movie;
-- DROP TABLE actor;
-- DROP TABLE movie;
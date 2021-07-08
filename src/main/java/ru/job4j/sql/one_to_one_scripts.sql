DROP TABLE IF EXISTS passport;
DROP TABLE IF EXISTS person;

CREATE TABLE person (
                        id serial primary key,
                        first_name varchar (50) NOT NULL,
                        second_name varchar(50) NOT NULL

);

CREATE TABLE passport (
                      id serial primary key,
                      number int NOT NULL,
                      issuing_authority varchar(50) NOT NULL,
                      person_id int references person(id)
);


INSERT INTO person (first_name, second_name)
VALUES
('Иван', 'Петров'),
('Сергей', 'Иванов'),
('Ирина', 'Орлова');

INSERT INTO passport (number, issuing_authority, person_id)
VALUES
(1345876, 'УФМС - 1', 1),
(0983469, 'УФМС - 2', 2),
(9871263, 'УФМС - 3', 3);

-- DROP TABLE  passport;
-- DROP TABLE  person;
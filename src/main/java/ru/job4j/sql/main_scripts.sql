
DROP TABLE IF EXISTS persons; -- удалить указанную таблицу если она уже существует

/* Создать таблицу с заданными полями (наименование поля, тип, размер, запрет нулевых значений) */
CREATE TABLE persons (
	id serial primary key,
	first_name varchar (50) NOT NULL,
	second_name varchar(50) NOT NULL,
	age int NOT NULL,
	address text NOT NULL
)
/* Добавить значение в указанные поля */
INSERT INTO persons(first_name, second_name, age, address)
VALUES
    ('Jon', 'Snow', 17, 'Winterfell');

/* Изменить значение поля */
UPDATE persons SET second_name = 'Targaryen', age = 23, address= 'the Wall';

/* добавить колонку в таблицу */
ALTER TABLE persons ADD COLUMN age int NOT NULL;

SELECT first_name, age, address
FROM persons; -- Показать выборку выбранных значений таблицы

SELECT DISTINCT address -- Выборка уникальных  значений таблицы
FROM persons; -- выведенные значения не повторяются

SELECT * FROM persons; -- Показать выборку всех значений таблицы

select table_name, column_name -- Получение списка таблиц базы данных и ее полей
from information_schema.columns
where table_schema='public'

/*
DELETE FROM persons; -- Удалить все значения из указанной таблицы
DROP TABLE persons; -- удалить указанную таблицу
*/

-- Пример однострочного комментария

/* Комментарий свозможностью коментирования
    в несколько строк  */



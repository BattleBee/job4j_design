-- отработка присоединения (inner join) альясов и псевдонимов столбцов

CREATE TABLE IF NOT EXISTS cars
(
    id SERIAL PRIMARY KEY,
    brand  VARCHAR(50) NOT NULL,
    color VARCHAR(50) NOT NULL,
    year int NOT NULL,
    mileage int NOT NULL

);

INSERT INTO cars (brand, color, year, mileage )  VALUES
('BMW', 'BLACK', 2015, 120000),
('MAZDA', 'RED', 2019, 50000),
('TOYOTA', 'BLUE', 2021, 30000);


CREATE TABLE IF NOT EXISTS details
(
    id SERIAL PRIMARY KEY,
    name  VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,
    car_id int references cars(id)
);

INSERT INTO details (id, name, country, car_id)  VALUES
(1, 'Лобовое стекло', 'Россия', 2),
(2, 'Двигатель', 'Германия', 1),
(3, 'Дверь', 'Россия', 3),
(4, 'Колесо', 'Финляндия', 1),
(5, 'Аккумулятор', 'Япония', 3);

-- используем псевдоним столбцов
select d.name, d.country, c.brand, c.color, c.year
from details as d
join cars as c
on d.car_id = c.id;

-- используем псевдоним столбцов и присваиваем столбцам  новое имя при выводе
select d.id as Номер_детали, d.name as Наименование, d.country as Страна_производитель,
c.brand as Установлено_на_автомобиль,
c.color as Цвет, c.year as Год_выпуска
from details as d
join cars as c
on d.car_id = c.id;

-- объединяем данные в одной ячейке используя оператор конкатенации и задаем разделитель
select d.id "Номер детали", d.name Наименование, d.country "Страна производитель",
c.brand ||'(' || c.year || ') - ' || c.color as "Установлено на"
from details as d
join cars as c
on d.car_id = c.id;

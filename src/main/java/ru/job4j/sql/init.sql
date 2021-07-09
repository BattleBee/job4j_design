-- SQL скрипт init.sql инициализирующий создание новой базы данных.

CREATE DATABASE database1
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
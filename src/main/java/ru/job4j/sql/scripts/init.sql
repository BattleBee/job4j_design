-- SQL скрипт init.sql инициализирующий создание новой базы данных.

-- DROP DATABASE idea_db;

CREATE DATABASE idea_db
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
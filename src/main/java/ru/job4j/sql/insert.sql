
-- SQL скрипт insert.sql заполняющий начальные данные для системы заявок.

INSERT INTO role (name)
VALUES
('писатель');

INSERT INTO category (name)
VALUES
('category 1');

INSERT INTO state (name)
VALUES
('state 1');

INSERT INTO users (first_name, second_name, role_id)
VALUES
('Агния', 'Барто', 1);

INSERT INTO rules (name, description)
VALUES
('rule 1', 'write');

INSERT INTO role_rules (role_id, rules_id)
VALUES
(1, 1);

INSERT INTO item (users_id, category_id, state_id)
VALUES
(1, 1, 1);

INSERT INTO comments (content, item_id)
VALUES
('bla-bla-bla', 1);

INSERT INTO attaches (item_id)
VALUES
(1);


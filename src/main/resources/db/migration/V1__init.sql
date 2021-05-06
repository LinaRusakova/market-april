CREATE TABLE categories (id bigserial PRIMARY KEY, title VARCHAR(255));
INSERT INTO categories (title) VALUES
('Food');


CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int, category_id bigint references  categories(id));
INSERT INTO products (title, price, category_id) VALUES
('Bread', 25, 1),
('Milk', 30, 1),
('Cacao', 80, 1);

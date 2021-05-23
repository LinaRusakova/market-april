CREATE TABLE categories
(
    id    bigserial PRIMARY KEY,
    title VARCHAR(255)
);
INSERT INTO categories (title)
VALUES ('Food');


CREATE TABLE products
(
    id          bigserial PRIMARY KEY,
    title       VARCHAR(255),
    price       numeric (8, 2),
    category_id bigint references categories (id)
);
INSERT INTO products (title, price, category_id)
VALUES ('Bread', 25.43, 1),
       ('Milk', 30.33, 1),
       ('Milk Chocolate', 84.99, 1),
       ('Cacao', 80.11, 1);

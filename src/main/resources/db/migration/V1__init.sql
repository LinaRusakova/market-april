CREATE TABLE categories
(
    id         bigserial PRIMARY KEY,
    title      VARCHAR(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
INSERT INTO categories (title)
VALUES ('Food');


CREATE TABLE products
(
    id          bigserial PRIMARY KEY,
    title       VARCHAR(255),
    price       numeric(8, 2) not null,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);
INSERT INTO products (title, price, category_id)
VALUES ('Bread', 25.43, 1),
       ('Milk', 30.33, 1),
       ('Milk Chocolate', 84.99, 1),
       ('Cacao', 80.11, 1);

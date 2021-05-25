create table users
(
    id         bigserial primary key,
    username   varchar(30) not null unique,
    password   varchar(80) not null,
    email      varchar(80) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2y$12$kb8d6oyKIFU2AdbUQBOXbuUJurg0GoWvSJNgMcpYuwADOK2omCZ0u', 'user@gmail.com'),
       ('admin', '$2y$12$kb8d6oyKIFU2AdbUQBOXbuUJurg0GoWvSJNgMcpYuwADOK2omCZ0u', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

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

CREATE TABLE order_items
(
    id                bigserial PRIMARY KEY,
    product_id        bigint references products (id),
    quantity          int,
    price_per_product numeric(8, 2) not null,
    price             numeric(8, 2) not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

CREATE TABLE users_orders
(
    user_id bigint not null references users (id),
    order_id bigint not null references order_items (id),
    primary key (user_id, order_id)
);


CREATE TABLE IF NOT EXISTS categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    active BOOLEAN NOT NULL
);

INSERT INTO categories (name, active)
VALUES
    ('Bebidas', true),
    ('Comida', true),
    ('Limpieza', true),
    ('Electrónica', true),
    ('Librería', true),
    ('Vestimenta', true),
    ('Ferretería', true),
    ('Papelería', true),
    ('Deporte', true),
    ('Panadería', true)
ON CONFLICT DO NOTHING;


CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    active BOOLEAN,
    cellphone VARCHAR(255),
    email VARCHAR(255),
    member_identification VARCHAR(255),
    name VARCHAR(255),
    surname VARCHAR(255),
    user_type VARCHAR(255) NOT NULL
);

INSERT INTO users (active, member_identification, name, surname, cellphone, email, user_type)
VALUES
    (true, '00000000', 'Admin', 'Sistema', '000000000', 'admin@example.com', 'ADMIN'),
    (true, '12345678', 'Empleado', 'Sistema', '123456789', 'employee@example.com','EMPLOYEE'),
    (true, '87654321', 'Cliente', 'Prueba', '987654321', 'customer@example.com', 'CUSTOMER')
ON CONFLICT DO NOTHING;

CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    price NUMERIC(38,2),
    category_id BIGINT,
    active BOOLEAN DEFAULT true NOT NULL,
    CONSTRAINT fkog2rp4qthbtt2lfyhfo32lsw9
        FOREIGN KEY (category_id) REFERENCES categories(id)
);

INSERT INTO products (name, price, category_id, active)
VALUES
    ('Refresco 1L', 120.00, 1, true),
    ('Hamburguesa', 250.00, 2, true),
    ('Detergente', 180.00, 3, true)
ON CONFLICT DO NOTHING;

CREATE TABLE IF NOT EXISTS discounts (
    id BIGSERIAL PRIMARY KEY,
    active BOOLEAN DEFAULT false,
    amount DOUBLE PRECISION NOT NULL,
    description VARCHAR(255) NOT NULL,
    limit_amount DOUBLE PRECISION,
    category_id BIGINT,
    product_id BIGINT,
    discount_type VARCHAR(255) NOT NULL,
    CONSTRAINT discounts_discount_type_check
        CHECK (discount_type IN ('GENERAL', 'EMPLOYEE', 'CUSTOMER')),
    CONSTRAINT fkgi3f12a2ruhvyt0uid2v7qu9x
        FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT fke3tqxsyxv7qcy8uvf2lns1hx8
        FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO discounts (active, amount, description, discount_type, product_id)
VALUES
    (true, 10, 'Descuento general', 'GENERAL', 2)
ON CONFLICT DO NOTHING;

CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    date TIMESTAMP,
    state VARCHAR(255),
    total_discount NUMERIC(38,2),
    total_price NUMERIC(38,2),
    user_id BIGINT,
    cancelated BOOLEAN,
    CONSTRAINT fk32ql8ubntj5uh44ph9659tiih
        FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS order_items (
    id BIGSERIAL PRIMARY KEY,
    quantity INTEGER,
    sub_total NUMERIC(38,2),
    unit_price NUMERIC(38,2),
    order_id BIGINT,
    product_id BIGINT,
    cancelated BOOLEAN,
    CONSTRAINT fkbioxgbv59vetrxe0ejfubep1w
        FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fkocimc7dtr037rh4ls4l95nlfi
        FOREIGN KEY (product_id) REFERENCES products(id)
);


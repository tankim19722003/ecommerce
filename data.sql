CREATE TABLE shipping_providers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    shipping_speed VARCHAR(50),
    shipping_cost INT NOT NULL,
    created_at DATE NOT NULL,
    phone VARCHAR(12),
    email VARCHAR(255)
);
ALTER TABLE shipping_providers
ADD COLUMN password VARCHAR(255) NOT NULL;

ALTER TABLE shipping_providers
ADD COLUMN status varchar(30) NOT NULL DEFAULT 'pending';

ALTER TABLE shipping_providers
ADD CONSTRAINT status_check CHECK (status IN ('pending', 'complete', 'processing', 'canceled'));


ALTER TABLE shipping_providers
RENAME COLUMN name TO account;


CREATE TABLE products_rejections (
    id SERIAL PRIMARY KEY,
    rejected_reason TEXT NOT NULL,
    rejected_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE images (
    id SERIAL PRIMARY KEY,
    image_name TEXT NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    fullname varchar(255) NOT NULL,
    account varchar(255) UNIQUE NOT NULL,
    password varchar(255) NOT NULL,
    address TEXT,
    email varchar(255) UNIQUE,
    gender BOOLEAN ,
    birthdate DATE,
    avatar TEXT
);

CREATE TABLE shop_banned (
    id SERIAL PRIMARY KEY,
    ban_reason TEXT NOT NULL,
    ban_start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ban_end_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    bank_name varchar(255) NOT NULL,
    bank_account varchar(50) NOT NULL,
    account_holder_name varchar(256) NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    order_date TIMESTAMP NOT NULL,
    total_price INTEGER NOT NULL,
    payment_method varchar(50) NOT NULL,
    payment_status varchar(50) NOT NULL,
    discount_amount INTEGER DEFAULT 0,
    shipping_cost INTEGER DEFAULT 0,
    shipping_address TEXT NOT NULL,
    shipping_method varchar(50) NOT NULL,
    notes TEXT,
    coupon_code varchar(50),
    expected_receive_date DATE,
    user_id INTEGER NOT NULL,
    shipping_provider_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (shipping_provider_id) REFERENCES shipping_providers(id) ON UPDATE CASCADE
);


CREATE TABLE shops (
    id SERIAL PRIMARY KEY,
    description TEXT,
    name varchar(256) NOT NULL,
    created_at DATE NOT NULL,
    logo TEXT,
    address TEXT NOT NULL,
    phone_number varchar(256) UNIQUE NOT NULL,
    status varchar(30) NOT NULL
);


CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name varchar(256) NOT NULL UNIQUE,
    created_at DATE NOT NULL,
    description TEXT
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL,
    description TEXT,
    price INTEGER NOT NULL default 0,
    quantity INTEGER NOT NULL default 0,
    color varchar(30),
    size CHAR(1),
    rating INTEGER,
    thumbnail varchar(255),
    shop_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    FOREIGN KEY (shop_id) REFERENCES shops(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE product_discounts (
    product_id INTEGER PRIMARY KEY,
    discount_percent FLOAT NOT NULL,
    date_start TIMESTAMP NOT NULL,
    date_end TIMESTAMP NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE carts (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    color varchar(30),
    size char(1),
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE order_details (
    id SERIAL PRIMARY KEY,
    quantity INTEGER NOT NULL default 1,
    price INTEGER NOT NULL default 0,
    color VARCHAR(30),
    size CHAR(1),
    total_price INTEGER NOT NULL default 0,
    discount_percent REAL,
    order_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    CONSTRAINT fk_order_details_orders FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    CONSTRAINT fk_order_details_products FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE feedback (
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE,
    user_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    CONSTRAINT fk_feedback_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_feedback_products FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

ALTER TABLE shops
ADD COLUMN user_id INTEGER NOT NULL,
ADD CONSTRAINT fk_shops_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

CREATE TABLE coupons (
    id SERIAL PRIMARY KEY,
    code TEXT NOT NULL UNIQUE CHECK (LENGTH(code) <= 50),
    description TEXT,
    discount_value FLOAT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    minimum_order_value INTEGER NOT NULL,
    shop_id INTEGER NOT NULL,
    CONSTRAINT fk_coupons_shops FOREIGN KEY (shop_id) REFERENCES shops(id) ON DELETE CASCADE
);

CREATE TABLE user_banned (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    ban_reason TEXT NOT NULL,
    ban_start_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ban_end_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_banned_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

ALTER TABLE shop_banned
ADD COLUMN shop_id INTEGER NOT NULL,
ADD CONSTRAINT fk_shop_banned_shops FOREIGN KEY (shop_id) REFERENCES shops(id) ON DELETE CASCADE;

CREATE TABLE shop_rejections (
    id SERIAL PRIMARY KEY,
    rejected_reason TEXT NOT NULL,
    rejected_date TIMESTAMP NOT NULL,
    shop_id INTEGER NOT NULL,
    CONSTRAINT fk_shop_rejections_shops FOREIGN KEY (shop_id) REFERENCES shops(id) ON DELETE CASCADE
);

CREATE TABLE shipping_provider_rejects (
    id SERIAL PRIMARY KEY,
    rejected_reason TEXT NOT NULL,
    rejected_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    shipping_provider_id INTEGER NOT NULL,
    CONSTRAINT fk_shipping_provider
        FOREIGN KEY (shipping_provider_id)
        REFERENCES shipping_providers(id)
        ON DELETE CASCADE
);






CREATE TABLE category
(
    id   VARCHAR(50) NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id VARCHAR(50) NOT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE price
(
    id       VARCHAR(50) NOT NULL,
    currency VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    CONSTRAINT pk_price PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            VARCHAR(50) NOT NULL,
    title         VARCHAR(255) NULL,
    category_id   VARCHAR(50) NULL,
    `description` VARCHAR(255) NULL,
    image         VARCHAR(255) NULL,
    price_id      VARCHAR(50) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE product_orders
(
    orders_id   VARCHAR(50) NOT NULL,
    products_id VARCHAR(50) NOT NULL
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_PRICE FOREIGN KEY (price_id) REFERENCES price (id);

ALTER TABLE product_orders
    ADD CONSTRAINT fk_proord_on_orders FOREIGN KEY (orders_id) REFERENCES orders (id);

ALTER TABLE product_orders
    ADD CONSTRAINT fk_proord_on_product FOREIGN KEY (products_id) REFERENCES product (id);
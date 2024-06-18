CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime NULL,
    modified_at   datetime NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    imageurl      VARCHAR(255) NULL,
    rating_id     BIGINT NULL,
    price DOUBLE NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE rating
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime NULL,
    modified_at datetime NULL,
    rate DOUBLE NOT NULL,
    count       INT NOT NULL,
    CONSTRAINT pk_rating PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT uc_product_rating UNIQUE (rating_id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_RATING FOREIGN KEY (rating_id) REFERENCES rating (id);
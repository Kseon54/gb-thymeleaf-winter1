CREATE TABLE PRODUCT
(
    ID                 BIGSERIAL      NOT NULL PRIMARY KEY,
    TITLE              VARCHAR(255)   NOT NULL,
    COST               DECIMAL(10, 2) NOT NULL,
    MANUFACTURE_DATE   DATE           NOT NULL,
    VERSION            INT            NOT NULL DEFAULT 0,
    CREATED_BY         VARCHAR(255),
    CREATED_DATE       TIMESTAMP,
    LAST_MODIFIED_BY   VARCHAR(255),
    LAST_MODIFIED_DATE TIMESTAMP,
    STATUS             VARCHAR(20)    NOT NULL DEFAULT 'ACTIVE',

    UNIQUE (TITLE)
);

CREATE TABLE CART
(
    ID                 BIGSERIAL   NOT NULL PRIMARY KEY,
    VERSION            INT         NOT NULL DEFAULT 0,
    CREATED_BY         VARCHAR(255),
    CREATED_DATE       TIMESTAMP,
    LAST_MODIFIED_BY   VARCHAR(255),
    LAST_MODIFIED_DATE TIMESTAMP,
    STATUS             VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'
);

CREATE TABLE CART_PRODUCT
(
    CART_ID    BIGINT NOT NULL REFERENCES CART,
    PRODUCT_ID BIGINT NOT NULL REFERENCES PRODUCT,

    PRIMARY KEY (CART_ID,PRODUCT_ID)
);

-- CREATE TABLE Product
-- (
--     id            BIGINT AUTO_INCREMENT PRIMARY KEY,
--     productId     VARCHAR(50) PRIMARY KEY,
--     name          VARCHAR(100),
--     description   VARCHAR(255),
--     price         NUMERIC(10, 2),
--     stockQuantity INT
-- );
--
-- CREATE TABLE Address
-- (
--     id        BIGINT AUTO_INCREMENT PRIMARY KEY,
--     addressId VARCHAR(255),
--     street    VARCHAR(255),
--     city      VARCHAR(100),
--     state     VARCHAR(100),
--     zipCode   VARCHAR(20),
--     country   VARCHAR(100)
-- );
--
-- CREATE TABLE CUSTOMER
-- (
--     id                BIGINT AUTO_INCREMENT PRIMARY KEY,
--     customerId        VARCHAR(100),
--     name              VARCHAR(100),
--     email             VARCHAR(100),
--     shippingAddressId BIGINT,
--     billingAddressId  BIGINT,
--     FOREIGN KEY (shippingAddressId) REFERENCES Address (id),
--     FOREIGN KEY (billingAddressId) REFERENCES Address (id)
-- );
INSERT INTO Address (id, addressId, street, city, state, zipCode, country)
VALUES (1, 'A001', '123 Main St', 'Springfield', 'IL', '62701', 'USA');

INSERT INTO Address (id, addressId, street, city, state, zipCode, country)
VALUES (2, 'A002', '456 Elm St', 'Columbus', 'OH', '43215', 'USA');

INSERT INTO Customer (id, customerId, name, email, shippingAddressId, billingAddressId)
VALUES (1, 'C001', 'John Doe', 'john.doe@example.com', 1, 1);

INSERT INTO Customer (id, customerId, name, email, shippingAddressId, billingAddressId)
VALUES (2, 'C002', 'Jane Smith', 'jane.smith@example.com', 2, 2);


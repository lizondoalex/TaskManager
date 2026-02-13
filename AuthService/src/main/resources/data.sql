CREATE TABLE roles (
                       role_id BIGINT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL UNIQUE,
                       description VARCHAR(255)
);
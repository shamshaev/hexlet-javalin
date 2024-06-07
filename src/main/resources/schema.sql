DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name varchar(255) NOT NULL,
    email varchar(50) NOT NULL,
    password varchar(50) NOT NULL
);

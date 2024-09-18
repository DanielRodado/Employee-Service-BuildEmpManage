CREATE TABLE IF NOT EXISTS employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    role VARCHAR(30) NOT NULL,
    building VARCHAR(5),
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);
CREATE DATABASE cinema;

CREATE TABLE users (
	full_name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
	phone VARCHAR(13) NOT NULL UNIQUE
);

CREATE TABLE cinema (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	rows_amount INT DEFAULT 15,
	places_per_row_amount INT DEFAULT 13,
	CHECK (places_per_row_amount >= 0)
);

CREATE TABLE movie (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE movie_show (
	id SERIAL PRIMARY KEY,
	movie_id INT NOT NULL REFERENCES movie(id),
	cinema_id INT NOT NULL REFERENCES cinema(id),
	time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	price INT NOT NULL,
	CHECK (price > 0)
);

CREATE TABLE ticket_statuses (
	id SERIAL PRIMARY KEY,
	name VARCHAR(10) NOT NULL
);

INSERT INTO ticket_statuses (name) VALUES
('NEW'), ('PROCESSING'), ('DONE'), ('FAILED');

CREATE TABLE payment_statuses (
	id SERIAL PRIMARY KEY,
	name VARCHAR(10) NOT NULL
);

INSERT into payment_statuses (name) VALUES
('NEW'), ('DONE'), ('FAILED');

CREATE TABLE payments (
	id SERIAL PRIMARY KEY,
	amount INT NOT NULL,
	card VARCHAR(16) NOT NULL,
	status_id INT DEFAULT 1
);

CREATE TABLE ticket (
	movie_show_id INT NOT NULL REFERENCES movie_show(id),
	row INT NOT NULL,
	place INT NOT NULL,
	status_id INT DEFAULT 1,
	creation_date TIMESTAMP WITHOUT TIME ZONE DEFAULT current_timestamp,
	modification_date TIMESTAMP WITHOUT TIME ZONE DEFAULT current_timestamp,
	payment_id INT REFERENCES payments (id)
);
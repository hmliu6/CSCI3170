DROP DATABASE IF EXISTS project;
CREATE DATABASE project;
USE project;

DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS user CASCADE;
DROP TABLE IF EXISTS book CASCADE;
DROP TABLE IF EXISTS copy CASCADE;
DROP TABLE IF EXISTS checkout_record CASCADE;
DROP TABLE IF EXISTS author CASCADE;

CREATE TABLE category(
    category_id integer primary key,
    loan_period integer not null,
    max_books integer not null);

CREATE TABLE user(
    user_id integer primary key,
    name varchar(30) not null,
    address varchar(100) not null,
    category_id integer not null,
    FOREIGN KEY(category_id) REFERENCES category(category_id));

CREATE TABLE book(
    call_number integer primary key,
    title varchar(100) not null,
    publish_date varchar(10) not null);

CREATE TABLE copy(
    call_number integer not null,
    copy_number integer not null,
    PRIMARY KEY(call_number, copy_number),
    FOREIGN KEY(call_number) REFERENCES book(call_number));

CREATE TABLE checkout_record(
    user_id integer not null,
    call_number integer not null,
    copy_number integer not null,
    checkout_date varchar(10) not null,
    return_date varchar(10),
    PRIMARY KEY(user_id, call_number, copy_number, checkout_date),
    FOREIGN KEY(user_id) REFERENCES user(user_id),
    FOREIGN KEY(call_number, copy_number) REFERENCES copy(call_number, copy_number));

CREATE TABLE author(
    name varchar(30) not null,
    call_number integer not null,
    PRIMARY KEY(name, call_number),
    FOREIGN KEY(call_number) REFERENCES book(call_number));

INSERT INTO category VALUES (1, 70, 50);
INSERT INTO category VALUES (2, 50, 30);
INSERT INTO category VALUES (3, 30, 10);

INSERT INTO user VALUES (1, 'Alice', 'A.Home', 1);
INSERT INTO user VALUES (2, 'Bob', 'B.Home', 1);
INSERT INTO user VALUES (3, 'Charlie', 'C.Home', 2);
INSERT INTO user VALUES (4, 'Dave', 'A.Home', 3);
INSERT INTO user VALUES (5, 'Eve', 'A.Home', 3);

INSERT INTO book VALUES (1, 'Algorithm (I)', '01/11/2017');
INSERT INTO book VALUES (2, 'Algorithm (II)', '02/11/2017');
INSERT INTO book VALUES (3, 'Algorithm (III)', '03/11/2017');
INSERT INTO book VALUES (4, 'Algorithm (IV)', '04/11/2017');
INSERT INTO book VALUES (5, 'Algorithm (V)', '05/11/2017');
INSERT INTO book VALUES (6, 'Algorithm (VI)', '06/11/2017');

INSERT INTO copy VALUES (1, 10);
INSERT INTO copy VALUES (2, 1);
INSERT INTO copy VALUES (3, 4);
INSERT INTO copy VALUES (4, 6);
INSERT INTO copy VALUES (5, 2);
INSERT INTO copy VALUES (6, 8);

INSERT INTO checkout_record VALUES (1, 2, 1, '22/11/2017', '24/11/2017');
INSERT INTO checkout_record VALUES (4, 4, 6, '28/11/2017', '29/11/2017');

INSERT INTO author VALUES ('Peter', 1);
INSERT INTO author VALUES ('Thunder', 2);
INSERT INTO author VALUES ('Thunder', 3);
INSERT INTO author VALUES ('Thunder', 4);
INSERT INTO author VALUES ('Thunder', 5);
INSERT INTO author VALUES ('Peter', 2);
INSERT INTO author VALUES ('Thunder', 6);
INSERT INTO author VALUES ('Peter', 6);
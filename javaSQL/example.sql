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
    return_date varchar(10) not null,
    PRIMARY KEY(user_id, call_number, copy_number, checkout_date),
    FOREIGN KEY(user_id) REFERENCES user(user_id),
    FOREIGN KEY(call_number) REFERENCES book(call_number),
    FOREIGN KEY(copy_number) REFERENCES copy(copy_number));

CREATE TABLE author(
    name integer not null,
    call_number integer not null,
    PRIMARY KEY(name, call_number),
    FOREIGN KEY(call_number) REFERENCES book(call_number)
);
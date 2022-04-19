--sqlite3 test.db  

BEGIN TRANSACTION;
CREATE TABLE customer ( id INTEGER PRIMARY KEY, email TEXT, password TEXT, fullname TEXT, balance REAL, is_renting INTEGER);
CREATE TABLE staff ( id INTEGER PRIMARY KEY, email TEXT, password TEXT, fullname TEXT);
CREATE TABLE vehicle (id INTEGER PRIMARY KEY, current_customer_id INTEGER, year INTEGER, make TEXT, model TEXT, colour TEXT);
CREATE TABLE rental ( id INTEGER PRIMARY KEY, customer_id INTEGER, car_id INTEGER, start_date TEXT, end_date TEXT, status TEXT);
CREATE TABLE bill ( id INTEGER PRIMARY KEY, issued_on TEXT, due_date TEXT, amount REAL, status TEXT);
CREATE TABLE payment ( id INTEGER PRIMARY KEY, bill_id INTEGER, payment_date TEXT, amount REAL);

INSERT INTO customer (email, password, fullname, balance, is_renting) VALUES("customer1@customer.com", "123456", "Customer 1", 0.00, 0);
INSERT INTO customer (email, password, fullname, balance, is_renting) VALUES("customer2@customer.com", "123456", "Customer 2", 0.00, 0);
COMMIT;
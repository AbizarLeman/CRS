--sqlite3 test.db  

BEGIN TRANSACTION;
CREATE TABLE customer ( id INTEGER PRIMARY KEY, email TEXT, password TEXT, fullname TEXT, balance REAL, is_renting INTEGER);
CREATE TABLE staff ( id INTEGER PRIMARY KEY, email TEXT, password TEXT, fullname TEXT);
CREATE TABLE vehicle (id INTEGER PRIMARY KEY, year INTEGER, year INTEGER, make TEXT, model TEXT, color TEXT, current_customer_id INTEGER);
CREATE TABLE rental ( id INTEGER PRIMARY KEY, customer_id INTEGER, vehicle_id INTEGER, start_date TEXT, end_date TEXT, status TEXT);
CREATE TABLE bill ( id INTEGER PRIMARY KEY, rental_id INTEGER, customer_id INTEGER, issued_on TEXT, due_date TEXT, amount REAL, status TEXT);
CREATE TABLE payment ( id INTEGER PRIMARY KEY, bill_id INTEGER, payment_date TEXT, amount REAL);

INSERT INTO customer (email, password, fullname, balance, is_renting) VALUES("customer1@customer.com", "123456", "Customer 1", 0.00, 0);
INSERT INTO customer (email, password, fullname, balance, is_renting) VALUES("customer2@customer.com", "123456", "Customer 2", 0.00, 0);

INSERT INTO rental (customer_id, vehicle_id, start_date, end_date, status) VALUES(1, 1, "01-01-2020", "01-01-2020", "Pending Approval");
INSERT INTO rental (customer_id, vehicle_id, start_date, end_date, status) VALUES(1, 1, "01-01-2020", "01-01-2020", "Pending Approval");

INSERT INTO payment (bill_id, payment_date, amount) VALUES(1, "01-01-2020", 20.20);
INSERT INTO payment (bill_id, payment_date, amount) VALUES(1, "01-01-2020", 99.0);
COMMIT;
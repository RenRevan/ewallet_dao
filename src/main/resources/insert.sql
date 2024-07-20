INSERT INTO ewalletdb.bank (id, name, mfo, address) VALUES (1, 'Privat Bank', '111111', 'Zhylyanska 12');
INSERT INTO ewalletdb.bank (id, name, mfo, address) VALUES (2, 'Pivdenny Bank', null, 'Saksahanskoho 74');

INSERT INTO ewalletdb.currency (id, code) VALUES (1, 'UAH');
INSERT INTO ewalletdb.currency (id, code) VALUES (2, 'USD');
INSERT INTO ewalletdb.currency (id, code) VALUES (3, 'EUR');

INSERT INTO ewalletdb.customer (id, mobile_number, email, first_name, last_name, password, customer_role_id) VALUES (1, '380934655700', 'first@gmail.com', 'Denys', 'Alexeev', '123456', 2);

INSERT INTO ewalletdb.customer_role (id, role) VALUES (1, 'admin');
INSERT INTO ewalletdb.customer_role (id, role) VALUES (2, 'owner');
INSERT INTO ewalletdb.customer_role (id, role) VALUES (3, 'viewer');

INSERT INTO ewalletdb.operation_code (id, code) VALUES (1, 'A2A');
INSERT INTO ewalletdb.operation_code (id, code) VALUES (2, 'A2A_OTHER_BANK');

INSERT INTO ewalletdb.wallet (id, customer_id) VALUES (1, 1);

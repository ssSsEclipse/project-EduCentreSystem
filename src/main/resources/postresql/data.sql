DELETE FROM USERS;
INSERT INTO USERS (username, password, active, role) VALUES ('admin', '$2a$06$CLzncWyTDxk90/jPUum/tu49s1qHMHyIJfmHXiV38nuXzhlIAE4.G', true, 'ROLE_ADMIN');
INSERT INTO USERS (username, password, active, role) VALUES ('user1', '$2a$06$npGoWwSUhdy/xDVWbMZO3eQfGOTJInIcwTEFtg39qQehwh2xahg46', true, 'ROLE_USER');
INSERT INTO USERS (username, password, active, role) VALUES ('user2', '$2a$06$qTUgxlayCXKVIiMbAM3rD.eLiftmOireGfQN6nlDBgF.6FRezlVt2', false, 'ROLE_USER');

DELETE FROM transaction_record;
INSERT INTO transaction_record ( record_time, content, commission, amount, customer_name, cheque_issued_date, cheque_id) VALUES ( '2016-09-19 10:23:54', 'Test Content', 9999999.22, 23333.234, 'Chan Test', '2016-09-18 10:23:54', 'ABC1234577825213');	
INSERT INTO transaction_record ( record_time, content, commission, amount, customer_name, cheque_issued_date, cheque_id) VALUES ( '2016-10-01 00:00:00', 'Test Content A', 365.34, 2342, 'Test A', '2016-09-18 10:23:54', '');	
INSERT INTO transaction_record ( record_time, content, commission, amount, customer_name, cheque_issued_date, cheque_id) VALUES ( '2016-10-02 10:23:54', 'Test Content 1', 45.22, 54645256, 'Test B', '2016-09-18 10:23:54', 'DEF1234577825213');	
INSERT INTO transaction_record ( record_time, content, commission, amount, customer_name, cheque_issued_date, cheque_id) VALUES ( '2016-10-03 10:23:54', 'Test Content A1', 435.4, 76767675, 'Test C', '2016-09-18 10:23:54', '');	
INSERT INTO transaction_record ( record_time, content, commission, amount, customer_name, cheque_issued_date, cheque_id) VALUES ( '2016-10-04 10:23:54', 'Test Content 2A', 785.3, 56, 'Test D', '2016-09-18 10:23:54', 'GHI1234577825213');	
INSERT INTO transaction_record ( record_time, content, commission, amount, customer_name, cheque_issued_date, cheque_id) VALUES ( '2016-10-05 10:23:54', 'Test Content324 A', 43.22, 25465.6756, 'Test E', '2016-09-18 10:23:54', '');	
INSERT INTO transaction_record ( record_time, content, commission, amount, customer_name, cheque_issued_date, cheque_id) VALUES ( '2016-10-05 10:23:54', 'Test Conte566nt A', 123.22, 67467.465, 'Test F', '2016-09-18 10:23:54', '');	
INSERT INTO transaction_record ( record_time, content, commission, amount, customer_name, cheque_issued_date, cheque_id) VALUES ( '2016-10-06 10:23:54', 'Test Content G', 54635.22, 542.4, 'Test G', '2016-09-18 10:23:54', 'JKL1234577825213');	
INSERT INTO transaction_record ( record_time, content, commission, amount, customer_name, cheque_issued_date, cheque_id) VALUES ( '2016-10-31 23:59:59', 'Test Content H', 112.22, 2343.234, 'Test H', '2016-09-18 10:23:54', '');	
INSERT INTO transaction_record ( record_time, content, commission, amount, customer_name, cheque_issued_date, cheque_id) VALUES ( '2016-11-01 00:00:00', 'Test Content I', 12343.22, 3432.234, 'Test H', '2016-09-18 10:23:54', 'MNO1234577825213');	
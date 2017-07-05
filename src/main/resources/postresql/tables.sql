CREATE TABLE users (
id serial NOT NULL PRIMARY KEY,
username varchar(32) NOT NULL,
password varchar(256) NOT NULL,
active boolean NOT NULL,
role varchar(50) NOT NULL,
tutorial_centre_id integer,
create_datetime timestamp,
modified_datetime timestamp
);

CREATE TABLE tutorial_centre (
id serial NOT NULL PRIMARY KEY,
institution_pic varchar(64),
school_name varchar(128),
school_address varchar(512),
pic_mobile varchar(24),
school_phone varchar(24),
school_fax varchar(24),
email varchar(128),
website varchar(256),
bank_name varchar(128),
account_name varchar(128),
account_number varchar(64),
coupon_code varchar(36),
discount_comission_pdf varchar(512),
create_datetime timestamp,
modified_datetime timestamp
);

CREATE TABLE transaction_record (
id serial NOT NULL PRIMARY KEY,
record_time timestamp,
customer_name varchar(64),
content varchar(512),
amount numeric,
commission numeric 
);
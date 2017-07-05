CREATE TABLE loginUser (
id bigserial NOT NULL PRIMARY KEY,
username varchar(32) NOT NULL,
password varchar(64) DEFAULT NULL
);
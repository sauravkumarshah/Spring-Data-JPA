DROP DATABASE IF EXISTS orderservice;
DROP USER IF EXISTS `orderadmin`@`%`;
DROP USER IF EXISTS `orderuser`@`%`;
CREATE DATABASE IF NOT EXISTS orderservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `orderadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `orderservice`.* TO `orderadmin`@`%`;
CREATE USER IF NOT EXISTS `orderuser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `orderservice`.* TO `orderuser`@`%`;
FLUSH PRIVILEGES;




--- extra queries to run on mysql

SET SQL_SAFE_UPDATES = 0;

update orderservice.order_header set version = 0 where version is null;

update orderservice.order_line set version = 0 where version is null;

update orderservice.order_header set version = null;

update orderservice.order_line set version = null;
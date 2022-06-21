use pnr;

DROP ROLE 'administrator';
CREATE ROLE 'administrator';
GRANT ALL ON pnr.* TO 'administrator';

DROP ROLE 'observer';
CREATE ROLE 'observer';
GRANT SELECT ON pnr.* TO 'observer';

DROP ROLE 'field_man';
CREATE ROLE 'field_man';
GRANT SELECT, INSERT, UPDATE, DELETE ON pnr.* TO 'field_man';

DROP USER 'francis'@'localhost';
CREATE USER 'francis'@'localhost' IDENTIFIED BY 'lol';
GRANT 'observer' to 'francis'@'localhost';

SET DEFAULT ROLE ALL TO 'francis'@'localhost';
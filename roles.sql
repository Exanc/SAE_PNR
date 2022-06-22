use pnr;

DROP ROLE 'administrator';
CREATE ROLE 'administrator';
GRANT CREATE USER, GRANT OPTION, ROLE_ADMIN ON *.* TO 'administrator';
GRANT SELECT ON mysql.role_edges TO 'administrator';
GRANT ALL PRIVILEGES ON pnr.* TO 'administrator';

DROP ROLE 'observer';
CREATE ROLE 'observer';
GRANT SELECT ON pnr.* TO 'observer';

DROP ROLE 'field_man';
CREATE ROLE 'field_man';
GRANT SELECT, INSERT, UPDATE, DELETE ON pnr.* TO 'field_man';

DROP USER 'francis'@'localhost';
CREATE USER 'francis'@'localhost' IDENTIFIED BY 'lol';
GRANT 'administrator' to 'francis'@'localhost';

SET DEFAULT ROLE ALL TO 'francis'@'localhost';

FLUSH PRIVILEGES;

Select max(idObs)
from observation;
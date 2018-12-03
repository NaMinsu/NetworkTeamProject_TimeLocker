# Term Project Database

DROP DATABASE TIMELOCKER_DB;
CREATE DATABASE TIMELOCKER_DB;
USE TIMELOCKER_DB;

CREATE TABLE USERS (
ID VARCHAR(20) PRIMARY KEY,
PASSWORD VARCHAR(20) NOT NULL,
NAME VARCHAR(20) NOT NULL,
AGE INT DEFAULT 1,
PHONENUM NUMERIC(11,0));

CREATE TABLE PCROOM (
ID NUMERIC(8,0) PRIMARY KEY,
NAME VARCHAR(20) NOT NULL,
FRANCHISE INT,
DO VARCHAR(20),
SI VARCHAR(20),
GU VARCHAR(20),
DONG VARCHAR(20),
FEE INT DEFAULT 0);

CREATE TABLE REGISTERED (
PCR_ID NUMERIC(8,0) REFERENCES PCROOM(ID),
USER_ID VARCHAR(20) REFERENCES USER(ID),
LEFTTIME INT DEFAULT 0
LEFTPOINT INT DEFAULT 0);

# TODO - Insert 20 PCROOM
//INSERT INTO PCROOM VALUES();                 
INSERT INTO PCROOM VALUES(1, 'Arena', 1, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(2, 'Gold crown', 2, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(3, 'Centro', 3, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(4, '3POP Dreamnet', 4, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(5, 'Top Place', 5, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(6, 'I Sense League', 6, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(7, 'Game Innus', 7, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(8, 'Raitto', 8, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(9, 'Z', 9, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(10, 'Crowd', 10, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(11, 'Ready', 11, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(12, 'Joykan Seohyeon', 2, 12, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(13, 'Daily', 13, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(14, 'Chef', 13, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(15, 'SK', 15, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon',	0);
INSERT INTO PCROOM VALUES(16, 'Gate Zero', 16, 'Gyeonggi',	'Seongnam',	'Bundang', 'Seohyeon', 0);                 
INSERT INTO PCROOM VALUES(17, 'Lucipher', 17, 'Gyeonggi', 'Seongnam', 'Bundang',	'Seohyeon',	0);
INSERT INTO PCROOM VALUES(18, 'Gallery', 18, 'Gyeonggi', 'Seongnam',	'Bundang', 'Seohyeon', 0);
INSERT INTO PCROOM VALUES(19, 'LEO', 19, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);                 
INSERT INTO PCROOM VALUES(20, 'Uni Net', 20, 'Gyeonggi',	'Seongnam',	'Bundang', 'Seohyeon', 0);
                       
# TODO - Insert 5 USER info
//INSERT INTO USER VALUES();
INSERT INTO USER VALUES('201735824', '12345a', '나민수', 1, 01011111111);
INSERT INTO USER VALUES('201735830', '12345b', '박서연', 1, 01022222222);
INSERT INTO USER VALUES('201433709', '12345c', '전성욱', 1, 01045357713);                 
INSERT INTO USER VALUES('201631961', '12345d', '홍교연', 1, 01044444444);                 
INSERT INTO USER VALUES('201433702', '12345e', '이세진', 1, 01085299659);
                 
# TODO - Insert each user registerd 3 PCROOM
//INSERT INTO REGISTERED VALUES();                 
INSERT INTO REGISTERED VALUES(1, '201735824', 0, 0);
INSERT INTO REGISTERED VALUES(2, '201735824', 0, 0);
INSERT INTO REGISTERED VALUES(3, '201435824', 0, 0);
                                 
COMMIT;

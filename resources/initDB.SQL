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
POINT INT DEFAULT 0);

# TODO - Insert 20 PCROOM
INSERT INTO VALUES(1, 'Arena', 1, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO VALUES(2, 'Gold crown', 2, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO VALUES(3, 'Centro', 3, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO VALUES(4, '3POP Dreamnet', 4, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO VALUES(5, 'Top Place', 5, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO VALUES(6, 'I Sense League', 6, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO VALUES(7, 'Game Innus', 7, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO VALUES(8, 'Raitto', 8, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO VALUES(9, 'Z', 9, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO VALUES(10, 'Crowd', 10, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO VALUES(11, 'Ready', 11, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO VALUES(12, 'Joykan Seohyeon', 2, 12, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);
INSERT INTO VALUES(13, 'Daily', 13, 'Gyeonggi', 'Seongnam', 'Bundang', 'Seohyeon', 0);

# TODO - Insert 5 USER info

# TODO - Insert each user registerd 3 PCROOM
                 
COMMIT;

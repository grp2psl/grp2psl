/*Drops grp2PSL user and learningMgmtDB database not required to run in initial iteration*/
drop user 'grp2PSL'@'localhost';
drop database learningMgmtDB;

/*Creates new database named learningMgmtDB*/
Create database learningMgmtDB;

/*Creates new user named grp2PSL*/
CREATE USER 'grp2PSL'@'localhost' IDENTIFIED BY 'grp2PSLPass';

/*Grants all privileges on the created database to the created user*/
GRANT ALL PRIVILEGES ON learningMgmtDB.* TO 'grp2PSL'@'localhost';

/*Switches database to learningMgmtDB*/
use learningMgmtDB;

/*
drop table courseoffering;
drop table TeacherCourseMapping;
drop table learner;
drop table trainer;
drop table admin;
drop table course;
*/

/*Creates table to store learner's data*/
create table learner(
	learnerid int,
    name varchar(100),
    department varchar(50),
    phonenumber varchar(15),
    email varchar(50),
    password varchar(25),
    role varchar(45) DEFAULT 'ROLE_USER',
    enabled int DEFAULT 1,
    primary key(learnerid)
);

/*Creates table trainer's data*/
create table trainer(
	trainerid int,
    name varchar(100),
    department varchar(50),
    phonenumber varchar(15),
    email varchar(50),
    password varchar(25),
    role varchar(45) DEFAULT 'ROLE_USER',
    enabled int DEFAULT 1,
    primary key(trainerid)
);

/*Creates table admin's data*/
create table manager(
	managerid int,
    name varchar(100),
    phonenumber varchar(15),
    email varchar(50),
    password varchar(25),
    role varchar(45) DEFAULT 'ROLE_ADMIN',
    enabled int DEFAULT 1,
    primary key(managerid)
);

/*Creates table to store course details*/
create table course(
	courseid int,
    coursename varchar(100),
    prerequisite varchar(300),
    syllabus varchar(300),
    duration int,
    primary key(courseid)
);

/*Creates table which stores teachercourse mapping*/
create table TeacherCourseMapping(
	trainerid int,
    courseid int,
    tcid int,
    primary key (tcid),
    foreign key(trainerid) references trainer(trainerid),
    foreign key(courseid) references course(courseid)
);

/*Create table to store course offering details*/
create table courseoffering(
	feedback varchar(150),
    ratings int CHECK (ratings >= 0 AND ratings <= 5),
    startdate date,
    enddate date,
	learnerid int,
    tcid int,
    status varchar(20),
    percentage int check (percentage >= 0 AND percentage <= 100),
    primary key(tcid, learnerid),
    foreign key(learnerid) references learner(learnerid),
    foreign key(tcid) references TeacherCourseMapping(tcid)
);

ALTER TABLE manager ADD CONSTRAINT unique_constraint UNIQUE(email);
ALTER TABLE manager CHANGE email email varchar(50) not null;
ALTER TABLE learner ADD CONSTRAINT unique_constraint UNIQUE(email);
ALTER TABLE learner CHANGE email email varchar(50) not null;
ALTER TABLE trainer ADD CONSTRAINT unique_constraint UNIQUE(email);
ALTER TABLE trainer CHANGE email email varchar(50) not null;

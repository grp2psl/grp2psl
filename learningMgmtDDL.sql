drop user 'grp2PSL'@'localhost';
drop database learningMgmtDB;
Create database learningMgmtDB;

CREATE USER 'grp2PSL'@'localhost' IDENTIFIED BY 'grp2PSLPass';

GRANT ALL PRIVILEGES ON learningMgmtDB.* TO 'grp2PSL'@'localhost';

use learningMgmtDB;

create table learner(
	learnerid int,
    name varchar(100),
    department varchar(50),
    phonenumber long,
    email varchar(50),
    password varchar(25),
    primary key(learnerid)
);

create table trainer(
	trainerid int,
    name varchar(100),
    department varchar(50),
    phonenumber long,
    email varchar(50),
    password varchar(25),
    primary key(trainerid)
);

create table admin(
	adminid int,
    name varchar(100),
    phonenumber long,
    email varchar(50),
    password varchar(25),
    primary key(adminid)
);

create table course(
	courseid int,
    coursename varchar(100),
    prerequisite varchar(300),
    duration int,
    primary key(courseid)
);

create table courseoffering(
	feedback varchar(150),
    ratings int CHECK (ratings >= 0 AND ratings <= 5),
    startdate date,
    enddate date,
	learnerid int,
    tcid int,
    status varchar(20),
    percentage int check (percentage >= 0 AND percentage <= 100),
    primary key(learnerid, tcid)
);

create table TeacherCourseMapping(
	trainerid int,
    courseid int,
    tcid int,
    primary key (tcid)
)

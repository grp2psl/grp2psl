/*
-- Query: SELECT * FROM learningmgmtdb.course
LIMIT 0, 1000

-- Date: 2021-08-14 10:53
*/
INSERT INTO course(`courseid`,`coursename`,`prerequisite`,`syllabus`,`duration`) VALUES (1,'Java','Java Basics','https://www.javatpoint.com/spring-tutorial','4 day');
INSERT INTO course(`courseid`,`coursename`,`prerequisite`,`syllabus`,`duration`) VALUES (2,'Python','Data Structures','https://docs.python.org/3/tutorial/','2 days');
INSERT INTO course(`courseid`,`coursename`,`prerequisite`,`syllabus`,`duration`) VALUES (3,'C++','Basic programming','OOPs concepts','8 hr');

/*
-- Query: SELECT * FROM learningmgmtdb.learner
LIMIT 0, 1000

-- Date: 2021-08-14 10:54
*/
INSERT INTO learner(`learnerid`,`name`,`department`,`phonenumber`,`email`,`password`) VALUES (10000,'Abhishek','HR','9696565636','abhi@gmail.com','Abhishek10000@2562');
INSERT INTO learner(`learnerid`,`name`,`department`,`phonenumber`,`email`,`password`) VALUES (10001,'Pradnya','Sales','8585454525','pradnya@gmail.com','Pradnya10001@8063');
INSERT INTO learner(`learnerid`,`name`,`department`,`phonenumber`,`email`,`password`) VALUES (10002,'Ted Mosby','HR','9876543210','vaishnavipatil10march@gmail.com','Ted10002@5525');
INSERT INTO learner(`learnerid`,`name`,`department`,`phonenumber`,`email`,`password`) VALUES (10003,'Sheldon Cooper','L&D','9876543210','vaishnavi10march2000@gmail.com','Sheldon10003@3533');
INSERT INTO learner(`learnerid`,`name`,`department`,`phonenumber`,`email`,`password`) VALUES (10004,'Atul Kulkarni','L&D','9872343210','atulkulkarni@email.com','atul@kulkarni12');
INSERT INTO learner(`learnerid`,`name`,`department`,`phonenumber`,`email`,`password`) VALUES (10005,'Amogh Patil','HR','9442343210','amoghpatil@email.com','amogh@patil62');
INSERT INTO learner(`learnerid`,`name`,`department`,`phonenumber`,`email`,`password`) VALUES (10006,'Dinesh Chopada','IT','9442222340','dineshchopada@email.com','dinesh@chopada45');
INSERT INTO learner(`learnerid`,`name`,`department`,`phonenumber`,`email`,`password`) VALUES (10007,'Priya Sharma','IT','9443212340','priyasharma@email.com','priya@sharma32');


/*
-- Query: SELECT * FROM learningmgmtdb.trainer
LIMIT 0, 1000

-- Date: 2021-08-14 10:55
*/
INSERT INTO trainer(`trainerid`,`name`,`department`,`phonenumber`,`email`,`password`) VALUES (20000,'John','IT','3656562585','john@gmail.com','John20000@6495');
INSERT INTO trainer(`trainerid`,`name`,`department`,`phonenumber`,`email`,`password`) VALUES (20001,'Jill','HR','3656562563','jill@gmail.com','Jill20001@3196');
INSERT INTO trainer(`trainerid`,`name`,`department`,`phonenumber`,`email`,`password`) VALUES (20002,'Firstname Lastname','DepartmentName','9876543210','something@email.com','Firstname20002@7307');

/*
-- Query: SELECT * FROM learningmgmtdb.manager
LIMIT 0, 1000

-- Date: 2021-08-14 10:54
*/
INSERT INTO manager(`managerid`,`name`,`phonenumber`,`email`,`password`) VALUES (30000,'John Radnor','9657892335','group2.learning.management.system@gmail.com','manager123');

/*
-- Query: SELECT * FROM learningmgmtdb.teachercoursemapping
LIMIT 0, 1000

-- Date: 2021-08-14 10:54
*/
INSERT INTO teachercoursemapping(`trainerid`,`courseid`,`tcid`) VALUES (20000,1,0);
INSERT INTO teachercoursemapping(`trainerid`,`courseid`,`tcid`) VALUES (20001,2,1);
INSERT INTO teachercoursemapping(`trainerid`,`courseid`,`tcid`) VALUES (20000,2,2);

/*
-- Query: SELECT * FROM learningmgmtdb.courseoffering
LIMIT 0, 1000

-- Date: 2021-08-14 10:54
*/
INSERT INTO courseoffering(`feedback`,`ratings`,`startdate`,`enddate`,`learnerid`,`tcid`,`status`,`percentage`,`courseofferingid`) VALUES (NULL,0,'2021-08-14','2021-08-18',10000,0,'PASS,FEEDBACK_PENDING',80.00,1);
INSERT INTO courseoffering(`feedback`,`ratings`,`startdate`,`enddate`,`learnerid`,`tcid`,`status`,`percentage`,`courseofferingid`) VALUES (NULL,0,'2021-08-14','2021-08-18',10001,1,'PASS,FEEDBACK_PENDING',89.00,2);
INSERT INTO courseoffering(`feedback`,`ratings`,`startdate`,`enddate`,`learnerid`,`tcid`,`status`,`percentage`,`courseofferingid`) VALUES (NULL,0,'2021-09-01','2021-09-05',10002,1,'IN_PROGRESS',0.00,3);
INSERT INTO courseoffering(`feedback`,`ratings`,`startdate`,`enddate`,`learnerid`,`tcid`,`status`,`percentage`,`courseofferingid`) VALUES (NULL,0,'2021-09-01','2021-09-05',10002,1,'IN_PROGRESS',0.00,4);




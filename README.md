Employee Learning Management System

## Features

- Admin

    He will have rights to add courses and their offerings.

    To register an employee as a trainer or a learner.

    To assign trainers and learners to existing course offerings.

- Trainer

    To view details of course such as course name and course syllabus.

    To view feedback and ratings provided by learners on a particular course offering.

- Learner

    To view all the course offerings in which they are enrolled.

    To view course details of a specific course offering.

    To submit feedback and ratings on a particular course offering.

    An employee, no matter what role they may have, can view their account details and change their account password at any point of time.

## Prerequisites

- MySQL 8
- Spring Boot
- React
## Process
1. Import the DDL file present on the main branch into MySQL. To do this, open Command Prompt / Terminal, navigate to where the LearningMgmtDDL.sql file is located, run the command 
```bash
mysql -u root -p
```
to load up the MySQL command line utility, then enter root password ,finally run the command 
```bash
source LearningMgmtDDL.sql
```
Run this file to create dummy data.
```bash
source createDummyData.sql
```

Steps to start the project.
- Go to the LearningManagementSystem/src/main/java/com/psl directory and run LearningManagementSystemApplication.java as a java app.
- Navigate to LearningManagementSystem/src/main/resources/webapp/front-end and run following command to install all dependencies
```bash
npm install
```
- To run the project, run
```bash
npm run start
```
to run the npm server.
- go to localhost:3000 in your web browser
![login page](https://github.com/grp2psl/grp2psl/blob/0b2cc7df30149a5a25e62102ed09131d06d72a5f/login%20.png)
Click on the role you want to login as and you will redirected to the appropriate page.
Get the login credentials from https://github.com/grp2psl/grp2psl/blob/0b2cc7df30149a5a25e62102ed09131d06d72a5f/createDummyData.sql

![login credentials](https://github.com/grp2psl/grp2psl/blob/ea691626e33b0485c7258973c6f427b361f70585/credentials.png)

API Documentation Link - 

```bash
http://localhost:8080/LearningManagementSystem/ui-apidoc.html
```

Login and enjoy the Learning Management System :-)

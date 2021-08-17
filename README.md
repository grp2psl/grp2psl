# Group 2 Mini Project

Employee Learning Management System

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
- Navigate to LearningManagementSystem/src/main/resources/webapp/front-end and run
```bash
npm run start
```
to run the npm server.
- go to localhost:3000 in your web browser

![login page](https://github.com/grp2psl/grp2psl/blob/0b2cc7df30149a5a25e62102ed09131d06d72a5f/login%20.png)

Click on the role you want to login as and you will redirected to the appropriate page.

Get the login credentials from https://github.com/grp2psl/grp2psl/blob/0b2cc7df30149a5a25e62102ed09131d06d72a5f/createDummyData.sql

![login credentials](https://github.com/grp2psl/grp2psl/blob/ea691626e33b0485c7258973c6f427b361f70585/credentials.png)

Login and enjoy the Learning Management System :-)

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

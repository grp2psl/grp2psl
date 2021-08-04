-- We will create user credentials table here, this table is must-run for login and spring security.
USE learningMgmtDB;

CREATE TABLE usercredentials AS select learnerid as username, password, enabled, role from learner union select trainerid as username, password, enabled, role from trainer union select managerid as username, password, enabled, role from manager;
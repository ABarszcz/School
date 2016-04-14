USE gc200318107;

DROP TABLE IF EXISTS admins;
CREATE TABLE admins (
	adminID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30),
    password VARCHAR(128)
);

SELECT * FROM admins;

DROP TABLE IF EXISTS pages;
CREATE TABLE pages (
	pageID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    pageTitle VARCHAR(30),
    pageContent VARCHAR(1500)
);

SELECT * FROM pages;
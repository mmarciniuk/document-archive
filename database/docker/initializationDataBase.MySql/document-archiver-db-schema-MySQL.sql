START TRANSACTION;

DROP SCHEMA IF EXISTS document_archiver_db;
CREATE SCHEMA document_archiver_db;

USE document_archiver_db;

DROP TABLE IF EXISTS transaction_info;
CREATE TABLE transaction_info (
	Id								    INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Uuid							    VARCHAR(36) NOT NULL,
    CreatedDateTime					    TIMESTAMP NOT NULL,
    ModificationDateTime			    TIMESTAMP
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
	Id								    INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    UserName							VARCHAR(50) NOT NULL,
    Password							VARCHAR(80) NOT NULL,
    FirstName							VARCHAR(50) NOT NULL,
    LastName							VARCHAR(50) NOT NULL,
    AddressEmail						VARCHAR(80) NOT NULL,
    CONSTRAINT FK_UserId FOREIGN KEY (Id) REFERENCES transaction_info(Id),
    CONSTRAINT UQ_UserName UNIQUE (UserName),
    CONSTRAINT UQ_AddressEmail UNIQUE (AddressEmail)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS role;
CREATE TABLE role (
	Id								    INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    RoleName							VARCHAR(10) NOT NULL,
    CONSTRAINT FK_RoleId FOREIGN KEY (Id) REFERENCES transaction_info(Id),
    CONSTRAINT UQ_RoleName UNIQUE (RoleName)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles (
	UserId							    INT UNSIGNED NOT NULL,
    RoleId							    INT UNSIGNED NOT NULL,
    PRIMARY KEY (UserId, RoleId),
    CONSTRAINT FK_UserRolesUserId FOREIGN KEY (UserId) REFERENCES user(Id),
    CONSTRAINT FK_UserRolesRoleId FOREIGN KEY (RoleId) REFERENCES role(Id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS document;
CREATE TABLE document (
	Id								    INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    DocumentName						VARCHAR(500) NOT NULL,
    Extension							VARCHAR(10) NOT NULL,
    DocumentBlob						BLOB NOT NULL,
    DocumentOwnerId					    INT UNSIGNED NOT NULL,
     CONSTRAINT FK_DocumentRoleId FOREIGN KEY (Id) REFERENCES transaction_info(Id),
     CONSTRAINT FK_DocumentOwnerId FOREIGN KEY (DocumentOwnerId) REFERENCES user(Id),
     CONSTRAINT UQ_UniqueDocument UNIQUE (DocumentName, Extension, DocumentOwnerId)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS document_meta_data;
CREATE TABLE document_meta_data (
	Id								    INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    DocumentId						    INT UNSIGNED NOT NULL,
    Name							    VARCHAR(500) NOT NULL,
    Value							    VARCHAR(500) NOT NULL,
    CONSTRAINT FK_DocumentMetaData_DocumentId FOREIGN KEY (Id) REFERENCES transaction_info(Id),
    CONSTRAINT FK_DocumentId FOREIGN KEY (DocumentId) REFERENCES document(Id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


-- Insert data
-- Insert role ADMIN
INSERT INTO transaction_info(Uuid, CreatedDateTime)
VALUES(uuid(), now());

SET @adminId = last_insert_id();

INSERT INTO role(Id, RoleName)
VALUES(@adminId, 'ADMIN');

-- Insert role USER
INSERT INTO transaction_info(Uuid, CreatedDateTime)
VALUES(uuid(), now());

SET @userId = last_insert_id();

INSERT INTO role(Id, RoleName)
VALUE(@userId, 'USER');

COMMIT;

























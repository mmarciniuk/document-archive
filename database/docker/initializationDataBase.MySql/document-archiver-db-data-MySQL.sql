START TRANSACTION;

USE document_archiver_db;

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
INSERT INTO COLUMN_ENTITY (name) VALUES('first Column');
INSERT INTO COLUMN_ENTITY (name) VALUES('second Column');
INSERT INTO TASK_ENTITY (name, description, date_of_creation, column_id) VALUES('Task1', 'description1', null , 1);
INSERT INTO TASK_ENTITY (name, description, date_of_creation, column_id) VALUES('Task2', 'description2', null , 2);

-- SELECT a.*, b.* FROM COLUMN_ENTITY AS a JOIN TASK_ENTITY AS b ON a.id = b.column_id;


package com.mirzoiev.testApp.repository.queryUtil;

import com.mirzoiev.testApp.entity.ColumnEntity;

/**
 * interface QueryUtil
 * contain all native SQL QUERY constants
 * for manipulating with database
 *
 * @author R.M.
 * @since 15.07.2022
 */
public interface QueryUtil {

    /*
        SQL Query for Column Entity
     */
    String SQL_CREATE_COLUMN = "INSERT INTO COLUMN_ENTITY (name) VALUES (?)";
    String SQL_UPDATE_COLUMN = "UPDATE COLUMN_ENTITY SET name=? WHERE ID=?";
    String SQL_DELETE_COLUMN = "DELETE COLUMN_ENTITY WHERE ID=?";
    String SQL_DELETE_TASK = "DELETE FROM TASK_ENTITY WHERE COLUMN_ID=?";
    String SQL_FIND_COLUMN_BY_ID = "SELECT id, name FROM COLUMN_ENTITY WHERE ID=?";

     /*
        SQL Query for Task Entity
     */
    String SQL_FIND_ALL_TASK = "SELECT id, name, description, date_of_creation, column_id FROM TASK_ENTITY"; //"SELECT * FROM TASK_ENTITY";
    String SQL_CREATE_TASK = "INSERT INTO TASK_ENTITY (name, description, date_of_creation, column_id) VALUES (?, ?, ?, ?)";
    String SQL_UPDATE_TASK = "UPDATE TASK_ENTITY SET name=?, description=?, date_of_creation=?, column_id=? WHERE ID=?";
    String SQL_DELETE_TASK_BY_ID = "DELETE FROM TASK_ENTITY WHERE ID=?";
    String SQL_TASK_FIND_BY_ID = "SELECT id, name, description, date_of_creation, column_id FROM TASK_ENTITY WHERE ID=?";

}

package com.mirzoiev.testApp.repository;

import com.mirzoiev.testApp.entity.ColumnEntity;
import com.mirzoiev.testApp.entity.TaskEntity;
import com.mirzoiev.testApp.exception.ResourceNotFoundException;
import com.mirzoiev.testApp.model.ColumnDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ColumnRepo {

    private static Logger logger = Logger.getLogger(ColumnRepo.class);

    private static final String SQL_FIND_ALL_COLUMN_TASK = " SELECT a.*, b.* FROM COLUMN_ENTITY AS a JOIN TASK_ENTITY AS b ON a.id = b.column_id";
    private static final String SQL_CREATE_COLUMN = "INSERT INTO COLUMN_ENTITY (name ) VALUES (?)";
    private static final String SQL_UPDATE_COLUMN = "UPDATE COLUMN_ENTITY SET name=? WHERE ID=?";
    private static final String SQL_DELETE_COLUMN = "DELETE COLUMN_ENTITY WHERE ID=?";
    private static final String SQL_DELETE_TASK = "DELETE FROM TASK_ENTITY WHERE COLUMN_ID=?";
    private static final String SQL_FIND_COLUMN_BY_ID = "SELECT id, name FROM COLUMN_ENTITY WHERE ID=?";
    private static final String SQL_FIND_TASK_BY_ID = "SELECT id, name, description, date_of_creation, column_id FROM TASK_ENTITY WHERE ID=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ColumnRepository columnRepository;

    public List<ColumnEntity> getAllColumns() {
        logger.debug("start method getAllColumns");
        return columnRepository.getAllColumns();
    }

    public ResponseEntity<ColumnDTO> createColumn(ColumnEntity column) {
        logger.debug("start method createColumn");
        jdbcTemplate.update(SQL_CREATE_COLUMN,
                column.getName());
        logger.debug("finish method createColumn");
        return ResponseEntity.ok(ColumnDTO.toModel(column));
    }

    public ResponseEntity<ColumnEntity> renameColumn(Long id, ColumnEntity column) {
        logger.debug("start method renameColumn");
        ColumnEntity columnEntity = columnRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Column not exist with id: " + id));
        columnEntity.setName(column.getName());
        jdbcTemplate.update(SQL_UPDATE_COLUMN,
                columnEntity.getName(), id);
        logger.debug("finish method renameColumn");
        return ResponseEntity.ok(columnEntity);
    }

    public Long deleteColumn(Long id) {
        logger.debug("start method deleteColumn");
        columnRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Column not exist with id: " + id));
        jdbcTemplate.update(SQL_DELETE_TASK, id);
        jdbcTemplate.update(SQL_DELETE_COLUMN, id);
        logger.debug("finish method deleteColumn");
        return id;
    }

    public ColumnEntity findColumnById(Long id) {
        return (ColumnEntity) jdbcTemplate.queryForObject(
                SQL_FIND_COLUMN_BY_ID,
                new Object[]{id},
                new BeanPropertyRowMapper(ColumnEntity.class));
    }
}

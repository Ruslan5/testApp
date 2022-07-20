package com.mirzoiev.testApp.repository;

import com.mirzoiev.testApp.entity.ColumnEntity;
import com.mirzoiev.testApp.exception.ResourceNotFoundException;
import com.mirzoiev.testApp.model.ColumnDTO;
import com.mirzoiev.testApp.repository.queryUtil.QueryUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Column repository class
 *
 * @author R.M.
 * @since 15.07.2022
 * @see ColumnEntity
 * @see ColumnRepository
 * @see QueryUtil
 */
@Repository
public class ColumnRepo {
    private static Logger logger = Logger.getLogger(ColumnRepo.class);
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
        jdbcTemplate.update(QueryUtil.SQL_CREATE_COLUMN,
                column.getName());
        logger.debug("finish method createColumn");
        return ResponseEntity.ok(ColumnDTO.toModel(column));
    }

    public ResponseEntity<ColumnEntity> renameColumn(Long id, ColumnEntity column) {
        logger.debug("start method renameColumn");
        ColumnEntity columnEntity = columnRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Column not exist with id: " + id));
        columnEntity.setName(column.getName());
        jdbcTemplate.update(QueryUtil.SQL_UPDATE_COLUMN,
                columnEntity.getName(), id);
        logger.debug("finish method renameColumn");
        return ResponseEntity.ok(columnEntity);
    }

    public Long deleteColumn(Long id) {
        logger.debug("start method deleteColumn");
        columnRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Column not exist with id: " + id));
        jdbcTemplate.update(QueryUtil.SQL_DELETE_TASK, id);
        jdbcTemplate.update(QueryUtil.SQL_DELETE_COLUMN, id);
        logger.debug("finish method deleteColumn");
        return id;
    }

    public ColumnEntity findColumnById(Long id) {
        return (ColumnEntity) jdbcTemplate.queryForObject(
                QueryUtil.SQL_FIND_COLUMN_BY_ID,
                new Object[]{id},
                new BeanPropertyRowMapper(ColumnEntity.class));
    }
}

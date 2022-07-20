package com.mirzoiev.testApp.service;

import com.mirzoiev.testApp.entity.ColumnEntity;
import com.mirzoiev.testApp.model.ColumnDTO;
import com.mirzoiev.testApp.repository.ColumnRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Column service class
 *
 * @author R.M.
 * @since 15.07.2022
 */
@Service
public class ColumnService {
    private static Logger logger = Logger.getLogger(ColumnService.class);

    @Autowired
    private ColumnRepo columnRepos;

    public List<ColumnEntity> getAllColumns() {
        logger.debug("start method getAllColumns");
        return columnRepos.getAllColumns();
    }
    public ResponseEntity<ColumnDTO> createColumn(ColumnEntity column) {
        logger.debug("start method createColumn");
        return columnRepos.createColumn(column);
    }
    public ResponseEntity<ColumnEntity> renameColumn(Long id, ColumnEntity column) {
        logger.debug("start method renameColumn");
        return columnRepos.renameColumn(id, column);
    }
    public Long deleteColumn(Long id) {
        logger.debug("start method deleteColumn");
        return columnRepos.deleteColumn(id);
    }
    public ColumnEntity findColumnById(Long id) {
        return columnRepos.findColumnById(id);
    }

}

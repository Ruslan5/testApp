package com.mirzoiev.testApp.service;

import com.mirzoiev.testApp.entity.ColumnEntity;
import com.mirzoiev.testApp.model.ColumnDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
class ColumnServiceTest {
    private static Logger logger = Logger.getLogger(ColumnServiceTest.class);
    @MockBean
    private ColumnService columnService;

    @Test
    void getAllColumns() {
        logger.debug("start test method getAllColumns");
        ColumnEntity columnEntity = getColumns();
        List<ColumnEntity> entityList = new ArrayList<>();
        entityList.add(columnEntity);
        given(columnService.getAllColumns()).willReturn(entityList);
        List<ColumnEntity> result = columnService.getAllColumns();
        System.out.println(result.toString());
        assertEquals(result.size(), 1);
        logger.debug("finish test method getAllColumns");
    }
    private ColumnEntity getColumns() {
        ColumnEntity column = new ColumnEntity();
        column.setId(1l);
        column.setName("name1");
        return column;
    }


    @Test
    void createColumn() {
        logger.debug("start test method createColumn");
        ColumnEntity column = getColumns();
        given(columnService.createColumn(column)).willReturn(ResponseEntity.ok(ColumnDTO.toModel(column)));
        ResponseEntity<ColumnDTO> result = columnService.createColumn(column);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        logger.debug("finish test method createColumn");
    }

    @Test
    void renameColumn() {
        logger.debug("start test method renameColumn");
        ColumnEntity column = getColumns();
        given(columnService.renameColumn(1l, column)).willReturn(ResponseEntity.ok(column));
        ResponseEntity<ColumnEntity> result = columnService.renameColumn(1l, column);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        logger.debug("finish test method renameColumn");
    }

    @Test
    void deleteColumn() {
        logger.debug("start test method deleteColumn");
        ColumnEntity columnEntity = getColumns();
        given(columnService.deleteColumn(1l)).willReturn(columnEntity.getId());
        assertEquals(columnService.deleteColumn(1l), 1);
        logger.debug("finish test method deleteColumn");
    }
}
package com.mirzoiev.testApp.repository;

import com.mirzoiev.testApp.entity.ColumnEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
class ColumnRepoTest {
    private static Logger logger = Logger.getLogger(ColumnRepoTest.class);

    @Autowired
    private ColumnRepo columnRepo;

    @Test
    void testGetAllColumns() {
        List<ColumnEntity> entityList = new ArrayList<>();
        columnRepo.getAllColumns().forEach(e -> entityList.add(e));
        System.out.println(entityList);
        assertEquals(entityList.size(), 2);
    }

    @Test
    void createColumn() {
        int expectedListSize = 3;
        ColumnEntity column = getColumn();
        columnRepo.createColumn(column);
        List<ColumnEntity> entityList = new ArrayList<>();
        columnRepo.getAllColumns().forEach(e -> entityList.add(e));
        assertEquals(entityList.size(), expectedListSize);

    }

    @Test
    void renameColumn() {
        String rename = "new name";
        Long columnId = 2l;
        ColumnEntity column = columnRepo.findColumnById(columnId);
        column.setName(rename);
        columnRepo.renameColumn(columnId, column);
        assertEquals(rename, column.getName());
    }
    @Test
    void findColumnById() {
        Long findColumnID = 1l;
        ColumnEntity result = columnRepo.findColumnById(findColumnID);
        assertEquals(findColumnID, result.getId());
    }

        @Test
    void deleteColumn() {
        ColumnEntity column = getColumn();
        columnRepo.createColumn(column);
        Long returnDeletedId = columnRepo.deleteColumn(column.getId());
        assertEquals(column.getId(), returnDeletedId);
    }

    private ColumnEntity getColumn() {
        ColumnEntity column = new ColumnEntity();
        column.setId(3l);
        column.setName("name1");
        return column;
    }
}
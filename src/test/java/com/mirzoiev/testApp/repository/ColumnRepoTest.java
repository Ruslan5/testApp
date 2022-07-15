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
        ColumnEntity column = getColumn();
        columnRepo.createColumn(column);
        ColumnEntity found = columnRepo.findColumnById(column.getId());
        assertEquals(column.getId(), found.getId());
    }

    @Test
    void renameColumn() {
        ColumnEntity column = getColumn();
        columnRepo.createColumn(column);
        column.setName("rename");
        columnRepo.renameColumn(column.getId(), column);
        assertEquals("rename", column.getName());
    }

    @Test
    void deleteColumn() {
        ColumnEntity column = getColumn();
        columnRepo.createColumn(column);
        Long returnDeletedId = columnRepo.deleteColumn(column.getId());
        assertEquals(column.getId(), returnDeletedId);
    }

    @Test
    void findColumnById() {
        ColumnEntity column = getColumn();
        columnRepo.createColumn(column);
        ColumnEntity result = columnRepo.findColumnById(column.getId());
        assertEquals(column.getId(), result.getId());
    }

    private ColumnEntity getColumn() {
        ColumnEntity column = new ColumnEntity();
        column.setId(3l);
        column.setName("name1");
        return column;
    }
}
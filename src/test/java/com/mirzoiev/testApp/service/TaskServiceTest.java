package com.mirzoiev.testApp.service;

import com.mirzoiev.testApp.entity.ColumnEntity;
import com.mirzoiev.testApp.entity.TaskEntity;
import com.mirzoiev.testApp.model.TaskDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
class TaskServiceTest {
    private static Logger logger = Logger.getLogger(TaskServiceTest.class);

    @MockBean
    private TaskService taskService;

    @Autowired
    private ColumnService columnService;

    @Test
    void getAllTask() {
        logger.debug("start test method getAllTask");
        TaskDTO taskEntity = TaskDTO.toModel(getTask());
        List<TaskDTO> entityList = new ArrayList<>();
        entityList.add(taskEntity);
        given(taskService.getAllTask()).willReturn(entityList);
        List<TaskDTO> result = taskService.getAllTask();
        assertEquals(result.size(), 1);
        logger.debug("finish test method getAllTask");
    }

    @Test
    void addTask() {
        logger.debug("start test method addTask");
        TaskEntity task = getTask();
        given(taskService.addTask(task, 1l)).willReturn(ResponseEntity.ok(TaskDTO.toModel(task)));
        ResponseEntity<TaskDTO> result = taskService.addTask(task, 1l);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        logger.debug("finish test method addTask");
    }

    @Test
    void updateTask() {
        logger.debug("start test method updateTask");
        TaskEntity task = getTask();
        given(taskService.updateTask(1l, task, 1l))
                .willReturn(ResponseEntity.ok(TaskDTO.toModel(task)));
        ResponseEntity<TaskDTO> result = taskService.updateTask(1l, task, 1l);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        logger.debug("finish test method updateTask");
    }

    @Test
    void deleteTask() {
        logger.debug("start test method deleteTask");
        TaskEntity task = getTask();
        given(taskService.deleteTask(1l)).willReturn(task.getId());
        assertEquals(taskService.deleteTask(1l), 1);
        logger.debug("finish test method deleteTask");
    }

    private TaskEntity getTask() {
        TaskEntity task = new TaskEntity();
        task.setId(1l);
        task.setName("TaskName1");
        task.setDescription("description1");
        task.setDateOfCreation("14-07-2022");
        ColumnEntity column = new ColumnEntity();
        column.setId(1l);
        column.setName("ColumnName1");
        task.setColumn(column);
        return task;
    }
}
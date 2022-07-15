package com.mirzoiev.testApp.repository;

import com.mirzoiev.testApp.entity.ColumnEntity;
import com.mirzoiev.testApp.entity.TaskEntity;
import com.mirzoiev.testApp.model.TaskDTO;
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
class TaskRepoTest {
    private static Logger logger = Logger.getLogger(TaskRepoTest.class);

    @Autowired
    private TaskRepo taskRepo;

    @Test
    void getAllTask() {
        List<TaskDTO> entityArrayList = new ArrayList<>();
        taskRepo.getAllTask().forEach(e -> entityArrayList.add(e));
        System.out.println(entityArrayList);
        assertEquals(entityArrayList.size(), 2);
    }

    @Test
    void addTask() {
        TaskEntity taskEntity = getTask();
        Long columnId = 1l;
        taskRepo.addTask(taskEntity, columnId);
        TaskEntity found = taskRepo.findTaskById(taskEntity.getId());
        assertEquals(taskEntity.getId(), found.getId());
    }

    @Test
    void updateTask() {
        TaskEntity task = getTask();
        Long columnId = 1l;
        taskRepo.addTask(task, columnId);
        task.setName("rename");
        taskRepo.updateTask(task.getId(), task, columnId);
        assertEquals("rename", task.getName());
    }

    @Test
    void deleteTask() {
        TaskEntity task = getTask();
        Long columnId = 1l;
        taskRepo.addTask(task, columnId);
        Long returnDeletedId = taskRepo.deleteTask(task.getId());
        assertEquals(task.getId(), returnDeletedId);
    }

    @Test
    void findTaskById() {
        TaskEntity task = getTask();
        Long columnId = 1l;
        taskRepo.addTask(task, columnId);
        TaskEntity result = taskRepo.findTaskById(task.getId());
        assertEquals(task.getId(), result.getId());
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
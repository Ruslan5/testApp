package com.mirzoiev.testApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirzoiev.testApp.TestAppApplication;
import com.mirzoiev.testApp.entity.ColumnEntity;
import com.mirzoiev.testApp.entity.TaskEntity;
import com.mirzoiev.testApp.model.TaskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestAppApplication.class)
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private TaskController taskController;

    @Test
    void testGetAllTasks() throws Exception {
        TaskEntity taskEntity = getTask();
        List<TaskDTO> entityList = new ArrayList<>();
        entityList.add(TaskDTO.toModel(taskEntity));
        given(taskController.getAllTasks()).willReturn(entityList);
        mvc.perform(get("/tasks").contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is(taskEntity.getName())));
    }

    @Test
    void testCreateTask() throws Exception {
        TaskEntity taskEntity = getTask();
        doNothing().when(taskController).createTask(taskEntity, 1l);
        mvc.perform(post("/tasks" + "?columnId=" + 1).content(asJson(taskEntity)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void testUpdateTask() throws Exception {
        TaskEntity taskEntity = getTask();
        doNothing().when(taskController).updateTask(1l, taskEntity, 1l);
        mvc.perform(put("/tasks/" + taskEntity.getId() + "?columnId=" + 1)
                        .content(asJson(taskEntity)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void testDeleteTask() throws Exception {
        TaskEntity taskEntity = getTask();
        doNothing().when(taskController).deleteTask(1l);
        mvc.perform(delete("/tasks/" + taskEntity.getId()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
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

    private static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
package com.mirzoiev.testApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirzoiev.testApp.TestAppApplication;
import com.mirzoiev.testApp.entity.ColumnEntity;
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
class ColumnControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ColumnController columnController;

    @Test
    void testGetAllColumns() throws Exception {
        ColumnEntity columnEntity = getColumn();
        List<ColumnEntity> entityList = new ArrayList<>();
        entityList.add(columnEntity);
        given(columnController.getAllColumns()).willReturn(entityList);
        mvc.perform(get("/").contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is(columnEntity.getName())));
    }

    @Test
    void testCreateColumn() throws Exception {
        ColumnEntity columnEntity = getColumn();
        doNothing().when(columnController).createColumn(columnEntity);
        mvc.perform(post("/").content(asJson(columnEntity)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void testRenameColumn() throws Exception {
        ColumnEntity columnEntity = getColumn();
        doNothing().when(columnController).renameColumn(1l, columnEntity);
        mvc.perform(put("/" + columnEntity.getId())
                        .content(asJson(columnEntity)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void testDeleteColumn() throws Exception {
        ColumnEntity columnEntity = getColumn();
        doNothing().when(columnController).deleteColumn(1l);
        mvc.perform(delete("/" + columnEntity.getId()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    private ColumnEntity getColumn() {
        ColumnEntity column = new ColumnEntity();
        column.setId(1l);
        column.setName("name1");
        return column;
    }

    private static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
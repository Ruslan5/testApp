package com.mirzoiev.testApp.repository;

import com.mirzoiev.testApp.entity.ColumnEntity;
import com.mirzoiev.testApp.entity.TaskEntity;
import com.mirzoiev.testApp.exception.ResourceNotFoundException;
import com.mirzoiev.testApp.model.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class TaskRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ColumnRepository columnRepository;

    private static final String SQL_FIND_ALL_TASK = "SELECT id, name, description, date_of_creation, column_id FROM TASK_ENTITY"; //"SELECT * FROM TASK_ENTITY";
    private static final String SQL_CREATE_TASK = "INSERT INTO TASK_ENTITY (name, description, date_of_creation, column_id) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_TASK = "UPDATE TASK_ENTITY SET name=?, description=?, date_of_creation=?, column_id=? WHERE ID=?";
    private static final String SQL_DELETE_TASK = "DELETE FROM TASK_ENTITY WHERE ID=?";
    private static final String SQL_FIND_TASK_BY_ID = "SELECT id, name, description, date_of_creation, column_id FROM TASK_ENTITY WHERE ID=?";

    public List<TaskDTO> getAllTask() {
        return jdbcTemplate.query(
                SQL_FIND_ALL_TASK,
                new BeanPropertyRowMapper(TaskDTO.class));
    }
    public ResponseEntity<TaskDTO> addTask(TaskEntity taskEntity, Long columnId) {
        ColumnEntity column = columnRepository.findById(columnId).get();
        taskEntity.setColumn(column);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String creationDate = simpleDateFormat.format(date);
        taskEntity.setDateOfCreation(creationDate);

        jdbcTemplate.update(SQL_CREATE_TASK,
                taskEntity.getName(), taskEntity.getDescription(),
                taskEntity.getDateOfCreation(), columnId);
        return ResponseEntity.ok(TaskDTO.toModel(taskEntity));
    }
    public ResponseEntity<TaskDTO> updateTask(Long id,
                                              TaskEntity task,
                                              Long columnId){
        TaskEntity taskEntity = findTaskById(id);
        if (taskEntity == null){
            throw new ResourceNotFoundException("Task not exist with id: " + id);
        }

        taskEntity.setName(task.getName());
        taskEntity.setDescription(task.getDescription());

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateOfUpdate = simpleDateFormat.format(date);
        taskEntity.setDateOfCreation(dateOfUpdate);

        ColumnEntity column = columnRepository.findById(columnId)
                .orElseThrow(() -> new ResourceNotFoundException("Column not exist with id: " +
                        taskEntity.getColumn().getId()));
        taskEntity.setColumn(column);

        jdbcTemplate.update(SQL_UPDATE_TASK,
                taskEntity.getName(), taskEntity.getDescription(),
                taskEntity.getDateOfCreation(), columnId, id);
        return ResponseEntity.ok(TaskDTO.toModel(taskEntity));
    }
    public Long deleteTask(Long id) {
        TaskEntity taskEntity = findTaskById(id);
        if (taskEntity == null){
            throw  new ResourceNotFoundException("Column not exist with id: " + id);
        }
        jdbcTemplate.update(SQL_DELETE_TASK, id);
        return id;
    }
    public TaskEntity findTaskById(Long id) {
        return (TaskEntity) jdbcTemplate.queryForObject(
                SQL_FIND_TASK_BY_ID,
                new Object[]{id},
                new BeanPropertyRowMapper(TaskEntity.class));
    }
}

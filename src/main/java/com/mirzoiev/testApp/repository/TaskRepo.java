package com.mirzoiev.testApp.repository;

import com.mirzoiev.testApp.entity.ColumnEntity;
import com.mirzoiev.testApp.entity.TaskEntity;
import com.mirzoiev.testApp.exception.ResourceNotFoundException;
import com.mirzoiev.testApp.model.TaskDTO;
import com.mirzoiev.testApp.repository.queryUtil.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.mirzoiev.testApp.repository.queryUtil.QueryUtil.SQL_CREATE_TASK;
import static com.mirzoiev.testApp.repository.queryUtil.QueryUtil.SQL_DELETE_TASK_BY_ID;
import static com.mirzoiev.testApp.repository.queryUtil.QueryUtil.SQL_FIND_ALL_TASK;
import static com.mirzoiev.testApp.repository.queryUtil.QueryUtil.SQL_TASK_FIND_BY_ID;
import static com.mirzoiev.testApp.repository.queryUtil.QueryUtil.SQL_UPDATE_TASK;

/**
 * Task repository class
 *
 * @author R.M.
 * @since 15.07.2022
 * @see TaskEntity
 * @see QueryUtil
 */
@Repository
public class TaskRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ColumnRepository columnRepository;
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
        jdbcTemplate.update(SQL_DELETE_TASK_BY_ID, id);
        return id;
    }
    public TaskEntity findTaskById(Long id) {
        return (TaskEntity) jdbcTemplate.queryForObject(
                SQL_TASK_FIND_BY_ID,
                new Object[]{id},
                new BeanPropertyRowMapper(TaskEntity.class));
    }
}

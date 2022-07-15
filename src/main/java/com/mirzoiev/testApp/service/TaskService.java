package com.mirzoiev.testApp.service;

import com.mirzoiev.testApp.entity.TaskEntity;
import com.mirzoiev.testApp.model.TaskDTO;
import com.mirzoiev.testApp.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<TaskDTO> getAllTask() {
        return taskRepo.getAllTask();
    }
    /**
     * creates a task with these parameters (localhost:8080/tasks?columnId=1)
     *
     * @param taskEntity
     * @param columnId   - identifier of the column in which the task will be
     * @return
     */
    public ResponseEntity<TaskDTO> addTask(TaskEntity taskEntity, Long columnId) {
        return taskRepo.addTask(taskEntity, columnId);
    }

    /**
     * update the task with these parameters  (localhost:8080/tasks/1?columnId=2)
     * @param id - ID of the task you want to update
     * @param task - rename body parameters
     *            {"name": "rename",
     *             "description": "rename",
     *             "dateOfCreation": null
     *             }
//     * @param columnId - column identifier to which you want to move the task
     * @return
     */
    public ResponseEntity<TaskDTO> updateTask(Long id,
                                              TaskEntity task,
                                              Long columnId){
        return taskRepo.updateTask(id, task, columnId);
    }

    /**
     * Method for deleting a task by id
     * @param id - id of task
     * @return - id of deleted task
     */
    public Long deleteTask(Long id) {
        return taskRepo.deleteTask(id);
    }

    public TaskEntity findTaskById(Long id) {
        return taskRepo.findTaskById(id);
    }

}

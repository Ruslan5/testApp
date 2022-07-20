package com.mirzoiev.testApp.controller;

import com.mirzoiev.testApp.entity.TaskEntity;
import com.mirzoiev.testApp.model.TaskDTO;
import com.mirzoiev.testApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest Controller class for task
 *
 * @author R.M.
 * @since 15.07.2022
 */

@RestController
@RequestMapping
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTask();
    }

    @GetMapping("/task/{id}")
    public TaskEntity getTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

    @PostMapping("/tasks")
    public void createTask(@RequestBody TaskEntity task,
                           @RequestParam Long columnId) {
        taskService.addTask(task, columnId);
    }

    @PutMapping("/tasks/{id}")
    public void updateTask(@PathVariable Long id,
                           @RequestBody TaskEntity task,
                           @RequestParam Long columnId) {
        taskService.updateTask(id, task, columnId);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

}

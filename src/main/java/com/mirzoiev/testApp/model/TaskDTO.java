package com.mirzoiev.testApp.model;

import com.mirzoiev.testApp.entity.TaskEntity;

public class TaskDTO {
    private Long id;
    private String name;
    private String description;
    private String dateOfCreation;

    private Long column_id;
    public static TaskDTO toModel(TaskEntity task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDateOfCreation(task.getDateOfCreation());
        taskDTO.setColumn_id(task.getColumn().getId());
        return taskDTO;
    }
    public TaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Long getColumn_id() {
        return column_id;
    }

    public void setColumn_id(Long column_id) {
        this.column_id = column_id;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateOfCreation='" + dateOfCreation + '\'' +
                ", column_id=" + column_id +
                '}';
    }
}

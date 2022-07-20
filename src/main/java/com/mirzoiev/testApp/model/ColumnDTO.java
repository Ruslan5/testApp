package com.mirzoiev.testApp.model;

import com.mirzoiev.testApp.entity.ColumnEntity;

import java.util.List;

/**
 * Column DTO class
 * for transfer data from ColumnEntity class
 *
 * @author R.M.
 * @see ColumnEntity
 * @since 15.07.2022
 */
public class ColumnDTO {
    private Long id;
    private String name;
    private List<TaskDTO> taskDTOList;

    public static ColumnDTO toModel(ColumnEntity columnEntity) {
        ColumnDTO columnDTO = new ColumnDTO();
        columnDTO.setId(columnEntity.getId());
        columnDTO.setName(columnEntity.getName());
        return columnDTO;
    }

    public ColumnDTO() {
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

    public List<TaskDTO> getTaskDTOList() {
        return taskDTOList;
    }

    public void setTaskDTOList(List<TaskDTO> taskDTOList) {
        this.taskDTOList = taskDTOList;
    }

    @Override
    public String toString() {
        return "ColumnDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taskDTOList=" + taskDTOList +
                '}';
    }
}

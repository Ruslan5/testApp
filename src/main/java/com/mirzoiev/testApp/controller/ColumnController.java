package com.mirzoiev.testApp.controller;

import com.mirzoiev.testApp.entity.ColumnEntity;
import com.mirzoiev.testApp.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest Controller class for column
 *
 * @author R.M.
 * @since 15.07.2022
 */
@RestController
@RequestMapping
public class ColumnController {

    @Autowired
    private ColumnService columnService;

    @GetMapping("/")
    public List<ColumnEntity> getAllColumns() {
        return columnService.getAllColumns();
    }

    @GetMapping("/{id}")
    public ColumnEntity getColumnById(@PathVariable Long id) {
        return columnService.findColumnById(id);
    }

    @PostMapping("/")
    public void createColumn(@RequestBody ColumnEntity column) {
        columnService.createColumn(column);
    }

    @PutMapping("/{id}")
    public void renameColumn(@PathVariable Long id, @RequestBody ColumnEntity column) {
        columnService.renameColumn(id, column);
    }

    @DeleteMapping("/{id}")
    public void deleteColumn(@PathVariable Long id) {
        columnService.deleteColumn(id);
    }
}

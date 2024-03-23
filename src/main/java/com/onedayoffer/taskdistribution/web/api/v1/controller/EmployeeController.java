package com.onedayoffer.taskdistribution.web.api.v1.controller;

import com.onedayoffer.taskdistribution.domain.enums.TaskStatus;
import com.onedayoffer.taskdistribution.services.EmployeeService;
import com.onedayoffer.taskdistribution.web.api.v1.dto.EmployeeDto;
import com.onedayoffer.taskdistribution.web.api.v1.dto.TaskDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sviridov_V_A
 * @version 1.0.0-SNAPSHOT
 * @since 2024-03-23
 */
@RestController
@RequestMapping(path = "employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getEmployees(@RequestParam(name = "sort", required = false) String sort) {
        return employeeService.getEmployees(sort);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getOneEmployee(@PathVariable(name = "id") Integer id) {
        return employeeService.getOneEmployee(id);
    }

    @GetMapping("{id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTasksByEmployeeId(@PathVariable(name = "id") Integer id) {
        return employeeService.getTasksByEmployeeId(id);
    }

    @PatchMapping("{id}/tasks/{taskId}/status")
    @ResponseStatus(HttpStatus.OK)
    public void changeTaskStatus(
            @PathVariable(name = "taskId") Integer taskId,
            @RequestParam(name = "newStatus") TaskStatus status
    ) {
        employeeService.changeTaskStatus(taskId, status);
    }

    @PostMapping("{id}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public void postNewTask(
            @PathVariable(name = "id") Integer id,
            @RequestBody TaskDto taskDto
    ) {
        employeeService.postNewTask(id, taskDto);
    }
}
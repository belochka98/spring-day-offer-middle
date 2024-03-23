package com.onedayoffer.taskdistribution.services;

import com.onedayoffer.taskdistribution.domain.entities.Employee;
import com.onedayoffer.taskdistribution.domain.entities.Task;
import com.onedayoffer.taskdistribution.domain.enums.TaskStatus;
import com.onedayoffer.taskdistribution.domain.repositories.EmployeeRepository;
import com.onedayoffer.taskdistribution.domain.repositories.TaskRepository;
import com.onedayoffer.taskdistribution.web.api.v1.dto.EmployeeDto;
import com.onedayoffer.taskdistribution.web.api.v1.dto.TaskDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Employee service logic-level</p>
 * Note: all operations under @Transactional(readonly=true),
 * cuz open-session-in-view has been disabled
 *
 * @author Sviridov_V_A
 * @version 1.0.0-SNAPSHOT
 * @since 2024-03-23
 */
@Slf4j
@Transactional(readOnly = true)
@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public List<EmployeeDto> getEmployees(String sort) {
        List<Employee> employees;
        if (StringUtils.isBlank(sort)) {
            employees = employeeRepository.findAll();
        } else {
            employees = employeeRepository.findAll(Sort.by(sort.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, "fio"));
        }

        return modelMapper.map(employees, new TypeToken<List<EmployeeDto>>() {
        }.getType());
    }


    public EmployeeDto getOneEmployee(Integer id) {
        final var employee = employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(employee, new TypeToken<EmployeeDto>() {
        }.getType());
    }

    public List<TaskDto> getTasksByEmployeeId(Integer id) {
        final var tasks = employeeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new)
                .getTasks();

        return modelMapper.map(tasks, new TypeToken<List<TaskDto>>() {
        }.getType());
    }

    // ToDo: check if status is null
    @Transactional
    public void changeTaskStatus(Integer taskId, TaskStatus status) {
        log.info("Trying to set new status {} to task {}", status, taskId);

        taskRepository.findById(taskId).orElseThrow(EntityNotFoundException::new)
                .setStatus(status);
    }


    @Transactional
    public void postNewTask(Integer employeeId, TaskDto newTask) {
        log.info("Trying to add task {} to user {}", newTask, employeeId);

        final var task = (Task) modelMapper.map(newTask, new TypeToken<Task>() {
        }.getType());

        final var employee = employeeRepository.findById(employeeId)
                .orElseThrow(EntityNotFoundException::new);

        task.setEmployee(employee);

        taskRepository.save(task);
    }
}

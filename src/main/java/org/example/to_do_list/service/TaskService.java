package org.example.to_do_list.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.to_do_list.dto.TaskRequest;
import org.example.to_do_list.dto.TaskResponse;
import org.example.to_do_list.entity.Task;
import org.example.to_do_list.exceptions.ResourceNotFoundException;
import org.example.to_do_list.repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public List<TaskResponse> getTask() {
        return taskRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private TaskResponse mapToResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getDueDate(),
                task.isCompleted()
        );
    }

    public TaskResponse createTask(@Valid TaskRequest request) {
        Task task = new Task();
        task.setName(request.name());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());

        Task savedTask = taskRepo.save(task);

        return mapToResponse(savedTask);
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Task not found with ID: " + id
        ));
        return mapToResponse(task);
    }

    public void deleteStudent(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Task not found with ID: " + id
        ));

        taskRepo.delete(task);
    }
}

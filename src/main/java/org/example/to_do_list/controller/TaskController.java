package org.example.to_do_list.controller;

import lombok.RequiredArgsConstructor;
import org.example.to_do_list.dto.TaskRequest;
import org.example.to_do_list.dto.TaskResponse;
import org.example.to_do_list.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks(){
        return ResponseEntity.ok(
                taskService.getTask()
        )
        ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable long id){
        return ResponseEntity.ok(
                taskService.getTaskById(id)
        );
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request){
        TaskResponse taskResponse = taskService.createTask(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        taskService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }
}

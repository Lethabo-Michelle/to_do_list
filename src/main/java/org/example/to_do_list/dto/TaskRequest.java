package org.example.to_do_list.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskRequest (

    @NotBlank(message = "Task name is required")
    @Size(max = 100, message = "Task name must not exceed 100 characters")
    String name,


    String description,

    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date cannot be in the past")
    LocalDate dueDate
    ){}

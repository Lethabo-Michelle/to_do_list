package org.example.to_do_list.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record TaskResponse (
    long id,
    String name,
    String description,
    LocalDate dueDate,
    boolean completed
){}

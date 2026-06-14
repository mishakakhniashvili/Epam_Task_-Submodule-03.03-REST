package com.epam.gymcrm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TraineeUpdateRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastName is required")
    private String lastName;

    private LocalDate dateOfBirth;

    private String address;

    @NotNull(message = "active is required")
    private Boolean active;
}

package com.epam.gymcrm.dto.trainee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.List;

@Getter
public class TraineeTrainersUpdateRequest {
    @NotBlank
    private String username;

    @NotEmpty
    private List<@NotBlank String> trainerUsernames;
}

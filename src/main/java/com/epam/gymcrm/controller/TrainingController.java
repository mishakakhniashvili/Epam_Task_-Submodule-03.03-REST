package com.epam.gymcrm.controller;

import com.epam.gymcrm.dto.AddTrainingRequest;
import com.epam.gymcrm.dto.TrainingTypeResponse;
import com.epam.gymcrm.entity.TrainingType;
import com.epam.gymcrm.exception.AuthenticationException;
import com.epam.gymcrm.facade.GymFacade;
import com.epam.gymcrm.repository.TraineeRepository;
import com.epam.gymcrm.repository.TrainerRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainingController {

    private final GymFacade gymFacade;


    public TrainingController(GymFacade gymFacade) {
        this.gymFacade = gymFacade;

    }

    @PostMapping("/trainings")
    public ResponseEntity<Void> addTraining(
            @RequestParam String password,
            @Valid @RequestBody AddTrainingRequest request
    ){
        gymFacade.addTraining(
                request.getTrainerUsername(),
                password,
                request.getTraineeUsername(),
                request.getTrainingName(),
                request.getTrainingDate(),
                request.getTrainingDuration()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/training-types")
    public ResponseEntity<List<TrainingTypeResponse>> getTrainingTypes(
            @RequestParam String username,
            @RequestParam String password
    ){
        if (!gymFacade.isTraineeCredentialsValid(username, password)
                && !gymFacade.isTrainerCredentialsValid(username, password)) {
            throw new AuthenticationException("Authentication Failed");
        }

        boolean validTrainee = gymFacade.isTraineeCredentialsValid(username, password);
        boolean validTrainer = gymFacade.isTrainerCredentialsValid(username, password);

        if (!validTrainee && !validTrainer) {
            throw new AuthenticationException("Authentication Failed");
        }

        List<TrainingType> trainingTypes = gymFacade.getTrainingTypes();

        List<TrainingTypeResponse> response = trainingTypes.stream()
                .map(type -> new TrainingTypeResponse(type.getId(), type.getTrainingTypeName()))
                .toList();

        return ResponseEntity.ok(response);
    }

}

package com.epam.gymcrm.controller;

import com.epam.gymcrm.dto.RegistrationResponse;
import com.epam.gymcrm.dto.TraineeRegistrationRequest;
import com.epam.gymcrm.entity.Trainee;
import com.epam.gymcrm.entity.User;
import com.epam.gymcrm.facade.GymFacade;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainees")
public class TraineeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TraineeController.class);

    private final GymFacade gymFacade;

    public TraineeController(GymFacade gymFacade) {
        this.gymFacade = gymFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerTrainee(
            @Valid @RequestBody TraineeRegistrationRequest request
    ) {
        LOGGER.info("Trainee registration request received for firstName={}, lastName={}",
                request.getFirstName(),
                request.getLastName());

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                null,
                null,
                false
        );

        Trainee trainee = new Trainee(
                user,
                request.getDateOfBirth(),
                request.getAddress()
        );

        Trainee createdTrainee = gymFacade.createTrainee(trainee);

        RegistrationResponse response = new RegistrationResponse(
                createdTrainee.getUser().getUsername(),
                createdTrainee.getUser().getPassword()
        );

        LOGGER.info("Trainee registered successfully with username={}", response.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
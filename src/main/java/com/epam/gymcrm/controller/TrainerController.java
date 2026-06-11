package com.epam.gymcrm.controller;

import com.epam.gymcrm.dto.RegistrationResponse;
import com.epam.gymcrm.dto.TrainerRegistrationRequest;
import com.epam.gymcrm.entity.Trainer;
import com.epam.gymcrm.facade.GymFacade;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainerController.class);

    private final GymFacade gymFacade;

    public TrainerController(GymFacade gymFacade) {
        this.gymFacade = gymFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerTrainer(
            @Valid @RequestBody TrainerRegistrationRequest request
    ) {
        LOGGER.info("Trainer registration request received for firstName={}, lastName={}",
                request.getFirstName(),
                request.getLastName());


        Trainer createdTrainer = gymFacade.createTrainer(
                request.getFirstName(),
                request.getLastName(),
                request.getSpecialization()
        );

        RegistrationResponse response = new RegistrationResponse(
                createdTrainer.getUser().getUsername(),
                createdTrainer.getUser().getPassword()
        );

        LOGGER.info("Trainer registered successfully with username={}", response.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

package com.epam.gymcrm.mapper;

import com.epam.gymcrm.dto.TraineeProfileResponse;
import com.epam.gymcrm.dto.TrainerShortResponse;
import com.epam.gymcrm.entity.Trainee;
import com.epam.gymcrm.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TraineeMapper {
    public TraineeProfileResponse toProfileResponse(Trainee trainee){
        User user = trainee.getUser();
        List<TrainerShortResponse> trainers = trainee.getTrainers().stream()
                .map((trainer) -> new TrainerShortResponse(
                        trainer.getUser().getUsername(),
                        trainer.getUser().getFirstName(),
                        trainer.getUser().getLastName(),
                        trainer.getSpecialization().getTrainingTypeName()
                        )
                )
                .toList();

        return new TraineeProfileResponse(
                user.getFirstName(),
                user.getLastName(),
                trainee.getDateOfBirth(),
                trainee.getAddress(),
                user.isActive(),
                trainers
        );
    }
}

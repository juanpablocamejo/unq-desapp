package services;

import model.builders.OutingPlaceBuilder;
import model.outings.OutingPlace;
import org.springframework.transaction.annotation.Transactional;

public class OutingPlaceService extends GenericService<OutingPlace> {

    @Transactional
    public void initialize() {
        OutingPlace oe1 = OutingPlaceBuilder.anOutingPlace().build();
        save(oe1);
    }
}


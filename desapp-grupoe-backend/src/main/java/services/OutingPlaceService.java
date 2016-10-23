package services;

import model.builders.outings.OutingPlaceBuilder;
import model.builders.time.WeekTimeScheduleBuilder;
import model.outings.OutingPlace;
import org.springframework.transaction.annotation.Transactional;
import services.initialization.Initializable;

public class OutingPlaceService extends GenericService<OutingPlace> implements Initializable {

    public OutingPlaceService() {
    }

    @Transactional
    public void initialize() {
        OutingPlace cuartetas = OutingPlaceBuilder.anOutingPlace().withName("Las Cuartetas").withDescription("Una de las pizzerias mas populares de Buenos Aires").withPrice(200).withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build()).build();
        OutingPlace tgiFridays = OutingPlaceBuilder.anOutingPlace().withName("TGI Fridays").withDescription("Restaurant popular de Puerto Madero").withPrice(600).withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build()).build();
        OutingPlace milanesa = OutingPlaceBuilder.anOutingPlace().withName("El Palacio de la Milanesa").withDescription("Altas milangas").withPrice(150).withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build()).build();
        save(cuartetas);
        save(tgiFridays);
        save(milanesa);
    }
}
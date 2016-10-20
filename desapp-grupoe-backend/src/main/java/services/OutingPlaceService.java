package services;

import model.builders.outings.OutingPlaceBuilder;
import model.builders.time.DayTimeScheduleBuilder;
import model.builders.time.TimeSlotBuilder;
import model.builders.time.WeekTimeScheduleBuilder;
import model.outings.OutingPlace;
import model.time.DayTimeSchedule;
import model.time.TimeSlot;
import model.time.WeekTimeSchedule;
import org.joda.time.LocalTime;
import org.springframework.transaction.annotation.Transactional;

public class OutingPlaceService extends GenericService<OutingPlace> {

    @Transactional
    public void initialize() {

        LocalTime morning = LocalTime.MIDNIGHT.plusHours(8);
        LocalTime midday = LocalTime.MIDNIGHT.minusHours(12);
        LocalTime night = midday.plusHours(7);

        TimeSlot morning2midday = TimeSlotBuilder.anyTimeSlot().withStart(morning).withEnd(midday).build();
        TimeSlot morning2night = TimeSlotBuilder.anyTimeSlot().withStart(morning).withEnd(night).build();
        TimeSlot midday2night = TimeSlotBuilder.anyTimeSlot().withStart(midday).withEnd(night).build();
        TimeSlot night2morning = TimeSlotBuilder.anyTimeSlot().withStart(night).withEnd(morning).build();

        DayTimeSchedule lunes = DayTimeScheduleBuilder.anyDayTimeSchedule().withWeekDay(1).withTimeSlot(morning2midday).withTimeSlot(midday2night).build();
        DayTimeSchedule martes = DayTimeScheduleBuilder.anyDayTimeSchedule().withWeekDay(2).withTimeSlot(morning2night).build();
        DayTimeSchedule miercoles = DayTimeScheduleBuilder.anyDayTimeSchedule().withWeekDay(3).withTimeSlot(morning2midday).withTimeSlot(night2morning).build();

        WeekTimeSchedule weekSchedule = WeekTimeScheduleBuilder.anyWeekTimeSchedule().addDayTimeSchedule(lunes).addDayTimeSchedule(martes).addDayTimeSchedule(miercoles).build();


        OutingPlace cuartetas = OutingPlaceBuilder.anOutingPlace().withName("Las Cuartetas").withDescription("Una de las pizzerias mas populares de Buenos Aires").withPrice(200).withWeekTimeSchedule(weekSchedule).build();
        OutingPlace tgiFridays = OutingPlaceBuilder.anOutingPlace().withName("TGI Fridays").withDescription("Restaurant popular de Puerto Madero").withPrice(600).withWeekTimeSchedule(weekSchedule).build();
        OutingPlace milanesa = OutingPlaceBuilder.anOutingPlace().withName("El Palacio de la Milanesa").withDescription("Altas milangas").withPrice(150).withWeekTimeSchedule(weekSchedule).build();
        save(cuartetas);
        save(tgiFridays);
        save(milanesa);
    }
}


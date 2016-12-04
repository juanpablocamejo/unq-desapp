package services;

import exceptions.EntityValidationException;
import model.builders.outings.OutingPlaceBuilder;
import model.builders.time.WeekTimeScheduleBuilder;
import model.locations.Address;
import model.locations.Coord;
import model.outings.OutingPlace;
import org.springframework.transaction.annotation.Transactional;
import persistence.AddressDAO;
import persistence.OutingPlaceDAO;
import persistence.strategies.OutingFilter;
import services.initialization.Initializable;

import java.util.List;
import java.util.regex.Pattern;

public class OutingPlaceService extends GenericService<OutingPlace> implements Initializable {

    private AddressDAO addressDAO;

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }


    @Transactional
    public void initialize() throws EntityValidationException {

        OutingPlace cuartetas = OutingPlaceBuilder.anOutingPlace()
                .withName("Las Cuartetas")
                .withDescription("Una de las pizzerias mas populares de Buenos Aires")
                .withPrice(200)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("/images/cuartetas.jpg")
                .withAddress(new Address(new Coord(-34.60375, -58.3785746), "Buenos Aires"))
                //.withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().withDayAndTimeSlot(1, TimeSlotBuilder.anyTimeSlot().withStart(LocalTime.MIDNIGHT).build()).build())
                .build();

        OutingPlace tgiFridays = OutingPlaceBuilder.anOutingPlace()
                .withName("TGI Fridays")
                .withDescription("Restaurant popular de Puerto Madero")
                .withPrice(600)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("/images/tgi.jpg")
                .withAddress(new Address(new Coord(-34.6095008, -58.3662253), "Buenos Aires"))
                .build();

        OutingPlace mcdonalds = OutingPlaceBuilder.anOutingPlace()
                .withName("Mc Donalds")
                .withDescription("Me encanta!")
                .withPrice(150)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("/images/mc.jpg")
                .withAddress(new Address(new Coord(-34.6038946, -58.3807468), "Microcentro"))
                .build();
        OutingPlace sigalavaca = OutingPlaceBuilder.anOutingPlace()
                .withName("Siga la vaca")
                .withDescription("La historia de Siga La Vaca se remonta a 1993, cuando un grupo de emprendedores vinculados a la industria de la carne apostó al proyecto que, en poco más de una década, cambiaría para siempre la gastronomía argentina.")
                .withPrice(100)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("/images/vaca.jpg")
                .withAddress(new Address(new Coord(-34.61863, -58.3651501), "Puerto Madero"))
                .build();
        OutingPlace cinemark = OutingPlaceBuilder.anOutingPlace()
                .withName("Cinemark")
                .withDescription("Cines de Argentina")
                .withPrice(85)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("/images/cine.png")
                .withAddress(new Address(new Coord(-34.5864633, -58.410349), "Recoleta"))
                .build();
        OutingPlace colon = OutingPlaceBuilder.anOutingPlace()
                .withName("Teatro Colon")
                .withDescription("El teatro mas emblematico de la Argentina")
                .withPrice(150)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("/images/colon.jpg")
                .withAddress(new Address(new Coord(-34.6010406, -58.3830786), "Microcentro"))
                .build();
        OutingPlace planetario = OutingPlaceBuilder.anOutingPlace()
                .withName("Planetario")
                .withDescription("Su cúpula tiene 20 m de diámetro. Sobre ella pueden llegar a reproducirse 8900 estrellas fijas, constelaciones y nebulosas.")
                .withPrice(150)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("/images/planetario.jpg")
                .withAddress(new Address(new Coord(-34.569722, -58.411667), "Palermo"))
                .build();
        OutingPlace granRex = OutingPlaceBuilder.anOutingPlace()
                .withName("Gran Rex")
                .withDescription("Es el teatro de los grandes espectáculos musicales y recibe a los artistas más consagrados, nacional e internacionalmente. Su capacidad es para 3.262 espectadores.")
                .withPrice(150)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("/images/rex.jpg")
                .withAddress(new Address(new Coord(-34.6033, -58.3789), "CABA"))
                .build();
        save(cuartetas);
        save(tgiFridays);
        save(mcdonalds);
        save(sigalavaca);
        save(cinemark);
        save(colon);
        save(planetario);
        save(granRex);
    }

    @Override
    @Transactional
    public void save(OutingPlace place) {
        validate(place);
        OutingPlace newOutingPlace = OutingPlaceBuilder.anOutingPlace().build();
        super.save(newOutingPlace);
        place.setId(newOutingPlace.getId());
        addressDAO.save(place.getAddress());
        super.update(place);
    }

    @Override
    @Transactional
    public void update(OutingPlace place) {
        validate(place);
        super.update(place);
    }

    @Transactional
    public List<OutingPlace> searchPlaces(OutingFilter filter) {
        OutingPlaceDAO repo = (OutingPlaceDAO) getRepository();
        return repo.findPlaces(filter);
    }

    private void validate(OutingPlace place) {
        if (!lettersOnly(place.getName())) {
            throw new EntityValidationException("Invalid name...");
        }
        if (!validNumber(place.getPrice())) {
            throw new EntityValidationException("Invalid price...");
        }

        if (!validNumber(place.getMaxAssistants())) {
            throw new EntityValidationException("Invalid maxAssistants...");
        }
    }

    private boolean validNumber(double price) {
        return (price >= 0);
    }

    private boolean lettersOnly(String text) {
        return (Pattern.matches("^[A-Za-z\\s]+$", text.trim()));
    }
}
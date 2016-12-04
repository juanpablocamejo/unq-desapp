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
import persistence.UserDAO;
import persistence.strategies.OutingFilter;
import services.initialization.Initializable;

import java.util.List;
import java.util.regex.Pattern;

public class OutingPlaceService extends GenericService<OutingPlace> implements Initializable {

    private AddressDAO addressDAO;
    private UserDAO userDAO;

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
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
        OutingPlace luna = OutingPlaceBuilder.anOutingPlace()
                .withName("Luna Park")
                .withDescription("El palacio de los deportes.")
                .withPrice(150)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("/images/luna.jpg")
                .withAddress(new Address(new Coord(-34.6023061, -58.3687473), "Retiro"))
                .build();
        OutingPlace carrefour = OutingPlaceBuilder.anOutingPlace()
                .withName("Carrefour")
                .withDescription("El mejor precio garantizado")
                .withPrice(150)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("/images/carre4.png")
                .withAddress(new Address(new Coord(-34.5888012, -58.4005737), "Recoleta"))
                .build();
        save(cuartetas);
        save(tgiFridays);
        save(mcdonalds);
        save(sigalavaca);
        save(cinemark);
        save(colon);
        save(luna);
        save(carrefour);
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
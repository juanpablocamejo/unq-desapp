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
                .withImage("http://www.lamejorpizzeria.com/wp-content/uploads/2014/12/lascuartetas-1024x768.jpg")
                .withAddress(new Address(new Coord(-34.60375, -58.3785746), "Buenos Aires"))
                .build();

        OutingPlace tgiFridays = OutingPlaceBuilder.anOutingPlace()
                .withName("TGI Fridays")
                .withDescription("Restaurant popular de Puerto Madero")
                .withPrice(600)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("https://www.fluidnetwork.co.uk/gfx/venues/22759/tgi_fridays_restaurant_london_westfield_mall_1.jpg")
                .withAddress(new Address(new Coord(-34.6095008, -58.3662253), "Buenos Aires"))
                .build();

        OutingPlace mcdonalds = OutingPlaceBuilder.anOutingPlace()
                .withName("Mc Donalds")
                .withDescription("Me encanta!")
                .withPrice(150)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("https://yt3.ggpht.com/-avTHbIvvjKY/AAAAAAAAAAI/AAAAAAAAAAA/GtO4B-SrWkA/s900-c-k-no-mo-rj-c0xffffff/photo.jpg")
                .withAddress(new Address(new Coord(-34.6038946, -58.3807468), "Microcentro"))
                .build();
        OutingPlace sigalavaca = OutingPlaceBuilder.anOutingPlace()
                .withName("Siga la vaca")
                .withDescription("www.sigalavaca.com").withPrice(100)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("http://www.ediciona.com/portafolio/image/7/6/0/7/5_siga_la_vaca_7067.jpg")
                .withAddress(new Address(new Coord(-34.61863, -58.3651501), "Puerto Madero"))
                .build();
        OutingPlace cinemark = OutingPlaceBuilder.anOutingPlace()
                .withName("Cinemark")
                .withDescription("Cines de Argentina")
                .withPrice(85)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("https://lh4.ggpht.com/Ix-Qx3srvC-cRW6xzvHHpaoYhooj-54rfDGbHtIpaXvly2YK28eB4qNgEnIzyVM7zA=w300")
                .withAddress(new Address(new Coord(-34.5864633, -58.410349), "Recoleta"))
                .build();
        OutingPlace colon = OutingPlaceBuilder.anOutingPlace()
                .withName("Teatro Colon")
                .withDescription("El teatro mas emblematico de la Argentina")
                .withPrice(150)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("http://disfrutemosba.buenosaires.gob.ar/imagenes/imagegallery/ce05-foto-exterior-nocturna-teatro-colon-copia.jpg")
                .withAddress(new Address(new Coord(-34.6010406, -58.3830786), "Microcentro"))
                .build();
        OutingPlace luna = OutingPlaceBuilder.anOutingPlace()
                .withName("Luna Park")
                .withDescription("Lugar para todo tipo de shows")
                .withPrice(150)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("https://www.brujulea.net/public/lugares/lugarpkgi58.jpg")
                .withAddress(new Address(new Coord(-34.6023061, -58.3687473), "Retiro"))
                .build();
        OutingPlace carrefour = OutingPlaceBuilder.anOutingPlace()
                .withName("Carrefour")
                .withDescription("El mejor precio garantizado")
                .withPrice(150)
                .withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build())
                .withImage("http://voxpopuli.com.ar/site/wp-content/uploads/2016/04/acaca.png")
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
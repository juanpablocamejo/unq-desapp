package services;

import exceptions.EntityValidationException;
import model.builders.outings.OutingPlaceBuilder;
import model.builders.time.WeekTimeScheduleBuilder;
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
        OutingPlace cuartetas = OutingPlaceBuilder.anOutingPlace().withName("Las Cuartetas").withDescription("Una de las pizzerias mas populares de Buenos Aires").withPrice(200).withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build()).withImage("https://www.brujulea.net/public/lugares/lugarpkgi58.jpg").build();
        OutingPlace tgiFridays = OutingPlaceBuilder.anOutingPlace().withName("TGI Fridays").withDescription("Restaurant popular de Puerto Madero").withPrice(600).withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build()).withImage("https://www.brujulea.net/public/lugares/lugarpkgi58.jpg").build();
        OutingPlace milanesa = OutingPlaceBuilder.anOutingPlace().withName("El Palacio de la Milanesa").withDescription("Altas milangas").withPrice(150).withWeekTimeSchedule(WeekTimeScheduleBuilder.anyWeekTimeSchedule().build()).withImage("https://www.brujulea.net/public/lugares/lugarpkgi58.jpg").build();
        save(cuartetas);
        save(tgiFridays);
        save(milanesa);
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
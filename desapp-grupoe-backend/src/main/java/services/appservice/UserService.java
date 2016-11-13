package services.appservice;

import model.builders.ProfileBuilder;
import model.builders.UserBuilder;
import model.users.Profile;
import model.users.User;
import org.springframework.transaction.annotation.Transactional;
import persistence.AddressDAO;
import persistence.ProfileDAO;
import services.initialization.Initializable;

public class UserService extends GenericService<User> implements Initializable {

    private AddressDAO addressDAO;
    private ProfileDAO profileDAO;

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public ProfileDAO getProfileDAO() {
        return profileDAO;
    }

    public void setProfileDAO(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    @Transactional
    public void initialize() {
        User tomas = UserBuilder.anyUser().withName("Tomas").withSurname("Perez").build();
        User diego = UserBuilder.anyUser().withName("Diego").withSurname("Garcia").build();
        save(tomas);
        save(diego);
    }

    @Override
    @Transactional
    public void save(User user) {
        Profile newProfile = ProfileBuilder.anyProfile().build();
        profileDAO.save(newProfile);
        user.getProfile().setId(newProfile.getId());
        profileDAO.update(user.getProfile());
        addressDAO.save(user.getAddress());
        User newUser = UserBuilder.anyUser().build();
        super.save(newUser);
        user.setId(newUser.getId());
        super.update(user);
    }
}
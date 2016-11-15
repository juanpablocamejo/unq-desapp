package services.appservice;

import model.builders.ProfileBuilder;
import model.builders.UserBuilder;
import model.users.Profile;
import model.users.User;
import org.springframework.transaction.annotation.Transactional;
import persistence.AddressDAO;
import persistence.ProfileDAO;
import persistence.UserDAO;
import services.initialization.Initializable;

import java.util.List;

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
        User tomas = UserBuilder.anyUser().withName("Tomas").withSurname("Perez").withEmail("tperez@gmail.com").withPriceLimit(50).withImage("https://organicthemes.com/demo/profile/files/2012/12/profile_img.png").build();
        User diego = UserBuilder.anyUser().withName("Diego").withSurname("Garcia").withEmail("dgarcia@gmail.com").withPriceLimit(200).withImage("https://organicthemes.com/demo/profile/files/2012/12/profile_img.png").build();
        save(tomas);
        save(diego);
        User esteban = UserBuilder.anyUser().withName("Esteban").withSurname("Schafir").withEmail("esteban.schafir@gmail.com").withPriceLimit(300).withFriend(tomas).withImage("https://organicthemes.com/demo/profile/files/2012/12/profile_img.png").build();
        User juan = UserBuilder.anyUser().withName("Juan Pablo").withSurname("Camejo").withEmail("jp.came@gmail.com").withPriceLimit(250).withImage("https://organicthemes.com/demo/profile/files/2012/12/profile_img.png").build();
        User lio = UserBuilder.anyUser().withName("Leonel").withSurname("Messi").withEmail("leomessi@gmail.com").withPriceLimit(50000).withFriend(esteban).withFriend(juan).withImage("https://s-media-cache-ak0.pinimg.com/736x/f6/f4/b7/f6f4b7430f8abe6ea809c9312525d5ed.jpg").build();
        save(esteban);
        save(juan);
        save(lio);
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

    @Transactional
    public User findByEmail(String email) {
        UserDAO repo = (UserDAO) getRepository();
        return repo.findByEmail(email);
    }

    @Transactional
    public List<String> findByName(String name) {
        UserDAO repo = (UserDAO) getRepository();
        return repo.findByName(name);
    }
}
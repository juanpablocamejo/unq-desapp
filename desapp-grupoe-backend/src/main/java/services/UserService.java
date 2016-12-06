package services;

import exceptions.EntityValidationException;
import model.builders.ProfileBuilder;
import model.builders.UserBuilder;
import model.users.Profile;
import model.users.User;
import org.springframework.transaction.annotation.Transactional;
import persistence.*;
import services.initialization.Initializable;

import java.util.List;
import java.util.regex.Pattern;

public class UserService extends GenericService<User> implements Initializable {

    private AddressDAO addressDAO;
    private ProfileDAO profileDAO;
    private TagDAO tagDAO;

    public UserService() {
    }

    public UserService(GenericRepository udao, AddressDAO aDao, ProfileDAO pDao) {
        this.setRepository(udao);
        this.addressDAO = aDao;
        this.profileDAO = pDao;
    }

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

    public TagDAO getTagDAO() {
        return tagDAO;
    }

    public void setTagDAO(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Transactional
    public void initialize() throws EntityValidationException {
        User tomas = UserBuilder.anyUser().withName("Tomas").withSurname("Perez").withEmail("tperez@gmail.com").withPriceLimit(50).withImage("https://organicthemes.com/demo/profile/files/2012/12/profile_img.png").build();
        User diego = UserBuilder.anyUser().withName("Diego").withSurname("Garcia").withEmail("dgarcia@gmail.com").withPriceLimit(200).withImage("https://organicthemes.com/demo/profile/files/2012/12/profile_img.png").build();
        save(tomas);
        save(diego);
        User esteban = UserBuilder.anyUser().withName("Esteban").withSurname("Schafir").withEmail("esteban.schafir@gmail.com").withPriceLimit(300).withFriend(tomas).withImage("https://organicthemes.com/demo/profile/files/2012/12/profile_img.png").withTag(tagDAO.findById(23)).withTag(tagDAO.findById(22)).withTag(tagDAO.findById(24)).build();
        User juan = UserBuilder.anyUser().withName("Juan Pablo").withSurname("Camejo").withEmail("jp.came@gmail.com").withPriceLimit(250).withImage("https://organicthemes.com/demo/profile/files/2012/12/profile_img.png").withTag(tagDAO.findById(23)).withTag(tagDAO.findById(22)).withTag(tagDAO.findById(24)).build();
        User lio = UserBuilder.anyUser().withName("Leonel").withSurname("Messi").withEmail("leomessi@gmail.com").withPriceLimit(50000).withFriend(esteban).withFriend(juan).withImage("https://s-media-cache-ak0.pinimg.com/736x/f6/f4/b7/f6f4b7430f8abe6ea809c9312525d5ed.jpg").build();
        User jose = UserBuilder.anyUser().withName("Jose").withSurname("Gonzalez").withEmail("jgonzalez@gmail.com").withPriceLimit(1000).withFriend(diego).withFriend(tomas).withImage("https://organicthemes.com/demo/profile/files/2012/12/profile_img.png").build();
        User marcelo = UserBuilder.anyUser().withName("Marcelo").withSurname("Sovich").withEmail("msovich@gmail.com").withPriceLimit(450).withImage("https://organicthemes.com/demo/profile/files/2012/12/profile_img.png").build();
        User ana = UserBuilder.anyUser().withName("Ana").withSurname("Tamariz").withEmail("atamariz@gmail.com").withPriceLimit(800).withFriend(esteban).withImage("https://organicthemes.com/demo/profile/files/2012/12/profile_img.png").build();
        User dai = UserBuilder.anyUser().withName("Dai").withSurname("Vernon").withEmail("daivernon@gmail.com").withPriceLimit(1000).withFriend(ana).withImage("https://organicthemes.com/demo/profile/files/2012/12/profile_img.png").build();
        save(esteban);
        save(juan);
        save(lio);
        save(jose);
        save(marcelo);
        save(ana);
        save(dai);
    }

    @Override
    @Transactional
    public void save(User user) {
        if (!(findByEmail(user.getEmail()) == null)) {
            throw new EntityValidationException("duplicated_user");
        }
        validate(user);
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

    @Override
    @Transactional
    public void update(User user) {
        validate(user);
        super.update(user);
    }

    @Transactional
    public User findByEmail(String email) {
        if (!isMail(email)) {
            throw new EntityValidationException("invalid_email");
        }
        UserDAO repo = (UserDAO) getRepository();
        return repo.findByEmail(email);
    }

    @Transactional
    public List<String> findByName(String name) {
        if (!lettersOnly(name)) {
            throw new EntityValidationException("invalid_name");
        }
        UserDAO repo = (UserDAO) getRepository();
        return repo.findByName(name);
    }

    private void validate(User user) {
        if (!lettersOnly(user.getName()) || user.getName().length() > 50) {
            throw new EntityValidationException("invalid_name");
        }
        if (!lettersOnly(user.getSurname()) || user.getSurname().length() > 50) {
            throw new EntityValidationException("invalid_surname");
        }

        if (!isMail(user.getEmail())) {
            throw new EntityValidationException("invalid_email");
        }

        if (user.getProfile().getInexpensiveOutingLimit() < 0 || user.getProfile().getInexpensiveOutingLimit() > 50000) {
            throw new EntityValidationException("invalid_outing_limit");
        }
    }

    private boolean isMail(String text) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(text);
        return m.matches();
    }

    private boolean lettersOnly(String text) {
        return (Pattern.matches("^[A-Za-z\\s]+$", text.trim()));
    }
}
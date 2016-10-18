package services;

import model.builders.ProfileBuilder;
import model.builders.UserBuilder;
import model.users.Profile;
import model.users.User;
import org.springframework.transaction.annotation.Transactional;

public class UserService extends GenericService<User> {

    @Transactional
    public void initialize() {
        User tomas = UserBuilder.anyUser().withName("Tomas").withSurname("Perez").build();
        User diego = UserBuilder.anyUser().withName("Diego").withSurname("Garcia").build();
        save(tomas);
        save(diego);
    }

    @Transactional
    public Profile getProfile(int id) {
        return getRepository().findById(id).getProfile();
    }
}

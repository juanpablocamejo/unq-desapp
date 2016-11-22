package services.builders;


import model.users.User;
import persistence.AddressDAO;
import persistence.GenericRepository;
import persistence.ProfileDAO;
import persistence.UserDAO;
import services.UserService;

public class UserServiceBuilder {

    private GenericRepository<User> repository = new UserDAO();
    private AddressDAO addressDAO = new AddressDAO();
    private ProfileDAO profileDAO = new ProfileDAO();

    public static UserServiceBuilder anyUserService() {
        return new UserServiceBuilder();
    }

    public UserService build() {
        return new UserService(repository, addressDAO, profileDAO);
    }

    public UserServiceBuilder withAddressDAO(AddressDAO adao) {
        this.addressDAO = adao;
        return this;
    }

    public UserServiceBuilder withProfileDAO(ProfileDAO pdao) {
        this.profileDAO = pdao;
        return this;
    }

    public UserServiceBuilder withRepository(UserDAO udao) {
        this.repository = udao;
        return this;
    }
}

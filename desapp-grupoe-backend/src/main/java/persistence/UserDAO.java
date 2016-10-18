package persistence;

import model.users.User;

public class UserDAO extends HibernateGenericDAO<User> implements GenericRepository<User> {

    @Override
    protected Class<User> getDomainClass() {
        return User.class;
    }


}

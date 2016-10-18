package persistence;


import model.users.Profile;
import model.users.User;

import java.util.List;

public class UserDAO extends HibernateGenericDAO<User> implements GenericRepository<User> {

    @Override
    protected Class<User> getDomainClass() {
        return User.class;
    }


    public List<User> findByName(String name) {
        return (List<User>) this.getHibernateTemplate()
                .find("from " + this.getDomainClass() + " where name = ?", name);
    }

    public User findById(int id) {
        return super.findById(id);
    }

    public Profile getProfile(int id) {
        return this.findById(id).getProfile();
    }

    public List<User> getFriends(int id) {
        return this.findById(id).getFriends();
    }


}

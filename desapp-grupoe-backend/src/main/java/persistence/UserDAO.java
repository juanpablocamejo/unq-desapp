package persistence;

import model.users.User;
import org.hibernate.Query;

import java.util.List;

public class UserDAO extends HibernateGenericDAO<User> implements GenericRepository<User> {

    @Override
    protected Class<User> getDomainClass() {
        return User.class;
    }

    public User findByEmail(String email) {
        String hql = "select user from User user where user.email = :email";
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();
        return user;
    }

    public List<String> findByName(String name) {
        String hql = "select user.id, user.name + ' ' + user.surname, user.image from User user where UPPER(user.name + user.surname) LIKE UPPER(:name)";
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("name", "%" + name + "%");
        query.setMaxResults(5);
        return (List<String>) query.list();
    }
}

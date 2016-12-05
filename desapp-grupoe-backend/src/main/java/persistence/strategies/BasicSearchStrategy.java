package persistence.strategies;


import model.outings.OutingEvent;
import model.outings.OutingPlace;
import model.users.User;
import org.hibernate.Query;
import persistence.HibernateGenericDAO;

import java.util.List;

public abstract class BasicSearchStrategy implements IPlanningStrategy {

    private HibernateGenericDAO dao;

    public HibernateGenericDAO getDao() {
        return dao;
    }

    public void setDao(HibernateGenericDAO dao) {
        this.dao = dao;
    }

    @Override
    public Query getEventsQuery(OutingFilter filter) {
        String hql = "select oe from OutingEvent oe join oe.tags tag where tag.name = :tagName and  oe.maxAssistants >= 4 * :assistants";
        Query query = dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("tagName", filter.getStrategy());
        query.setParameter("assistants", filter.getAssistants());
        return query;
    }

    @Override
    public Query getPlacesQuery(OutingFilter filter) {
        String hql = "select op from OutingPlace op join op.tags tag where tag.name = :tagName and  op.maxAssistants >= 4 * :assistants";
        Query query = dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("tagName", filter.getStrategy());
        query.setParameter("assistants", filter.getAssistants());
        return query;
    }

    @Override
    public Boolean hasEvents() {
        return true;
    }

    @Override
    public Boolean hasPlaces() {
        return true;
    }

    @Override
    public abstract List<OutingEvent> orderEvents(User user, List<OutingEvent> queryResults);

    @Override
    public abstract List<OutingPlace> orderPlaces(User user, List<OutingPlace> queryResults);



}

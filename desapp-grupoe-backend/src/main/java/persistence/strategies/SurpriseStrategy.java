package persistence.strategies;

import model.outings.OutingEvent;
import model.outings.OutingPlace;
import model.users.User;
import org.hibernate.Query;
import persistence.HibernateGenericDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SurpriseStrategy implements IPlanningStrategy {

    private HibernateGenericDAO dao;

    public SurpriseStrategy(HibernateGenericDAO dao) {
        this.setDao(dao);
    }

    public HibernateGenericDAO getDao() {
        return dao;
    }

    public void setDao(HibernateGenericDAO dao) {
        this.dao = dao;
    }

    @Override
    public Query getEventsQuery(OutingFilter filter) {
        String hql = "select oe from OutingEvent oe where oe.maxAssistants >= 4 * :assistants";
        Query query = dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("assistants", filter.getAssistants());
        return query;
    }

    @Override
    public Query getPlacesQuery(OutingFilter filter) {
        String hql = "select op from OutingPlace op where op.maxAssistants >= 4 * :assistants";
        Query query = dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
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
    public List<OutingEvent> orderEvents(User user, List<OutingEvent> queryResults) {
        List<OutingEvent> events = new ArrayList<>();
        events.addAll(queryResults);

        Comparator<OutingEvent> comparator = new Comparator<OutingEvent>() {
            @Override
            public int compare(OutingEvent o1, OutingEvent o2) {
                return new Integer(o2.getAssistants().size()).compareTo(o1.getAssistants().size());
            }
        };
        Collections.sort(events, comparator);

        return events;
    }

    @Override
    public List<OutingPlace> orderPlaces(User user, List<OutingPlace> queryResults) {
        List<OutingPlace> places = new ArrayList<>();
        places.addAll(queryResults);

        Comparator<OutingPlace> comparator = new Comparator<OutingPlace>() {
            @Override
            public int compare(OutingPlace o1, OutingPlace o2) {
                return new Integer(o2.getAssistants().size()).compareTo(o1.getAssistants().size());
            }
        };
        Collections.sort(places, comparator);
        return places;
    }
}

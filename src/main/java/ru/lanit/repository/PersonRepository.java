package ru.lanit.repository;

import org.hibernate.Session;
import ru.lanit.entity.Address;
import ru.lanit.entity.Person;
import ru.lanit.exception.MappingException;
import ru.lanit.exception.NotFoundEntityException;
import ru.lanit.mapper.request.AddressMapper;
import ru.lanit.mapper.request.PersonMapper;
import ru.lanit.provider.SessionProvider;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

public class PersonRepository implements RepositoryInterface {

    public static List<Person> getList(boolean useRelations) {
        try (Session session = SessionProvider.getInstance().getSession()) {
            CriteriaQuery<Person> cq = session.getCriteriaBuilder().createQuery(Person.class);
            Root<Person> rootEntry = cq.from(Person.class);
            if (useRelations) {
                rootEntry.fetch("addresses", JoinType.LEFT);
            }
            CriteriaQuery<Person> all = cq.select(rootEntry);
            TypedQuery<Person> allQuery = session.createQuery(all);
            return allQuery.getResultList();
        }
    }

    public static List<Person> getList() {
        return getList(true);
    }

    public static Person getById(int id) throws NotFoundEntityException {
        try (Session session = SessionProvider.getInstance().getSession()) {
            Person person = (Person) session.get(Person.class, id);
            if (person == null) {
                throw new NotFoundEntityException();
            }
            return person;
        }
    }

    public static void save(Person person) {
        try (Session session = SessionProvider.getInstance().getSession()) {
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        }
    }
}

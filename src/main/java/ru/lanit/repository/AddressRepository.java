package ru.lanit.repository;

import org.hibernate.Session;
import ru.lanit.entity.Address;
import ru.lanit.provider.SessionProvider;

public class AddressRepository implements RepositoryInterface {

    public static void save(Address address){
        try (Session session = SessionProvider.getInstance().getSession()) {
            session.beginTransaction();
            session.save(address);
            session.getTransaction().commit();
        }
    }
}

package lk.royal.project.dao.custom.impl;

import lk.royal.project.dao.custom.RegistrationDAO;
import lk.royal.project.entity.Registration;
import lk.royal.project.factory.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public String getLastRegId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("SELECT regId FROM Registration ORDER BY regId DESC LIMIT 1");
        String id = (String) sqlQuery.uniqueResult();
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public boolean add(Registration entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(Registration entity) throws Exception {
        return false;
    }

    @Override
    public Registration find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Registration> findAll() throws Exception {
        return null;
    }
}

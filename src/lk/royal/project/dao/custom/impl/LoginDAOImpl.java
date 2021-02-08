package lk.royal.project.dao.custom.impl;

import lk.royal.project.dao.custom.LoginDAO;
import lk.royal.project.entity.Login;
import lk.royal.project.factory.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean add(Login entity) throws Exception {
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
    public boolean update(Login entity) throws Exception {
        return false;
    }

    @Override
    public Login find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Login login = session.get(Login.class, s);

        transaction.commit();
        session.close();
        return login;
    }

    @Override
    public List<Login> findAll() throws Exception {
        return null;
    }
}

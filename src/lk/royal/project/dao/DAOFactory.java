package lk.royal.project.dao;

import lk.royal.project.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public <T extends SuperDAO> T getDAO(DAOType daoType){
        switch (daoType){
            case STUDENT:
                return (T) new StudentDAOImpl();
            case COURSE:
                return (T) new CourseDAOImpl();
            case REGISTRATION:
                return (T) new RegistrationDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case LOGIN:
                return (T)new LoginDAOImpl();
            default:
                return null;
        }
    }
}

package lk.royal.project.bo.custom.impl;

import lk.royal.project.bo.custom.DefaultBO;
import lk.royal.project.dao.DAOFactory;
import lk.royal.project.dao.DAOType;
import lk.royal.project.dao.custom.impl.QueryDAOImpl;

public class DefaultBOImpl implements DefaultBO {

    QueryDAOImpl queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
    @Override
    public int getTotalStudent() throws Exception {
        return queryDAO.getTotalStudent();
    }

    @Override
    public int getTotalCourse() throws Exception {
        return queryDAO.getTotalCourse();
    }
}

package lk.royal.project.bo.custom.impl;

import lk.royal.project.bo.custom.LoginBO;
import lk.royal.project.dao.DAOFactory;
import lk.royal.project.dao.DAOType;
import lk.royal.project.dao.custom.impl.LoginDAOImpl;
import lk.royal.project.dto.LoginDTO;
import lk.royal.project.entity.Login;

public class LoginBOImpl implements LoginBO {

    LoginDAOImpl dao = DAOFactory.getInstance().getDAO(DAOType.LOGIN);
    @Override
    public boolean saveLogin(LoginDTO dto) throws Exception {
        return dao.add(new Login(dto.getUserName(),dto.getPassword()));
    }
}

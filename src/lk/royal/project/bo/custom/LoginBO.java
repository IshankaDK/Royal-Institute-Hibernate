package lk.royal.project.bo.custom;

import lk.royal.project.bo.SuperBO;
import lk.royal.project.dto.LoginDTO;

public interface LoginBO extends SuperBO {
    boolean saveLogin(LoginDTO dto)throws Exception;
    LoginDTO getLogin(String id)throws Exception;
    boolean updatePassword(LoginDTO dto)throws Exception;
}

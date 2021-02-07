package lk.royal.project.bo.custom;

import lk.royal.project.bo.SuperBO;
import lk.royal.project.dto.RegistrationDTO;
import lk.royal.project.dto.StudentDTO;

import java.util.List;

public interface RegistrationBO extends SuperBO {
    public boolean addRegistration(RegistrationDTO registrationDTO) throws Exception;

    public RegistrationDTO getRegistration(String id) throws Exception;

    public String newRegID() throws Exception;
}

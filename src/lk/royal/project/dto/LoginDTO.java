package lk.royal.project.dto;

import lk.royal.project.entity.SuperEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

public class LoginDTO {
    private String userName;
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String userName, String password) {
        this.setUserName(userName);
        this.setPassword(password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package lk.royal.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login implements SuperEntity{
    @Id
    private String userName;
    private String password;

    public Login() {
    }

    public Login(String userName, String password) {
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

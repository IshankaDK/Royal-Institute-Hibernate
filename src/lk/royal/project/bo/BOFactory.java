package lk.royal.project.bo;

import lk.royal.project.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BOType boType) {
        switch (boType) {
            case STUDENT:
                return (T) new StudentBOImpl();
            case COURSE:
                return (T) new CourseBOImpl();
            case REGISTRATION:
                return (T) new RegistrationBOImpl();
            case REPORT:
                return (T) new ReportBOImpl();
            case DEFAULT:
                return (T) new DefaultBOImpl();
            case LOGIN:
                return (T) new LoginBOImpl();
            default:
                return null;
        }
    }

}

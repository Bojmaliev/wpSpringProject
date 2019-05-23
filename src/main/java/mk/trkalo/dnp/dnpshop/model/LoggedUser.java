package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class LoggedUser extends User{
    public String email;
    public String password;
    @Enumerated(EnumType.STRING)
    public Role role = Role.ROLE_CLIENT;

    public LoggedUser(String name, String email, String password){
        super(name);
        this.email=email;
        this.password=password;
    }

    public static LoggedUser createNewAdmin(String name, String email, String password) {
        LoggedUser admin =  new LoggedUser(name,email,password);
        admin.role= Role.ROLE_ADMIN;
        return admin;
    }
}

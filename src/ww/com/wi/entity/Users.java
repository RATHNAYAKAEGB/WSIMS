package ww.com.wi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users implements SuperEntity {
    private String fullname;
    private String email;
    private String nic;
    @Id
    private String userName;
    private String password;
    public Users() {
    }

    public Users(String fullname, String email, String nic, String userName, String password) {
        this.fullname = fullname;
        this.email = email;
        this.nic = nic;
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Users{" +
                "fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", nic='" + nic + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}

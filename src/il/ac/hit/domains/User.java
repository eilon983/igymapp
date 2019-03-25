package il.ac.hit.domains;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Mapping User Table to JAVA object
 */
@Entity(name = "User")
public class User implements java.io.Serializable {

    /**
     * Instantiates a new User
     * @params id id
     * @params username User name
     * @params password User password
     * @params email User email
     */
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String userName;

    @Column(name = "PASS")
    private String password;

    @Column(name = "EMAIL")
    private String email;



    public User(String username, String password,String email) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
    }
    public User(){};
    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
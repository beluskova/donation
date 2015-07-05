package models;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Table;
import play.db.jpa.Model;
import play.db.jpa.Blob;

@Entity
public class User extends Model
{
  public boolean usCitizen;
  public String firstName;
  public String lastName;
  public String email;
  public String password;

  public User(boolean usCitizen, String firstName, String lastName, String email, String password)
  {
    this.usCitizen = usCitizen;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  public static User findByEmail(String email)
  {
    return find("email", email).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }
}

package models;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class User extends Model
{
  public boolean usCitizen;
  public String firstName;
  public String lastName;
  public String age;   //story01
  public String email;
  public String password;
  public String state;  //story01
  
  public User(boolean usCitizen, String firstName, String lastName, String age, String email, String password, String state)
  {
    this.usCitizen = usCitizen;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;    //story01
    this.email = email;
    this.password = password;
    this.state = state;   //story01
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

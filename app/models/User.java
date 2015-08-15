package models;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class User extends Model
{
  public boolean usCitizen;
  public String firstName;
  public String lastName;
  public String age;               //story01
  public String email;
  public String password;
  public String state;             //story01
  public String addrLine1;         //story05
  public String addrLine2;         //story05
  public String city;              //story05
  public String zipcode;           //story05
  
  //story01: age and state added here
  //story05: addrLine1, addrLine2, city, zipcode added here
  public User(boolean usCitizen, String firstName, String lastName, String age, String email, String password, String state,
              String addrLine1, String addrLine2, String city, String zipcode)
  {
    this.usCitizen = usCitizen;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;                //story01
    this.email = email;
    this.password = password;
    this.state = state;            //story01
    this.addrLine1 = addrLine1;    //story05
    this.addrLine2 = addrLine2;    //story05
    this.city = city;              //story05
    this.zipcode = zipcode;        //story05
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

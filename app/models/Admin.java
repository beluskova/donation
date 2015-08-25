// a new model created for story10
package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Admin extends Model
{
  public String adminEmail;
  public String adminPassword;

  @OneToMany
  public List<User> users;
  @OneToMany
  public List<Donation> donations;

  public Admin(String adminEmail, String adminPassword)
  {
    this.adminEmail = adminEmail;
    this.adminPassword = adminPassword;   
  }

  public static Admin findByEmail(String adminEmail)
  {
    return find("adminEmail", adminEmail).first();
  }
  
  public boolean checkPassword(String adminPassword)
  {
    return this.adminPassword.equals(adminPassword);
  }
}
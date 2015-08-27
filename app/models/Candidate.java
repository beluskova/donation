// a new model created for story06
package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class Candidate extends Model
{
  public String candidateFirstName;
  public String candidateLastName;
  public String candidateEmail;
  public String candidatePassword;
  public long target;           //story12
  @OneToOne                     //story11
  public Office office;
  @OneToMany
  public List<User> users;
  @OneToMany
  public List<Donation> donations;

  public Candidate(String candidateFirstName, String candidateLastName, String candidateEmail, String candidatePassword, long target)
  {
    this.candidateFirstName = candidateFirstName;
    this.candidateLastName = candidateLastName;
    this.candidateEmail = candidateEmail;
    this.candidatePassword = candidatePassword;
    this.target = target;      //story12
  }
  //story11
  public void addOffice(Office office)
  {
    this.office = office;
  }

  public static Candidate findByEmail(String candidateEmail)
  {
    return find("candidateEmail", candidateEmail).first();
  }
}